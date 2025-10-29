package org.example.clases;

// Participante
public abstract class Participant {
    protected String name;
    protected int age;
    protected String email;
    protected String phoneNumber;

    public Participant(String name, int age, String email, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public abstract String getParticipantType();

    // getters and setters
    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public String getEmail() { return this.email; }
    public String getPhoneNumber() { return this.phoneNumber; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
