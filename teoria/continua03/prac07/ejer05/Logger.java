package com.mycompany.to.lab;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static volatile Logger instancia; // volatile para evitar problemas de visibilidad entre hilos
    private static final Object lock = new Object(); // bloqueo para sincronización
    private PrintWriter writer;
    private static final String ARCHIVO_LOG = "bitacora.log";

    // Constructor privado: evita instanciación externa
    private Logger() {
        try {
            writer = new PrintWriter(new FileWriter(ARCHIVO_LOG, true)); // true = append
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstancia() {
        if (instancia == null) { // Primer chequeo (sin bloqueo)
            synchronized (lock) {
                if (instancia == null) { // Segundo chequeo (con bloqueo)
                    instancia = new Logger();
                }
            }
        }
        return instancia;
    }

    public void log(String mensaje) {
        synchronized (writer) { // evita que varios hilos escriban a la vez
            String tiempo = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.println("[" + tiempo + "] " + mensaje);
            writer.flush(); // asegura que se escriba inmediatamente
        }
    }

    public void cerrar() {
        if (writer != null) {
            writer.close();
        }
    }
}
