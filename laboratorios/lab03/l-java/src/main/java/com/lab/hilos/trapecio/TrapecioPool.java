package com.lab.hilos.trapecio;

import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TrapecioPool {
    private static double areaTotalPool = 0.0;
    private static final Object areaMutexPool = new Object();
    private static String funcionMatematicaPool;

    public static double evaluarFuncionPool(String expresion, double valorX) {
        try {
            // Reemplazar x por el valor
            String exprConValor = expresion.replace("x", String.valueOf(valorX));
            
            Expression expr = new Expression(exprConValor);
            EvaluationValue result = expr.evaluate();
            
            return result.getNumberValue().doubleValue();
        } catch (Exception e) {
            System.out.println("Error al compilar la funcion: '" + expresion + "'");
            return 0.0;
        }
    }

    public static double calcularAreaTrapecioPool(double baseMenor, double baseMayor, double altura) {
        return (baseMenor + baseMayor) * altura / 2.0;
    }

    public static void calcularTrapecioPool(double limiteA, double limiteB, int indiceHilo, int totalHilos) {
        double altura = (limiteB - limiteA) / totalHilos;
        double x0 = limiteA + indiceHilo * altura;
        double x1 = limiteA + (indiceHilo + 1) * altura;

        double fX0 = evaluarFuncionPool(funcionMatematicaPool, x0);
        double fX1 = evaluarFuncionPool(funcionMatematicaPool, x1);

        double areaTrapecio = calcularAreaTrapecioPool(fX0, fX1, altura);

        synchronized (areaMutexPool) {
            areaTotalPool += areaTrapecio;
        }

        System.out.printf("=> Hilo %d: [%f, %f] -> Area = %f%n", indiceHilo, x0, x1, areaTrapecio);
    }

    public static void main(String[] args) {
        System.out.println("---------- CALCULADORA DE INTEGRALES CON POOL DE HILOS ----------");
        System.out.println();

        System.out.println("Ingrese la función f(x) a integrar:");
        System.out.println("   Ejemplos:");
        System.out.println("   - 2*x^2 + 3*x + 0.5");
        System.out.println("   - SIN(x)");
        System.out.println("   - COS(x) + x^2");
        System.out.println("   - EXP(x)");
        System.out.println("   - LOG(x)");
        System.out.println("   - SQRT(x)");
        System.out.println();
        System.out.print("f(x) = ");

        Scanner scanner = new Scanner(System.in);
        funcionMatematicaPool = scanner.nextLine().trim();

        if (funcionMatematicaPool.isEmpty()) {
            System.out.println("Error: No se ingreso ninguna funcion");
            return;
        }

        System.out.println("Funcion ingresada: '" + funcionMatematicaPool + "'");

        // validar funcion
        try {
            double test1 = evaluarFuncionPool(funcionMatematicaPool, 1.0);
            double test2 = evaluarFuncionPool(funcionMatematicaPool, 2.0);
            if (test1 != 0.0 || test2 != 0.0) {
                System.out.println("Funcion valida");
            }
        } catch (Exception e) {
            System.out.println("Error: La funcion no es valida");
            System.exit(1);
        }

        double limiteInferior, limiteSuperior;
        int decimales, numeroHilos;

        System.out.print("Limite inferior (a): ");
        limiteInferior = scanner.nextDouble();
        System.out.print("Limite superior (b): ");
        limiteSuperior = scanner.nextDouble();
        System.out.print("Numero de hilos (trapecios): ");
        numeroHilos = scanner.nextInt();
        System.out.print("Decimales en el resultado: ");
        decimales = scanner.nextInt();
        System.out.println();

        // validaciones
        if (limiteSuperior <= limiteInferior) {
            System.out.println("Error: El limite superior debe ser mayor al inferior");
            return;
        }

        if (numeroHilos <= 0) {
            System.out.println("Error: El numero de hilos debe ser positivo");
            return;
        }

        if (numeroHilos > 1000) {
            System.out.println("Error: Muchos hilos pueden afectar el rendimiento");
        }

        if (decimales < 0 || decimales > 10) {
            System.out.println("Error: El numero de decimales debe estar entre 0 y 10");
            return;
        }

        System.out.println("Iniciando calculo...");

        // Crear pool de hilos
        int poolSize = Math.min(numeroHilos, Runtime.getRuntime().availableProcessors());
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        // Enviar tareas al pool
        for (int i = 0; i < numeroHilos; i++) {
            final int indice = i;
            executor.submit(() -> calcularTrapecioPool(limiteInferior, limiteSuperior, indice, numeroHilos));
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println("RESULTADO FINAL:");
        System.out.printf("   Area = %.*f%n", decimales, areaTotalPool);
        System.out.println();

        scanner.close();
    }
}
