class MacFactory implements UIFactory {
    @Override
    public Boton crearBoton() {
        return new BotonMac();
    }

    @Override
    public Ventana crearVentana() {
        return new VentanaMac();
    }
}