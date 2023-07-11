/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;

import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.PRIMERO;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.SEGUNDO;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.TERCERO;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.util.Pedido;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author ghutt
 */
public class TareaRecepcionPedido extends CyclicBehaviour {

    private RecepcionPedido agente;
    private AID agenteDF;

    public TareaRecepcionPedido(Agent a, AID agenteDF) {
        super(a);
        this.agente = (RecepcionPedido) a;
        this.agenteDF = agenteDF;
    }

    @Override
    public void action() {
        Pedido pedido = new Pedido();

        //Recepción de la información para realizar la operación
        MessageTemplate plantilla = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.not(MessageTemplate.MatchSender(agenteDF)));
        ACLMessage mensaje = myAgent.receive(plantilla);
        
        if (mensaje != null) {
            //procesamos el mensaje
            String[] contenido = mensaje.getContent().split(",");
            try {
                pedido.setCantidadCaramelos(Integer.parseInt(contenido[PRIMERO]));
                pedido.setCantidadChicles(Integer.parseInt(contenido[SEGUNDO]));
                pedido.setCantidadGominolas(Integer.parseInt(contenido[TERCERO]));
                System.out.print("Recibido el pedido: "+pedido.toString());
                agente.addPedido(pedido);
            } catch (NumberFormatException ex) {
                // No sabemos tratar el mensaje y los presentamos por consola
                System.out.println("El agente: " + myAgent.getName()
                        + " no entiende el contenido del mensaje: \n\t"
                        + mensaje.getContent() + " enviado por: \n\t"
                        + mensaje.getSender());
            }
        } else {
            block();
        }
    }
}
