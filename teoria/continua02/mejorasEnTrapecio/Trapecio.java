import java.util.Scanner;
import java.util.function.ToDoubleFunction;

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

            // Función inline para calcular el área del trapecio
            ToDoubleFunction<Integer> calcularArea = divisiones -> {
                double h = (b - a) / divisiones;  // ancho de cada intervalo
                
                // f(x) en los puntos extremos
                double suma = funcion(a) + funcion(b);
                
                // Suma de los puntos intermedios
                for (int i = 1; i < divisiones; i++) {
                    double x = a + i * h;
                    suma += 2 * funcion(x);
                }
                
                return (h / 2) * suma;
            };

            double area = calcularArea.applyAsDouble(n);
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