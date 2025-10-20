import java.util.Scanner;
import java.util.InputMismatchException;

public class ValidadorEntradas {
    
    public static double leerDouble(Scanner scanner, String mensaje) {
        double valor = 0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            try {
                System.out.print(mensaje);
                valor = scanner.nextDouble();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.next();
            }
        }
        return valor;
    }
    
    public static int leerEnteroPositivo(Scanner scanner, String mensaje) {
        int valor = 0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            try {
                System.out.print(mensaje);
                valor = scanner.nextInt();
                
                if (valor <= 0) {
                    System.out.println("Error: Debe ser un número entero positivo.");
                    continue;
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
                scanner.next();
            }
        }
        return valor;
    }
    
    public static double validarLimiteSuperior(double b, double a) {
        if (b <= a) {
            throw new IllegalArgumentException("El límite superior (" + b + ") debe ser mayor que el inferior (" + a + ")");
        }
        return b;
    }
}