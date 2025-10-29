package org.example.clases;

//Administrativo 
public class Administrative extends Participant {
    private String area;
    private String experience;

    public Administrative(
        String name, 
        int age, 
        String email, 
        String phoneNumber, 
        String area,
        String experience
    ) {
        super(name, age, email, phoneNumber);
        this.area = area;
        this.experience = experience;
    }

    @Override
    public String getParticipantType() {
        return "Administrativo";
    }

    // getters and setters
    public String getArea() { return area; }
    public String getExperience() { return experience; }

    public void setArea(String area) { this.area = area; }
    public void setExperience(String experience) { this.experience = experience; }
}
