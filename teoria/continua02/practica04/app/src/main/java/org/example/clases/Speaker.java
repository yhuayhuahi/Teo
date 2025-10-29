package org.example.clases;

// Ponente
public class Speaker extends Participant {
    protected String topic;
    protected int experienceYears;
    protected String type;

    public Speaker(
        String name, 
        int age, 
        String email, 
        String phoneNumber,
        String topic, 
        int experienceYears, 
        String type
    ) {
        super(name, age, email, phoneNumber);
        this.topic = topic;
        this.experienceYears = experienceYears;
        this.type = type;
    }

    @Override
    public String getParticipantType() {
        return this.type;
    }

    // getters and setters
    public String getTopic() { return this.topic; }
    public int getExperienceYears() { return this.experienceYears; }
    public String getType() { return this.type; }

    public void setTopic(String topic) { this.topic = topic; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }
    public void setType(String type) { this.type = type; }
}
    

