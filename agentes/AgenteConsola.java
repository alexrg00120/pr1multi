/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.agentes;

import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NO_ENCONTRADO;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NombreServicio.CONSOLA;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.PRIMERO;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.TIPO_SERVICIO;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.gui.ConsolaJFrame;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.PresentarMensaje;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.RecepcionMensaje;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.TareaRecepcionMensajes;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.util.MensajeConsola;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.ArrayList;

/**
 *
 * @author ghutt
 */
public class AgenteConsola extends Agent implements PresentarMensaje, RecepcionMensaje {
    //Variables del agente
    private ArrayList<ConsolaJFrame> myGui;
    private ArrayList<MensajeConsola> mensajesPendientes;

    @Override
    protected void setup() {
       //Inicialización de las variables del agente
       myGui = new ArrayList();
       mensajesPendientes = new ArrayList();
       
       //Configuración del GUI
       
       //Registro del agente en las Páginas Amarrillas
       DFAgentDescription dfd = new DFAgentDescription();
       dfd.setName(getAID());
       ServiceDescription sd = new ServiceDescription();
       sd.setType(TIPO_SERVICIO);
       sd.setName(CONSOLA.name());
       dfd.addServices(sd);
       try {
           DFService.register(this, dfd);
       } catch (FIPAException fe) {
            fe.printStackTrace();
       }
       
       //Registro de la Ontología
       
       System.out.println("Se inicia la ejecución del agente: " + this.getName());
       //Añadir las tareas principales
       addBehaviour(new TareaRecepcionMensajes(this));
    }

    @Override
    protected void takeDown() {
       //Eliminar registro del agente en las Páginas Amarillas
       try {
            DFService.deregister(this);
	}
            catch (FIPAException fe) {
            fe.printStackTrace();
	}

       //Liberación de recursos, incluido el GUI
       cerrarConsolas();
       
       //Despedida
       System.out.println("Finaliza la ejecución del agente: " + this.getName());
    }
    
    //Métodos de utilidad para el agente consola
    private ConsolaJFrame buscarConsola(String nombreAgente) {
        // Obtenemos la consola donde se presentarán los mensajes
        for( ConsolaJFrame gui : myGui) 
            if (gui.getNombreAgente().compareTo(nombreAgente) == NO_ENCONTRADO)
                return gui;
                    
        return null;
    }
    
    private void cerrarConsolas() {
        //Se eliminan las consolas que están abiertas
        for( ConsolaJFrame gui : myGui )
            gui.dispose();
    }      

    @Override
    public MensajeConsola getMensaje() {
        return mensajesPendientes.remove(PRIMERO);
    }

    @Override
    public ConsolaJFrame getGui(String nameAgente) {
        return buscarConsola(nameAgente);
    }

    @Override
    public void addGui(ConsolaJFrame gui) {
        myGui.add(gui);
    }

    @Override
    public void addMensaje(MensajeConsola mensaje) {
        mensajesPendientes.add(mensaje);
    }
}
