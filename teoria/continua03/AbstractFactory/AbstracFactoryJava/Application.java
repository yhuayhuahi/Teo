class Application {
    private final Boton boton;
    private final Ventana ventana;

    public Application(UIFactory factory) {
        // La aplicación no sabe qué tipo de botón/ventana concreto está creando.
        // Solo sabe que son de la familia producida por 'factory'.
        this.boton = factory.crearBoton();
        this.ventana = factory.crearVentana();
    }

    public void pintarUI() {
        System.out.println("\n--- Creando UI ---");
        boton.pintar();
        ventana.mostrar();
    }
}