/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;

import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NombreServicio.CONSOLA;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author ghutt
 */
public class TareaEnvioMensajeSinCiclos extends OneShotBehaviour {

    private EnvioMensaje agente;

    public TareaEnvioMensajeSinCiclos(Agent a) {
        super(a);
        this.agente = (EnvioMensaje) a;
    }

    @Override
    public void action() {
         ACLMessage mensaje;
        AID consola = agente.getAgent(CONSOLA);
        if ( consola != null ) {
            String contenido = agente.getMensaje();
            if ( contenido != null ) {
                mensaje = new ACLMessage(ACLMessage.INFORM);
                mensaje.setSender(myAgent.getAID());
                mensaje.addReceiver(consola);
                mensaje.setContent(contenido);
            
                myAgent.send(mensaje);
            } else {
                // Si queremos hacer algo si no tenemos mensajes
                // pendientes para enviar a la consola
            }
        }
    }

}
