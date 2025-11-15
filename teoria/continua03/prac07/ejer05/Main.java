package com.mycompany.to.lab;

public class Main {

    // Simula una tarea que usa el Logger
    private static void tareaLogger(int id) {
        Logger logger = Logger.getInstancia();
        for (int i = 0; i < 3; i++) {
            logger.log("Mensaje desde hilo " + id + ", iteración " + i);
            try {
                Thread.sleep(100); // pequeña pausa para simular trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Prueba del Logger Singleton Thread-Safe en Java ===");

        // Crear múltiples hilos que acceden al mismo Logger
        Thread h1 = new Thread(() -> tareaLogger(1));
        Thread h2 = new Thread(() -> tareaLogger(2));
        Thread h3 = new Thread(() -> tareaLogger(3));
        Thread h4 = new Thread(() -> tareaLogger(4));

        // Iniciar hilos
        h1.start();
        h2.start();
        h3.start();
        h4.start();

        // Esperar a que terminen
        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Prueba finalizada. Revisa el archivo bitacora.log");
    }
}
