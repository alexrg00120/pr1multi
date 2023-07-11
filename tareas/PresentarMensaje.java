/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;

import com.uja.ssmmaa.curso2021.practica2_multiagentes.util.MensajeConsola;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.gui.ConsolaJFrame;


/**
 *
 * @author ghutt
 */
public interface PresentarMensaje {
    public MensajeConsola getMensaje();
    public ConsolaJFrame getGui(String nameAgent);
    public void addGui(ConsolaJFrame gui);
    
}