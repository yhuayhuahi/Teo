import java.util.*;
import java.util.concurrent.atomic.DoubleAdder;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Trapecio {

    // Intervalo y función objetivo: f(x) = 2x^2 + 3x + 1/2
    static final double A = 2.0;
    static final double B = 20.0;

    static double f(double x) {
        return 2*x*x + 3*x + 0.5;
    }

    // Calcula el area por método del trapecio con N subintervalos
    //Se crea un hilo por cada trapecio.
    static double areaConN(int N) throws InterruptedException {
        final double h = (B - A) / N;
        DoubleAdder acumulador = new DoubleAdder();
        List<Thread> hilos = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            final int idx = i;
            Runnable tarea = () -> {
                double xi = A + idx * h;
                double xj = xi + h;
                // Área del trapecio i: h * (f(xi) + f(xj)) / 2
                double ai = h * (f(xi) + f(xj)) * 0.5;
                acumulador.add(ai);
            };
            Thread t = new Thread(tarea, "trapecio-" + i);
            hilos.add(t);
            t.start();
        }

        for (Thread t : hilos) t.join();
        return acumulador.doubleValue();
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántos decimales de precisión deseas? ");
        int decimales = sc.nextInt();
        if (decimales < 0) decimales = 0;
        if (decimales > 12) {
            System.out.println("Advertencia, se limitará a 12 decimales para evitar sobrecarga numerica");
            decimales = 12;
        }
        double tolerancia = 0.5 * Math.pow(10, -decimales);

        // Comenzamos con pocos trapecios y vamos refinando duplicando N en cada iteración
        int N = 4;
        double anterior = areaConN(N);
        int iter = 0;

        while (true) {
            int siguienteN = N * 2;

            // advertencia si se disparan demasiados hilos
            if (siguienteN > 200_000) {
                System.out.println("Se alcanzó un límite práctico de hilos (" + siguienteN + "). "
                        + "Deteniendo el refinamiento para evitar sobrecarga del sistema.");
                break;
            }

            double actual = areaConN(siguienteN);
            iter++;

            double diff = Math.abs(actual - anterior);

            if (diff < tolerancia) {
                N = siguienteN;
                anterior = actual;
                break;
            } else {
                N = siguienteN;
                anterior = actual;
            }
        }

        BigDecimal redondeado = new BigDecimal(anterior).setScale(decimales, RoundingMode.HALF_UP);

        System.out.println("\nRESULTADO:");
        System.out.println("Función: f(x) = 2x^2 + 3x + 1/2");
        System.out.println("Intervalo: [" + A + ", " + B + "]");
        System.out.println("Trapecios usados (N): " + N + " (1 hilo por trapecio)");
        System.out.println("Área aproximada (doble precisión): " + anterior);
        System.out.println("Área redondeada a " + decimales + " decimales: " + redondeado);
    }
}

