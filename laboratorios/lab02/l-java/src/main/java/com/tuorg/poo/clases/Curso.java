package com.tuorg.poo.clases;

public class Curso {
    private String id;
    private String nombre;
    // Composicion: Un curso tiene un horario
    private Horario horario;

    public Curso(String id, String nombre, Horario horario) {
        this.id = id;
        this.nombre = nombre;
        this.horario = horario;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
