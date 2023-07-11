/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;

import com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NombreServicio;
import jade.core.AID;
import java.util.List;

/**
 *
 * @author ghutt
 */
public interface EnvioPedido {
     public List<AID> getAgentesVendedor(NombreServicio servicio);
    public void addMensaje(String contenido);
}
