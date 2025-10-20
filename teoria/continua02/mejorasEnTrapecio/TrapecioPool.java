import java.util.Scanner;
import java.util.function.ToDoubleFunction;

public class TrapecioPool {
    static double f(double x) {
        return 2*x*x + 3*x + 0.5;
    }

    // Hilos de trabajo
    static class Worker extends Thread {
        double a, h;
        int ini, fin;
        double res = 0;

        Worker(double a, double h, int ini, int fin) {
            this.a = a;
            this.h = h;
            this.ini = ini;
            this.fin = fin;
        }

        public void run() {
            // Función inline para calcular suma parcial
            ToDoubleFunction<Integer> calcularSumaParcial = rango -> {
                double suma = 0;
                for (int i = ini; i < fin; i++) {
                    suma += 2 * f(a + i * h);
                }
                return suma;
            };
            
            res = calcularSumaParcial.applyAsDouble(fin - ini);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Entrada de datos con verificación simplificada
            double a = ValidadorEntradas.leerDouble(scanner, "Ingrese el límite inferior (a): ");
            double b = ValidadorEntradas.leerDouble(scanner, "Ingrese el límite superior (b): ");
            ValidadorEntradas.validarLimiteSuperior(b, a); // Validar que b > a
            int n = ValidadorEntradas.leerEnteroPositivo(scanner, "Ingrese el número de divisiones (n > 0): ");
            int T = ValidadorEntradas.leerEnteroPositivo(scanner, "Ingrese el número de hilos (T > 0): ");
            
            scanner.close();
            
            double h = (b - a) / n;
            Worker[] ws = new Worker[T];

            int bloque = n / T;
            for (int t = 0; t < T; t++) {
                int ini;
                int fin;

                if (t == 0) {
                    ini = 1; // empezamos en 1 porque f(a) ya se suma aparte
                } else {
                    ini = t * bloque;
                }

                if (t == T - 1) {
                    fin = n; // último hilo llega hasta n
                } else {
                    fin = (t + 1) * bloque;
                }

                ws[t] = new Worker(a, h, ini, fin);
                ws[t].start();
            }

            // Función inline para calcular el área final
            ToDoubleFunction<Double> calcularAreaFinal = sumaTotal -> (h / 2) * sumaTotal;

            double suma = f(a) + f(b);
            for (Worker w : ws) {
                w.join();
                suma += w.res;
            }

            double area = calcularAreaFinal.applyAsDouble(suma);
            System.out.printf("Área (Java): %.6f%n", area);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            scanner.close();
        }
    }
}