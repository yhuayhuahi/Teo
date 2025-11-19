import { Boton, BotonMac, BotonWindows } from './boton'
import { Ventana, VentanaMac, VentanaWindows } from './ventana'

export interface UIFactory {
    crearBoton: () => Boton
    crearVentana: () => Ventana
}

export class MacFactory implements UIFactory {
    crearBoton () : Boton {
        return new BotonMac()
    }

    crearVentana () : Ventana {
        return new VentanaMac()
    }
}

export class WindowsFactory implements UIFactory {
    crearBoton () : Boton {
        return new BotonWindows()
    }

    crearVentana () : Ventana {
        return new VentanaWindows()
    }
}