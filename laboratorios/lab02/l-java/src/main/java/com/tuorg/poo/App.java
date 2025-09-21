package com.tuorg.poo;
import com.tuorg.poo.clases.*;

public class App {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA UNIVERSITARIO ===\n");
        
        // 1. Crear 2 profesores
        Profesor profesor1 = new Profesor("29386481","Dr. García", 45);
        Profesor profesor2 = new Profesor("29386489","Dra. Martínez", 38);
        
        System.out.println("Profesores creados:");
        System.out.println("- " + profesor1.getNombre() + ", " + profesor1.getEdad() + " años");
        System.out.println("- " + profesor2.getNombre() + ", " + profesor2.getEdad() + " años\n");
        
        // 2. Crear 3 estudiantes
        Estudiante estudiante1 = new Estudiante("12345678","Ana López", 20);
        Estudiante estudiante2 = new Estudiante("87654321","Carlos Ruiz", 22);
        Estudiante estudiante3 = new Estudiante("11223344","María González", 19);

        System.out.println("Estudiantes creados:");
        System.out.println("- " + estudiante1.getNombre() + ", " + estudiante1.getEdad() + " años");
        System.out.println("- " + estudiante2.getNombre() + ", " + estudiante2.getEdad() + " años");
        System.out.println("- " + estudiante3.getNombre() + ", " + estudiante3.getEdad() + " años\n");
        
        // 3. Crear 2 cursos (cada uno con un horario)
        Horario horario1 = new Horario("Lunes", "08:00", "10:00");
        Curso curso1 = new Curso("CS101", "Programación I", horario1);
        
        Horario horario2 = new Horario("Miércoles", "14:00", "16:00");
        Curso curso2 = new Curso("MAT201", "Matemáticas Discretas", horario2);
        
        System.out.println("Cursos creados:");
        System.out.println("- " + curso1.getNombre() + " (" + curso1.getId() + ")");
        System.out.println("  Horario: " + curso1.getHorario().getDia() + " de " + 
                          curso1.getHorario().getHoraInicio() + " a " + curso1.getHorario().getHoraFin());
        System.out.println("- " + curso2.getNombre() + " (" + curso2.getId() + ")");
        System.out.println("  Horario: " + curso2.getHorario().getDia() + " de " + 
                          curso2.getHorario().getHoraInicio() + " a " + curso2.getHorario().getHoraFin() + "\n");
        
        // 4. Crear universidad y agregar los cursos
        Universidad universidad = new Universidad("Universidad Tecnológica Nacional");
        universidad.agregarCursos(curso1);
        universidad.agregarCursos(curso2);
        
        System.out.println("Universidad: " + universidad.getNombre());
        System.out.println("Cursos agregados a la universidad:");
        for (Curso curso : universidad.getCursos()) {
            System.out.println("- " + curso.getNombre() + " (" + curso.getId() + ")");
        }
        System.out.println();
        
        // 5. Generar un reporte de un estudiante
        Reporte reporte = new Reporte(profesor1);
        System.out.println("=== GENERACIÓN DE REPORTE ===");
        reporte.generarReporte(estudiante1);
        
        // Generar otro reporte para mostrar más funcionalidad
        Reporte reporte2 = new Reporte(profesor2);
        reporte2.generarReporte(estudiante2);
        
        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}