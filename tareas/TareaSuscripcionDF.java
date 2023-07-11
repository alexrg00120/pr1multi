/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.curso2021.practica2_multiagentes.tareas;



import com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.NombreServicio;
import static com.uja.ssmmaa.curso2021.practica2_multiagentes.Constantes.SERVICIOS;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFSubscriber;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import java.util.Iterator;

/**
 * Tarea para la suscripción al servicio de páginas amarillas para obtener
 * los agentes que prestan un tipo de servicio establecido en la plantilla
 * de suscripción.
 * @author pedroj
 */
public class TareaSuscripcionDF extends DFSubscriber {
    private SuscripcionDF agente;

    public TareaSuscripcionDF(Agent a, DFAgentDescription template) {
        super(a, template);
        this.agente = (SuscripcionDF) a;
    }

    @Override
    public void onRegister(DFAgentDescription dfad) {
        Iterator it = dfad.getAllServices();
        while( it.hasNext() ) {
            ServiceDescription sd = (ServiceDescription) it.next();
            for( NombreServicio servicio : SERVICIOS )
                if( sd.getName().equals(servicio.name()) )
                    agente.addAgent(dfad.getName(), servicio);
        }
            
        // Para depurar el funcionamiento de la tarea
        System.out.println("El agente: " + myAgent.getName() +
                    "ha encontrado a:\n\t" + dfad.getName());
    }

    @Override
    public void onDeregister(DFAgentDescription dfad) {
        AID agente = dfad.getName();
            
        for( NombreServicio servicio : SERVICIOS )
            if( this.agente.removeAgent(agente, servicio) )
                System.out.println("El agente: " + agente.getName() + 
                            " ha sido eliminado de la lista de " 
                            + myAgent.getName());
    }
}
