/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;

import com.uja.ssmmaa.curso2021.practica2_multiagentes.util.MensajeConsola;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Tarea cíclica que recoge un mensaje INFORM y lo transforma 
 * en un MensajeConsola y se lo pasa al agente para que lo almacene 
 * y crea una TareaPresentarMensaje.
 * @author pedroj
 */
public class TareaRecepcionMensajes extends CyclicBehaviour {
    private RecepcionMensaje agente;

    public TareaRecepcionMensajes(Agent a) {
        super(a);
        this.agente = (RecepcionMensaje) a;
    }
    
    @Override
    public void action() {
        //Solo se atenderán mensajes INFORM
        MessageTemplate plantilla = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage mensaje = myAgent.receive(plantilla);
        if (mensaje != null) {
            //procesamos el mensaje
            MensajeConsola mensajeConsola = new MensajeConsola(mensaje.getSender().getName(),
                                    mensaje.getContent());
            agente.addMensaje(mensajeConsola);
            myAgent.addBehaviour(new TareaPresentarMensaje(myAgent));
        } else
           block();
    }
}
