/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;

import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NombreServicio.PRODUCTO;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.util.Pedido;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ghutt
 */
public class TareaEnvioPedido extends OneShotBehaviour {

    private EnvioPedido agente;
    private Pedido pedido;

    public TareaEnvioPedido(Agent a, Pedido pedido) {
        super(a);
        this.agente = (EnvioPedido) a;
        this.pedido = pedido;
    }

    @Override
    public void action() {
        // Se crea el mensaje
        ACLMessage mensaje = new ACLMessage(ACLMessage.INFORM);
        mensaje.setSender(myAgent.getAID());

        
        List<AID> listaAgentes = agente.getAgentesVendedor(PRODUCTO);
        Random aleatorio = new Random();
        int numVendedor = aleatorio.nextInt(listaAgentes.size());
        mensaje.addReceiver(listaAgentes.get(numVendedor));

        // Se crea el contenido
        mensaje.setContent(pedido.getCantidadCaramelos() + "," + pedido.getCantidadChicles() + "," + pedido.getCantidadGominolas());

        // Se realiza el envío
        myAgent.send(mensaje);

        //Se añade el contenido del mensaje para la consola
        agente.addMensaje("Enviado el pedido:" + pedido.toString());
    }
}
