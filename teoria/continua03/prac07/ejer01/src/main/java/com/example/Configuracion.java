package com.example;

public class Configuracion {
    // Atributo estático para guardar la única instancia
    private static Configuracion instancia;

    // Configuración
    private String idioma;
    private String zonaHoraria;

    // Constructor privado para evitar instanciación externa
    private Configuracion() {
        this.idioma = "Español";
        this.zonaHoraria = "UTC-5";
    }

    // Método estático para obtener la instancia
    public static Configuracion getInstancia() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    // Métodos para modificar la configuración
    public void mostrarConfiguracion() {
        System.out.println("Idioma: " + idioma + ", Zona horaria: " + zonaHoraria);
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }
}
