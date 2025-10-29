package org.example.clases;

// Invitado
public class Guest extends Participant {

    public Guest(String name, int age, String email, String phoneNumber) {
        super(name, age, email, phoneNumber);
    }

    @Override
    public String getParticipantType() {
        return "Invitado";
    }
}
