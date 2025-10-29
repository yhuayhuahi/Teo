package org.example.service;
import java.util.List;

import org.example.clases.Participant;
import org.example.clases.Speaker;

public class Register {
    // covarianza
    // Permite leer listas de cualquier subtipo de Participante
    public static void printInformation(List<? extends Participant> participants) {
        for (Participant participant : participants) {
            System.out.println("\nNombre: " + participant.getName());
            System.out.println("Edad: " + participant.getAge());
            System.out.println("Email: " + participant.getEmail());
            System.out.println("Teléfono: " + participant.getPhoneNumber());
            System.out.println("Tipo de participante: " + participant.getParticipantType());
            System.out.println("---------------------------");
        }
    }

    // contravarianza
    // Permite agregar elementos a listas de cualquier supertipo de Ponente
    public static void addPonentsToList(
        List<? super Speaker> participants,
        List<? extends Speaker> newParticipants
    ){
        for (Speaker speaker : newParticipants) {
            participants.add(speaker);
        }
        System.out.println("Se agregaron " + newParticipants.size() + " ponentes a la lista");
    }
}
