import * as fs from 'fs';
import * as path from 'path';

class Logger {
    private static instancia: Logger;
    private archivo: string;

    // Constructor privado para evitar instanciación directa
    private constructor() {
        this.archivo = path.join(process.cwd(), 'bitacora.log');
        if (!fs.existsSync(this.archivo)) {
            fs.writeFileSync(this.archivo, '=== INICIO DE BITÁCORA ===\n', 'utf8');
        }
    }

    // Método estático para obtener la única instancia 
    public static getInstance(): Logger {
        if (!Logger.instancia) {
            Logger.instancia = new Logger();
        }
        return Logger.instancia;
    }

    // Metodo para escribir mensajes en el log
    public log(mensaje: string): void {
        const fecha = new Date();
        const timestamp = fecha.toISOString().replace('T', ' ').substring(0, 19);
        const linea = `[${timestamp}] ${mensaje}\n`;
        
        try {
            fs.appendFileSync(this.archivo, linea, 'utf8');
            console.log(`✓ Log registrado: ${mensaje}`);
        } catch (error) {
            console.error('Error al escribir en el log:', error);
        }
    }

    // Metodo adicional para leer el contenido del log
    public leerLog(): string {
        try {
            return fs.readFileSync(this.archivo, 'utf8');
        } catch (error) {
            console.error('Error al leer el log:', error);
            return '';
        }
    }

    // Metodo adicional para limpiar el log
    public limpiarLog(): void {
        try {
            fs.writeFileSync(this.archivo, '=== INICIO DE BITÁCORA ===\n', 'utf8');
            console.log('✓ Log limpiado');
        } catch (error) {
            console.error('Error al limpiar el log:', error);
        }
    }

    // Metodo para obtener la ruta del archivo de log
    public getRutaArchivo(): string {
        return this.archivo;
    }
}

export = Logger;