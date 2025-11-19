import { UIFactory } from "./uifactory"
import { Boton } from './boton'
import { Ventana } from './ventana'

export class Aplication {
    private boton: Boton
    private ventana: Ventana

    constructor (factory: UIFactory) {
        this.boton = factory.crearBoton()
        this.ventana = factory.crearVentana()
    }

    pintarUI () {
        console.log('Creando UI :v')
        this.boton.pintar()
        this.ventana.mostrar()
    }
}