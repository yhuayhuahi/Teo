package com.lab.hilos;

import com.lab.hilos.trapecio.Trapecio;
import com.lab.hilos.trapecio.TrapecioPool;
import java.util.Scanner;

/**
 * Aplicación principal para el cálculo de integrales numéricas
 * usando el método del trapecio con diferentes enfoques de hilos.
 * 
 * @author Yourdyy Yossimar Huayhua Hillpa
 * @version 1.0
 */
public class App {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = scanner.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.println("EJECUTANDO: Método con Hilos Tradicionales");
                        Trapecio.main(new String[]{});
                        break;
                        
                    case 2:
                        System.out.println("EJECUTANDO: Método con Pool de Hilos (ExecutorService)");
                        TrapecioPool.main(new String[]{});
                        break;
                        
                    case 0:
                        System.out.println("\n¡Gracias por usar la aplicación!");
                        break;
                        
                    default:
                        System.out.println("\nOpción no válida. Intente nuevamente.");
                }
                
                if (opcion != 0) {
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine(); 
                    scanner.nextLine(); 
                }
                
            } catch (Exception e) {
                System.out.println("\nError: Ingrese un número válido.");
                scanner.nextLine(); 
                opcion = -1; 
            }
            
        } while (opcion != 0);
        
        scanner.close();
    }
    
    /**
     * Muestra el menú principal de opciones
     */
    private static void mostrarMenu() {
        System.out.println("│||||" + "MENU PRINCIPAL" + "│|||||");
        System.out.println("1.  Cálculo con Hilos Tradicionales");
        System.out.println("2.  Cálculo con Pool de Hilos");
        System.out.println("0.  Salir");
        System.out.println("───────────────────────────────");
    }
    

}
