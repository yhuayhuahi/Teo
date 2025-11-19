public class DemoAbstractFactory {
    
    // El código principal decide qué fábrica concreta usar
    private static void configurarAplicacion(String os) {
        UIFactory factory;
        
        if (os.equalsIgnoreCase("windows")) {
            factory = new WindowsFactory();
            System.out.println("Configurando la aplicación para Windows...");
        } else if (os.equalsIgnoreCase("mac")) {
            factory = new MacFactory();
            System.out.println("Configurando la aplicación para macOS...");
        } else {
            throw new IllegalArgumentException("SO no soportado: " + os);
        }

        Application app = new Application(factory);
        app.pintarUI();
    }

    public static void main(String[] args) {
        // Ejecución para Windows
        configurarAplicacion("Windows");
        
        // Ejecución para macOS
        configurarAplicacion("Mac");
    }
}