/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes;

import java.util.Random;

/**
 *
 * @author ghutt
 */
public interface Constantes {

    public static final long REPETICION = 10000; // 10 segundos
    public static final long REPETICION2 = 2;
    public static final long REPETICION3 = 5;
    public static final int NO_ENCONTRADO = 0;
    public static final int PRIMERO = 0;
    public static final int SEGUNDO = 1;
    public static final int TERCERO = 2;
    public static final int D15 = 15;
    public static final int D100 = 100;

    public static final String TIPO_SERVICIO = "Agentes Servicio";

    public enum NombreServicio {
        PRODUCTO, CONSOLA;
    }
    public static final NombreServicio[] SERVICIOS = NombreServicio.values();

    public enum Producto {
        GOMINOLAS(5), CHICLES(10), CARAMELOS(15);

        private int valor;

        private Producto(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }

        /**
         * Devuelve una operación de las disponibles aleatoriamente
         *
         * @return Operacion; una de las operaciones disponibles
         */
        public static Producto getProducto() {
            Random aleatorio = new Random();
            int tiradaDado = aleatorio.nextInt(D15);

            for (Producto producto : Producto.values()) {
                if (producto.valor > tiradaDado) {
                    return producto;
                }
            }

            return CARAMELOS;
        }
    }
    public static final Producto[] PRODUCTOS = Producto.values();
}
