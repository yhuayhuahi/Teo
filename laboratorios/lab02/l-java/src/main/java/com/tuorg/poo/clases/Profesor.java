package com.tuorg.poo.clases;

//Hereda de Persona
public class Profesor extends Persona {
    public Profesor(String id, String nombre, int edad) {
        super(id, nombre, edad);
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", edad=" + getEdad() +
                '}';
    }
}
