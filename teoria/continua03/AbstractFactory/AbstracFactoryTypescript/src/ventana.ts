export interface Ventana {
    mostrar: () => void
}

export class VentanaMac implements Ventana {
    mostrar(): void {
        console.log("Mostrando una Ventana de estilo macOS.")
    }
}

export class VentanaWindows implements Ventana {
    mostrar(): void {
        console.log("Mostrando una Ventana de estilo Windows.")
    }
}