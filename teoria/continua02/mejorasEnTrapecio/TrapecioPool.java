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
            for (int i = ini; i < fin; i++) {
                res += 2 * f(a + i * h);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        double a = 2, b = 20;
        int n = 1000, T = 4;
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

        double suma = f(a) + f(b);
        for (Worker w : ws) {
            w.join();
            suma += w.res;
        }

        System.out.printf("Área (Java):" + (h / 2) * suma);
    }
}