#include <iostream>
#include <thread>
#include <vector>
#include <cmath>
#include <mutex>
#include <string>
#include <iomanip>
#include "exprtk.hpp"

using namespace std;

double area_total = 0.0;
mutex area_mutex;
string funcion_matematica; // funcion que ingresa el usuario

double evaluarFuncion(const string& expresion, double valor_x) {
    // tabla de simbolos
    exprtk::symbol_table<double> tabla_simbolos;
    double x_var = valor_x;
    tabla_simbolos.add_variable("x", x_var);
    tabla_simbolos.add_constants(); // constantes como pi, e, etc.
    
    // expresion
    exprtk::expression<double> expr;
    expr.register_symbol_table(tabla_simbolos);
    
    exprtk::parser<double> parser;
    
    if (!parser.compile(expresion, expr)) {
        cout << "Error al compilar la funcion: '" << expresion << "'" << endl;
        return 0.0;
    }
    
    return expr.value();
}


// funcion para calcular el area de un trapecio
double calcularAreaTrapecio(double base_menor, double base_mayor, double altura) {
    return (base_menor + base_mayor) * altura / 2.0;
}

// funcion para calcular el area de un trapecio en un hilo
void calcularTrapecio(double limite_a, double limite_b, int indice_hilo, int total_hilos) {
    double altura = (limite_b - limite_a) / total_hilos;
    double x0 = limite_a + indice_hilo * altura;        // lado izquierdo
    double x1 = limite_a + (indice_hilo + 1) * altura;  // lado derecho
    
    // valores evaluados en los extremos
    double f_x0 = evaluarFuncion(funcion_matematica, x0);
    double f_x1 = evaluarFuncion(funcion_matematica, x1);
    
    double area_trapecio = calcularAreaTrapecio(f_x0, f_x1, altura);
    
    {
        lock_guard<mutex> lock(area_mutex);
        area_total += area_trapecio;
    }
    
    cout << "=> Hilo " << indice_hilo << ": [" << x0 << ", " << x1 << "] -> Area = " << area_trapecio << endl;
}

int main() {
    cout << "---------- CALCULADORA DE INTEGRALES CON HILOS NORMALES ----------" << endl;
    cout << endl;
    
    cout << "Ingrese la función f(x) a integrar:" << endl;
    cout << "   Ejemplos:" << endl;
    cout << "   - 2*x^2 + 3*x + 0.5" << endl;
    cout << "   - sin(x)" << endl;
    cout << "   - cos(x) + x^2" << endl;
    cout << "   - exp(x)" << endl;
    cout << "   - log(x)" << endl;
    cout << "   - sqrt(x)" << endl;
    cout << endl;
    cout << "f(x) = ";
    
    getline(cin, funcion_matematica);
    
    // eliminar espacios en blanco al inicio y final
    size_t start = funcion_matematica.find_first_not_of(" \t");
    if (start == string::npos) {
        cout << "Error: No se ingreso ninguna funcion" << endl;
        return 1;
    }
    size_t end = funcion_matematica.find_last_not_of(" \t");
    funcion_matematica = funcion_matematica.substr(start, end - start + 1);

    cout << "Funcion ingresada: '" << funcion_matematica << "'" << endl;

    // validamos la funcion 
    try {
        double test1 = evaluarFuncion(funcion_matematica, 1.0);
        double test2 = evaluarFuncion(funcion_matematica, 2.0);
        if (test1 != 0.0 || test2 != 0.0) {
            cout << "Funcion valida" << endl;
        }
    } catch (...) {
        cout << "Error: La funcion no es valida" << endl;
        exit(1);
    }
    
    // se piden los parametros de la integral
    double limite_inferior, limite_superior;
    int decimales, numero_hilos;
    
    cout << "Limite inferior (a): ";
    cin >> limite_inferior;
    cout << "Limite superior (b): ";
    cin >> limite_superior;
    cout << "Numero de hilos (trapecios): ";
    cin >> numero_hilos;
    cout << "Decimales en el resultado: ";
    cin >> decimales;
    cout << endl;
    
    // validaciones
    if (limite_superior <= limite_inferior) {
        cout << "Error: El limite superior debe ser mayor al inferior" << endl;
        return 1;
    }
    
    if (numero_hilos <= 0) {
        cout << "Error: El numero de hilos debe ser positivo" << endl;
        return 1;
    }
    
    if (numero_hilos > 1000) {
        cout << "Error: Muchos hilos pueden afectar el rendimiento" << endl;
    }

    if (decimales < 0 || decimales > 10) {
        cout << "Error: El numero de decimales debe estar entre 0 y 10" << endl;
        return 1;
    }
    
    cout << "Iniciando calculo..." << endl;
    
    // se crean y lanzan hilos
    vector<thread> hilos;
    hilos.reserve(numero_hilos);
    
    for (int i = 0; i < numero_hilos; i++) {
        hilos.emplace_back(calcularTrapecio, limite_inferior, limite_superior, i, numero_hilos);
    }
    
    // se espera a que terminen todos los hilos
    for (auto& hilo : hilos) {
        hilo.join();
    }

    cout << endl;
    cout << "RESULTADO FINAL:" << endl;
    cout << "   Area = " << area_total << endl;
    cout << endl;
    
    return 0;
}
