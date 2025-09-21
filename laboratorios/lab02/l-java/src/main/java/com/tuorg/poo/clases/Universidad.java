package com.tuorg.poo.clases;
import java.util.ArrayList;

public class Universidad {
    String nombre;
    // Agregacion: Una universidad tiene muchos cursos
    ArrayList<Curso> cursos;

    public Universidad(String nombre) {
        this.nombre = nombre;
        this.cursos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarCursos(Curso curso) {
        this.cursos.add(curso);
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }
}
