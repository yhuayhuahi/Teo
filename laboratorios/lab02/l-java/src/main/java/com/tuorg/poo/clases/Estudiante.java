package com.tuorg.poo.clases;

//Hereda de Persona
public class Estudiante extends Persona {
    public Estudiante(String id, String nombre, int edad) {
        super(id, nombre, edad);
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", edad=" + getEdad() +
                '}';
    }
}