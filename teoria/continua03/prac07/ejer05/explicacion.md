# Explicación del Patrón Singleton Thread-Safe en Java

## Análisis del Logger Singleton

Este proyecto implementa un **Logger Singleton thread-safe** que permite registrar mensajes en un archivo de log desde múltiples hilos de manera segura.

## Implementación del Patrón Singleton

### Clase Logger - Componentes Clave

#### 1. Variables de Instancia
```java
private static volatile Logger instancia; // volatile para visibilidad entre hilos
private static final Object lock = new Object(); // objeto para sincronización
private PrintWriter writer; // escritor del archivo
private static final String ARCHIVO_LOG = "bitacora.log"; // nombre del archivo
```

**¿Por qué `volatile`?**
- Garantiza que todos los hilos vean el valor más actualizado de `instancia`
- Previene problemas de visibilidad en entornos multi-hilo

#### 2. Constructor Privado
```java
private Logger() {
    try {
        writer = new PrintWriter(new FileWriter(ARCHIVO_LOG, true));
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

**Características:**
- **Privado**: Evita que se creen instancias desde fuera de la clase
- **Inicialización**: Configura el escritor de archivos en modo "append"

#### 3. Método getInstance() - Double-Checked Locking

```java
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
```

**Patrón Double-Checked Locking:**

1. **Primer chequeo**: Sin sincronización para mejor rendimiento
2. **Bloque sincronizado**: Solo se ejecuta si `instancia == null`
3. **Segundo chequeo**: Dentro del bloque sincronizado para evitar múltiples creaciones

**Ventajas:**
- Thread-safe (seguro para múltiples hilos)
- Eficiente (evita sincronización innecesaria después de la primera creación)
- Lazy initialization (se crea solo cuando se necesita)

#### 4. Método log() - Sincronización del Escritor

```java
public void log(String mensaje) {
    synchronized (writer) { // evita escrituras concurrentes
        String tiempo = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        writer.println("[" + tiempo + "] " + mensaje);
        writer.flush(); // asegura escritura inmediata
    }
}
```

**Características:**
- **Sincronizado en `writer`**: Evita que múltiples hilos escriban simultáneamente
- **Timestamp**: Cada mensaje incluye fecha y hora
- **Flush**: Garantiza que el mensaje se escriba inmediatamente al archivo

## Análisis del Main.java

### Simulación Multi-Hilo

El `Main.java` demuestra el uso seguro del Singleton en un entorno multi-hilo:

```java
private static void tareaLogger(int id) {
    Logger logger = Logger.getInstancia(); // Misma instancia para todos los hilos
    for (int i = 0; i < 3; i++) {
        logger.log("Mensaje desde hilo " + id + ", iteración " + i);
        Thread.sleep(100); // simula trabajo
    }
}
```

### Creación y Manejo de Hilos

```java
Thread h1 = new Thread(() -> tareaLogger(1));
Thread h2 = new Thread(() -> tareaLogger(2));
Thread h3 = new Thread(() -> tareaLogger(3));
Thread h4 = new Thread(() -> tareaLogger(4));

// Iniciar todos los hilos
h1.start(); h2.start(); h3.start(); h4.start();

// Esperar a que terminen
h1.join(); h2.join(); h3.join(); h4.join();
```

## Resultado en bitacora.log

El archivo de log muestra que:

```
[2025-11-12 15:00:25] Mensaje desde hilo 4, iteración 0
[2025-11-12 15:00:25] Mensaje desde hilo 3, iteración 0
[2025-11-12 15:00:25] Mensaje desde hilo 2, iteración 0
[2025-11-12 15:00:25] Mensaje desde hilo 1, iteración 0
```

**Observaciones:**
- **No hay corrupción de datos**: Cada línea es completa y legible
- **Orden variable**: Los hilos escriben en diferentes órdenes (comportamiento esperado)
- **Timestamps precisos**: Cada mensaje tiene su timestamp correcto
- **Integridad**: No hay mensajes parciales o mezclados

## Ventajas de esta Implementación

### 1. Thread Safety
- **Double-checked locking** para creación segura
- **Sincronización del writer** para escrituras seguras
- **Volatile** para visibilidad entre hilos

### 2. Eficiencia
- Lazy initialization (se crea solo cuando se usa)
- Mínima sincronización (solo cuando es necesario)
- Flush inmediato para persistencia


