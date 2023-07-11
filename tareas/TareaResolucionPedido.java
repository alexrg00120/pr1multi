/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;

import com.uja.ssmmaa.curso2021.practica2_multiagentes.util.Pedido;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/**
 *
 * @author ghutt
 */
public class TareaResolucionPedido extends TickerBehaviour {

    private ResolucionPedido agente;
    private Pedido pedido;

    public TareaResolucionPedido(Agent a,long period) {
        super(a,period);
        this.agente = (ResolucionPedido) a;
        this.pedido = null;
    }

    @Override
    protected void onTick() {
        pedido=agente.getSiguientePedido();
        if(pedido!=null){
            if (agente.getPedido().getCantidadCaramelos() >= pedido.getCantidadCaramelos()
                    && agente.getPedido().getCantidadChicles() >= pedido.getCantidadChicles()
                    && agente.getPedido().getCantidadGominolas() >= pedido.getCantidadGominolas()) {
                agente.getPedido().setCantidadCaramelos(agente.getPedido().getCantidadCaramelos()-pedido.getCantidadCaramelos());
                agente.getPedido().setCantidadChicles(agente.getPedido().getCantidadChicles()-pedido.getCantidadChicles());
                agente.getPedido().setCantidadGominolas(agente.getPedido().getCantidadGominolas()-pedido.getCantidadGominolas());
                agente.addMensajeRecursos("Se ha añadido el pedido: "+pedido.toString()+". En el almacen ahi "+agente.getPedido().toString());
                agente.removePedido();

            } else {
                if(agente.getSuficientesRecuros()){
                    agente.changeSufiecientesRecursos();
                }
                agente.addMensajeRecursos("No ahi suficientes recursos para el pedido: "+pedido.toString()+". En el almacen ahi "+agente.getPedido().toString());
            }
            myAgent.addBehaviour(new TareaEnvioMensajeSinCiclos(myAgent));
        }
    }
}
