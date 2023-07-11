/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;

import com.uja.ssmmaa.curso2021.practica2_multiagentes.util.Pedido;

/**
 *
 * @author ghutt
 */
public interface GeneraRecursos {
    public Pedido getPedido();
     public void changeSufiecientesRecursos();
    public boolean getSuficientesRecuros();
}
