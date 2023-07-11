/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.agentes;

import com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NombreServicio;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NombreServicio.PRODUCTO;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.PRIMERO;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.REPETICION;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.SERVICIOS;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.TIPO_SERVICIO;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.gui.CompradorJFrame;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.EnvioMensaje;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.EnvioPedido;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.SuscripcionDF;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.TareaEnvioMensaje;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.TareaEnvioPedido;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.TareaSuscripcionDF;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.util.Pedido;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ghutt
 */
public class AgenteComprador extends Agent implements EnvioMensaje, EnvioPedido,
                                                        SuscripcionDF{
    private CompradorJFrame myGui;
    private ArrayList<AID>[] listaAgentes;
    private ArrayList<String> mensajesPendientes;
    
    
    @Override
    protected void setup() {
       //Inicialización de las variables del agente
       mensajesPendientes = new ArrayList();
       listaAgentes = new ArrayList[SERVICIOS.length];
       for( NombreServicio servicio : SERVICIOS )
           listaAgentes[servicio.ordinal()] = new ArrayList();
       
       //Configuración del GUI
       myGui = new CompradorJFrame(this);
       myGui.setVisible(true);
       
       //Registro del agente en las Páginas Amarrillas
       
       //Registro de la Ontología
       
       System.out.println("Se inicia la ejecución del agente: " + this.getName());
       //Añadir las tareas principales
       addBehaviour(new TareaEnvioMensaje(this,REPETICION));
       
       //Suscripción al servicio de páginas amarillas
       //Para localiar a los agentes operación y consola
       DFAgentDescription template = new DFAgentDescription();
       ServiceDescription templateSd = new ServiceDescription();
       templateSd.setType(TIPO_SERVICIO);
       template.addServices(templateSd);
       addBehaviour(new TareaSuscripcionDF(this,template));
    }

    @Override
    protected void takeDown() {
       //Eliminar registro del agente en las Páginas Amarillas
       
       //Liberación de recursos, incluido el GUI
       myGui.dispose();
       
       //Despedida
       System.out.println("Finaliza la ejecución del agente: " + this.getName());
    }
    
    //Métodos de trabajo del agente
    public void enviarPedido(Pedido pedido) {
        addBehaviour(new TareaEnvioPedido(this,pedido));
    }
    
    @Override
    public AID getAgent(NombreServicio servicio) {
        if( listaAgentes[servicio.ordinal()].isEmpty() )
            return null;
        else 
            return listaAgentes[servicio.ordinal()].get(PRIMERO);
    }

    @Override
    public String getMensaje() {
        if( mensajesPendientes.isEmpty() )
            return null;
        else
            return mensajesPendientes.remove(PRIMERO);
    }

    @Override
    public List<AID> getAgentesVendedor(NombreServicio servicio) {
        return listaAgentes[servicio.ordinal()];
    }

    @Override
    public void addMensaje(String contenido) {
        mensajesPendientes.add(contenido);
    }

    @Override
    public void addAgent(AID agente, NombreServicio servicio) {
        listaAgentes[servicio.ordinal()].add(agente);
        
        if ( !listaAgentes[PRODUCTO.ordinal()].isEmpty() )
            myGui.activarEnviar(true);
    }

    @Override
    public boolean removeAgent(AID agente, NombreServicio servicio) {
        boolean resultado = listaAgentes[servicio.ordinal()].remove(agente);
        
        if ( listaAgentes[PRODUCTO.ordinal()].isEmpty() )
            myGui.activarEnviar(false);
        
        return resultado;
    }
}
