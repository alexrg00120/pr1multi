/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.agentes;

import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.D100;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NombreServicio;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NombreServicio.CONSOLA;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NombreServicio.PRODUCTO;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.PRIMERO;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.REPETICION2;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.REPETICION3;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.TIPO_SERVICIO;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.EnvioMensaje;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.GeneraRecursos;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.RecepcionPedido;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.ResolucionPedido;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.SuscripcionDF;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.TareaGeneraRecursos;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.TareaRecepcionPedido;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.TareaResolucionPedido;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas.TareaSuscripcionDF;
import com.uja.ssmmaa.curso2021.practica2_multiagentes.util.Pedido;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ghutt
 */
public class AgenteVendedor extends Agent implements SuscripcionDF, EnvioMensaje,
        GeneraRecursos, RecepcionPedido, ResolucionPedido {
    //Variables del agente

    private ArrayList<AID> agentesConsola;
    private ArrayList<String> mensajesPendientes;
    private ArrayList<Pedido> pedidosPendientes;
    private AID agenteDF;
    private Pedido reservas;
    private boolean RecursosSuficientes;

    @Override
    protected void setup() {
        //Inicialización de las variables del agente
        mensajesPendientes = new ArrayList();
        pedidosPendientes = new ArrayList();
        agentesConsola = new ArrayList();
        agenteDF = new AID("df", AID.ISLOCALNAME);
        Random aleatorio = new Random();
        reservas = new Pedido(aleatorio.nextInt(D100), aleatorio.nextInt(D100), aleatorio.nextInt(D100));
        System.out.print("Las reservas iniciales del vendedor son: "+reservas.toString());
        RecursosSuficientes=true;
        //Configuración del GUI
        //Registro del agente en las Páginas Amarrillas
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(TIPO_SERVICIO);
        sd.setName(PRODUCTO.name());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        //Registro de la Ontología
        System.out.println("Se inicia la ejecución del agente: " + this.getName());

        //Añadir las tareas principales
        addBehaviour(new TareaGeneraRecursos(this, REPETICION2));
        addBehaviour(new TareaRecepcionPedido(this, agenteDF));
        addBehaviour(new TareaResolucionPedido(this,REPETICION3 ));
        

        //Suscripción al servicio de páginas amarillas
        //Para localiar a los agentes operación y consola
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription templateSd = new ServiceDescription();
        templateSd.setType(TIPO_SERVICIO);
        templateSd.setName(CONSOLA.name());
        template.addServices(templateSd);
        addBehaviour(new TareaSuscripcionDF(this, template));
    }

    @Override
    protected void takeDown() {
        //Eliminar registro del agente en las Páginas Amarillas
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        //Liberación de recursos, incluido el GUI
        //Despedida
        System.out.println("Finaliza la ejecución del agente: " + this.getName());
    }

    //Métodos de trabajo del agente
    @Override
    public void addAgent(AID agente, NombreServicio servicio) {
        agentesConsola.add(agente);
    }

    @Override
    public boolean removeAgent(AID agente, NombreServicio servicio) {
        return agentesConsola.remove(agente);
    }

    @Override
    public AID getAgent(NombreServicio servicio) {
        if (agentesConsola.isEmpty()) {
            return null;
        } else {
            return agentesConsola.get(PRIMERO);
        }
    }

    @Override
    public String getMensaje() {
        if (mensajesPendientes.isEmpty()) {
            return null;
        } else {
            return mensajesPendientes.remove(PRIMERO);
        }
    }

    @Override
    public Pedido getPedido() {
        return reservas;
    }

    @Override
    public void addMensajeRecursos(String contenido) {
        mensajesPendientes.add(contenido);
    }

    @Override
    public void addPedido(Pedido pedido) {
        pedidosPendientes.add(pedido);
    }
    @Override
    public void removePedido() {
        pedidosPendientes.remove(PRIMERO);
    }


    @Override
    public Pedido getSiguientePedido() {
         if (pedidosPendientes.isEmpty()) {
            return null;
        } else {
            return pedidosPendientes.get(PRIMERO);
        }
    }

    @Override
    public void changeSufiecientesRecursos() {
        RecursosSuficientes=!RecursosSuficientes;
    }

    @Override
    public boolean getSuficientesRecuros() {
        return RecursosSuficientes;
    }

}
