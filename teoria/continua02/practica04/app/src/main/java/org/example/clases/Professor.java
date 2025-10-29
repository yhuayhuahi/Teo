package org.example.clases;

// Docente Universitario
public class Professor extends Participant{
    protected String faculty;
    protected String specialization;

    public Professor(
        String name, 
        int age, 
        String email, 
        String phoneNumber, 
        String faculty, 
        String specialization
    ) {
        super(name, age, email, phoneNumber);
        this.faculty = faculty;
        this.specialization = specialization;
    }

    @Override
    public String getParticipantType() {
        return "Docente Universitario";
    }

    // getters and setters
    public String getFaculty() { return this.faculty; }
    public String getSpecialization() { return this.specialization; }

    public void setFaculty(String faculty) { this.faculty = faculty; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
}
