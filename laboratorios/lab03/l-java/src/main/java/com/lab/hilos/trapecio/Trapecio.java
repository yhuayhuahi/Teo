package com.lab.hilos.trapecio;

import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

public class Trapecio {
    private static double areaTotal = 0.0;
    private static final Object areaMutex = new Object();
    private static String funcionMatematica;

    public static double evaluarFuncion(String expresion, double valorX) {
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

    public static double calcularAreaTrapecio(double baseMenor, double baseMayor, double altura) {
        return (baseMenor + baseMayor) * altura / 2.0;
    }

    public static void calcularTrapecio(double limiteA, double limiteB, int indiceHilo, int totalHilos, CountDownLatch latch) {
        try {
            double altura = (limiteB - limiteA) / totalHilos;
            double x0 = limiteA + indiceHilo * altura;
            double x1 = limiteA + (indiceHilo + 1) * altura;

            double fX0 = evaluarFuncion(funcionMatematica, x0);
            double fX1 = evaluarFuncion(funcionMatematica, x1);

            double areaTrapecio = calcularAreaTrapecio(fX0, fX1, altura);

            synchronized (areaMutex) {
                areaTotal += areaTrapecio;
            }

            System.out.printf("=> Hilo %d: [%f, %f] -> Area = %f%n", indiceHilo, x0, x1, areaTrapecio);
        } finally {
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        System.out.println("---------- CALCULADORA DE INTEGRALES CON HILOS NORMALES ----------");
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
        funcionMatematica = scanner.nextLine().trim();

        if (funcionMatematica.isEmpty()) {
            System.out.println("Error: No se ingreso ninguna funcion");
            return;
        }

        System.out.println("Funcion ingresada: '" + funcionMatematica + "'");

        // validar funcion
        try {
            double test1 = evaluarFuncion(funcionMatematica, 1.0);
            double test2 = evaluarFuncion(funcionMatematica, 2.0);
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

        CountDownLatch latch = new CountDownLatch(numeroHilos);

        for (int i = 0; i < numeroHilos; i++) {
            final int indice = i;
            new Thread(() -> calcularTrapecio(limiteInferior, limiteSuperior, indice, numeroHilos, latch)).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println("RESULTADO FINAL:");
        System.out.printf("   Area = %.*f%n", decimales, areaTotal);
        System.out.println();

        scanner.close();
    }
}
