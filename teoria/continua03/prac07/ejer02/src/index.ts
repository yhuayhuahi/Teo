import Logger = require('./Logger');

function pruebaLogger() {
    console.log('=== PRUEBA DEL SINGLETON LOGGER ===\n');

    // Obtener la primera instancia
    const logger1 = Logger.getInstance();
    console.log('Logger 1 obtenido');

    // Obtener la segunda instancia
    const logger2 = Logger.getInstance();
    console.log('Logger 2 obtenido');

    // Verificar que son la misma instancia
    console.log('¿Son la misma instancia?', logger1 === logger2);
    console.log('Archivo de log:', logger1.getRutaArchivo());
    console.log();

    // Probar el logging desde diferentes puntos
    logger1.log('Mensaje desde logger1 - Inicio de aplicación');
    logger2.log('Mensaje desde logger2 - Operación realizada');
    
    // Simular logs desde diferentes módulos
    logDesdeModulo1();
    logDesdeModulo2();

    // Leer y mostrar el contenido del log
    console.log('\n=== CONTENIDO DE LA BITÁCORA ===');
    console.log(logger1.leerLog());
}

function logDesdeModulo1() {
    const logger = Logger.getInstance();
    logger.log('Log desde módulo 1 - Procesando datos');
    logger.log('Log desde módulo 1 - Datos procesados correctamente');
}

function logDesdeModulo2() {
    const logger = Logger.getInstance();
    logger.log('Log desde módulo 2 - Conectando a base de datos');
    logger.log('Log desde módulo 2 - Conexión establecida');
}

// Ejecutar las pruebas
pruebaLogger();