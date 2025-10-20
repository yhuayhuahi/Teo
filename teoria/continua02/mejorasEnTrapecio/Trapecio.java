import java.util.Scanner;

public class Trapecio{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Entrada de datos con verificación simplificada
            double a = ValidadorEntradas.leerDouble(scanner, "Ingrese el límite inferior (a): ");
            double b = ValidadorEntradas.leerDouble(scanner, "Ingrese el límite superior (b): ");
            ValidadorEntradas.validarLimiteSuperior(b, a); // Validar que b > a
            int n = ValidadorEntradas.leerEnteroPositivo(scanner, "Ingrese el número de divisiones (n > 0): ");
            
            scanner.close();

            double h = (b - a) / n;  // ancho de cada intervalo

            // f(x) en los puntos a y b
            double suma = funcion(a) + funcion(b);

            for (int i = 1; i < n; i++) {
                double x = a + i * h;
                suma = suma + 2 * funcion(x);
            }

            double area = (h / 2) * suma;

            System.out.println("El área aproximada es: " + area);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            scanner.close();
        }
    }

    //f(x) = 2x^2 + 3x + 0.5
    public static double funcion(double x) {
        return 2 * x * x + 3 * x + 0.5;
    }
}