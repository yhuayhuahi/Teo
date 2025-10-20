public class Trapecio{

    public static void main(String[] args) {
        // Límites
        double a = 2;
        double b = 20;
        int n = 4;  // Divisiones

        double h = (b - a) / n;  // ancho de cada intervalo

        // f(x) en los puntos a y b
        double suma = funcion(a) + funcion(b);

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            suma = suma + 2 * funcion(x);
        }

        double area = (h / 2) * suma;

        System.out.println("El área aproximada es: " + area);
    }

    //f(x) = 2x^2 + 3x + 0.5
    public static double funcion(double x) {
        return 2 * x * x + 3 * x + 0.5;
    }
}