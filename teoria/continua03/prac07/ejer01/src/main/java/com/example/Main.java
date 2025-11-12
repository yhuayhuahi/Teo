package com.example;

public class Main {
    public static void main(String[] args) {

        // Testing
        Configuracion c1 = Configuracion.getInstancia();
        Configuracion c2 = Configuracion.getInstancia();

        c1.setIdioma("Inglés");
        c1.setZonaHoraria("UTC+0");

        System.out.println("Configuración 1:");
        c1.mostrarConfiguracion();

        System.out.println("Configuración 2:");
        c2.mostrarConfiguracion();

        // Comprobación de que todos los objetos apunten al mismo
        if (c1 == c2){
            System.out.println("Tienen la misma instancia");
        } else {
            System.out.println("No tiene la misma instancia");
        }
    }
}
