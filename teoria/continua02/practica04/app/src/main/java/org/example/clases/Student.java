package org.example.clases;

// Estudiante
public class Student extends Participant {
    protected String career;
    protected String semester;

    public Student(
        String name, 
        int age, 
        String email, 
        String phoneNumber, 
        String career, 
        String semester
    ) {
        super(name, age, email, phoneNumber);
        this.career = career;
        this.semester = semester;
    }

    @Override
    public String getParticipantType() {
        return "Estudiante";
    }

    // getters and setters
    public String getCareer() { return this.career; }
    public String getSemester() { return this.semester; }

    public void setCareer(String career) { this.career = career; }
    public void setSemester(String semester) { this.semester = semester; }
}
