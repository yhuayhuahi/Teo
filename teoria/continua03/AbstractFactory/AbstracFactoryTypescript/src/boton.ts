export interface Boton {
    //pintar(): void
    pintar: () => void
}

export class BotonMac implements Boton {
    pintar (): void {
        console.log("Pintando un Botón de estilo macOS.")
    }
}

export class BotonWindows implements Boton {
    pintar (): void {
        console.log("Pintando un Botón de estilo Windows.")
    }
}