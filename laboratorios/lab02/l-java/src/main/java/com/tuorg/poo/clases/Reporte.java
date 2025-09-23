package com.tuorg.poo.clases;

public class Reporte {
    Profesor profesor;

    public Reporte(Profesor profesor) {
        this.profesor = profesor;
    }

    public Profesor getProfesor() {
        return profesor;
    }
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    //Dependencia con Estudiante
    public void generarReporte(Estudiante estudiante) {
        System.out.println("Reporte del Profesor: " + profesor.getNombre() + ", acerca del Estudiante: " + estudiante.getNombre());
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "profesor=" + profesor +
                '}';
    }
}
