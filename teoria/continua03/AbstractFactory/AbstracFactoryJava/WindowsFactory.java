class WindowsFactory implements UIFactory {
    @Override
    public Boton crearBoton() {
        return new BotonWindows();
    }

    @Override
    public Ventana crearVentana() {
        return new VentanaWindows();
    }
}