/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;

import com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.Producto;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/**
 *
 * @author ghutt
 */
public class TareaGeneraRecursos extends TickerBehaviour {

    private GeneraRecursos agente;

    public TareaGeneraRecursos(Agent a, long period) {
        super(a, period);
        this.agente = (GeneraRecursos) a;
    }

    @Override
    protected void onTick() {
        if (!agente.getSuficientesRecuros()) {
            int cantidad;
            Producto productoSeleccionado = Constantes.Producto.getProducto();
            cantidad = productoSeleccionado.getValor();
            switch (productoSeleccionado.ordinal()) {
                case 0:
                    agente.getPedido().setCantidadGominolas(agente.getPedido().getCantidadGominolas() + cantidad);
                    System.out.print("Se han añadido a las reservas " + cantidad + " gominolas\n");
                    break;
                case 1:
                    agente.getPedido().setCantidadChicles(agente.getPedido().getCantidadChicles() + cantidad);
                    System.out.print("Se han añadido a las reservas " + cantidad + " chicles\n");
                    break;
                default:
                    agente.getPedido().setCantidadCaramelos(agente.getPedido().getCantidadCaramelos() + cantidad);
                    System.out.print("Se han añadido a las reservas " + cantidad + " caramelos\n");
                    break;
            }
            agente.changeSufiecientesRecursos();
        }
    }

}
