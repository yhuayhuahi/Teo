package org.example.clases;

// Publico en general
public class GeneralPublic extends Participant{
    private String type;
    
    public GeneralPublic(String name, int age, String email, String phoneNumber, String type) {
        super(name, age, email, phoneNumber);
        this.type = type;
    }

    @Override
    public String getParticipantType() {
        return "Publico en general: " + type;
    }

    // getters and setters
    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
}
