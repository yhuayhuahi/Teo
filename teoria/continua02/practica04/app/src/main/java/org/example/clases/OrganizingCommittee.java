package org.example.clases;

public class OrganizingCommittee extends Participant {
    private String role;
    private String responsibilities;

    public OrganizingCommittee(
        String name, 
        int age, 
        String email, 
        String phoneNumber, 
        String role, 
        String responsibilities
    ) {
        super(name, age, email, phoneNumber);
        this.role = role;
        this.responsibilities = responsibilities;
    }

    @Override
    public String getParticipantType() {
        return "Comité Organizador: " + role;
    }

    // getters and setters
    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public String getResponsibilities() { return responsibilities; }

    public void setResponsibilities(String responsibilities) { this.responsibilities = responsibilities; }
    
}
