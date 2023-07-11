/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;

import com.uja.ssmmaa.curso2021.practica2_multiagentes.gui.ConsolaJFrame;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.util.MensajeConsola;


import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

/**
 * Tarea que recoge un MensajeConsola del agente asociado para que se
 * visualice en una ventana ConsolaJFrame donde ese agente muestra ese 
 * tipo de mensajes.
 * @author pedroj
 */
public class TareaPresentarMensaje extends OneShotBehaviour {
    private PresentarMensaje agente;

    public TareaPresentarMensaje(Agent a) {
        super(a);
        this.agente = (PresentarMensaje) a;
    }
    
    
    @Override
    public void action() {
        //Se coge el primer mensaje
        MensajeConsola mensajeConsola = agente.getMensaje();
            
        //Se busca la ventana de consola o se crea una nueva
        ConsolaJFrame gui = agente.getGui(mensajeConsola.getNombreAgente());
        if (gui == null) {
            gui = new ConsolaJFrame(mensajeConsola.getNombreAgente());
            agente.addGui(gui);
        } 
            
        gui.presentarSalida(mensajeConsola);
    }
}
