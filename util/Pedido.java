/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.util;

/**
 *
 * @author ghutt
 */
public class Pedido {
    private int cantidadCaramelos;
    private int cantidadChicles;
    private int cantidadGominolas;
    
    public Pedido(){
        this.cantidadCaramelos=0;
        this.cantidadChicles=0;
        this.cantidadGominolas=0;
    }
    
    public Pedido(int _cantidadCaramelos,int _cantidadChicles,int _cantidadGominolas){
        this.cantidadCaramelos=_cantidadCaramelos;
        this.cantidadChicles=_cantidadChicles;
        this.cantidadGominolas=_cantidadGominolas;
    }

    /**
     * @return the cantidadCaramelos
     */
    public int getCantidadCaramelos() {
        return cantidadCaramelos;
    }

    /**
     * @param cantidadCaramelos the cantidadCaramelos to set
     */
    public void setCantidadCaramelos(int cantidadCaramelos) {
        this.cantidadCaramelos = cantidadCaramelos;
    }

    /**
     * @return the cantidadChicles
     */
    public int getCantidadChicles() {
        return cantidadChicles;
    }

    /**
     * @param cantidadChicles the cantidadChicles to set
     */
    public void setCantidadChicles(int cantidadChicles) {
        this.cantidadChicles = cantidadChicles;
    }

    /**
     * @return the cantidadGominolas
     */
    public int getCantidadGominolas() {
        return cantidadGominolas;
    }

    /**
     * @param cantidadGominolas the cantidadGominolas to set
     */
    public void setCantidadGominolas(int cantidadGominolas) {
        this.cantidadGominolas = cantidadGominolas;
    }
     @Override
    public String toString() {
        return "Pedido ( Caramelos: " + this.cantidadCaramelos + "    Chicles: " + this.cantidadChicles  + "    Gominolas: " + this.cantidadGominolas +" )";
    }
}
