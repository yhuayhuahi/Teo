# Ejercicio 02: Singleton Logger - Explicación de Implementación

## Objetivo del Ejercicio
Implementar un patrón Singleton para una clase Logger que registre mensajes en un archivo de texto (bitacora.log), garantizando que solo exista una instancia y que todos los módulos del programa utilicen el mismo archivo de log.

## Patrón Singleton
El patrón Singleton garantiza que una clase tenga una única instancia y proporciona un punto de acceso global a dicha instancia.

Características principales:
- Una sola instancia en toda la aplicación
- Acceso global a esa instancia
- La instancia se crea solo cuando se necesita

## Implementación

### Estructura de la Clase Logger

```typescript
class Logger {
    private static instancia: Logger;    // Variable estática para la única instancia
    private archivo: string;             // Ruta del archivo de log

    private constructor() { ... }        // Constructor privado
    public static getInstance(): Logger { ... } // Método para obtener la instancia
    public log(mensaje: string): void { ... }   // Método principal para logging
}
```

### Componentes Principales

**Constructor Privado**
- Evita crear instancias directamente con `new Logger()`
- Define la ruta del archivo de log
- Crea el archivo si no existe

**Método getInstance()**
- Comprueba si ya existe una instancia
- Crea la instancia solo la primera vez
- Siempre retorna la misma instancia

**Método log()**
- Genera timestamp con fecha y hora actual
- Escribe el mensaje con formato `[YYYY-MM-DD HH:mm:ss] mensaje`
- Maneja errores de escritura

## Pruebas Realizadas

Se verificó que:
1. Dos variables que llaman a getInstance() apuntan a la misma instancia
2. Logs desde diferentes módulos escriben al mismo archivo
3. El formato de timestamp es consistente
4. Los mensajes se persisten correctamente en el archivo

## Resultado

Archivo bitacora.log creado con formato:
```
=== INICIO DE BITÁCORA ===
[2025-11-12 20:13:52] Mensaje desde logger1 - Inicio de aplicación
[2025-11-12 20:13:52] Mensaje desde logger2 - Operación realizada
[2025-11-12 20:13:52] Log desde módulo 1 - Procesando datos
```

## Requisitos Cumplidos

- Solo existe una instancia de Logger
- Método log(mensaje) implementado con timestamp
- Archivo compartido entre todas las instancias
- Funciona desde múltiples puntos del programa

## Configuración Técnica

Dependencias:
- fs: Para operaciones de archivo
- path: Para manejo de rutas
- @types/node: Tipos TypeScript para Node.js

TypeScript configurado con:
- target: es2020
- module: commonjs
- Compilación en carpeta /build

## Métodos Adicionales

- leerLog(): Lee el contenido completo del archivo
- limpiarLog(): Reinicia el archivo de log
- getRutaArchivo(): Obtiene la ruta del archivo

## Comandos de Ejecución

```bash
# Ejecutar la prueba
npx ts-node src/test.ts

# Compilar TypeScript
tsc

# Ver contenido del log
type bitacora.log
```

## Conclusión

La implementación cumple exitosamente con el patrón Singleton, proporcionando una solución eficiente para logging centralizado donde una sola instancia maneja todas las operaciones de escritura al archivo de bitácora.