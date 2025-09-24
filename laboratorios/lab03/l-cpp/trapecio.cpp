#include <iostream>
#include <thread>
#include <vector>
#include <cmath>
#include <mutex>
using namespace std;

// area total
double area_total = 0.0;
mutex area_mutex;

// funcion que calcula el área de un trapecio
double calcularArea(double base_menor, double base_mayor, double altura) {
    return (base_menor + base_mayor) * altura / 2.0;
}

// funcion que ejecuta cada hilo para calcular un trapecio
void calcularTrapecio(double a, double b, int i, int n_hilos) {
    double altura = (b - a) / n_hilos;
    double x0 = a + i * altura; // Parte izquierda del trapecio
    double x1 = a + (i + 1) * altura; // Parte derecha del trapecio

    // funcion f(x) = 2x^2 + 3x + 1/2
    double base_menor = 2 * x0 * x0 + 3 * x0 + 0.5;
    double base_mayor = 2 * x1 * x1 + 3 * x1 + 0.5;

    double area = calcularArea(base_menor, base_mayor, altura);

    // mutex para proteger de la condicion de carrera
    lock_guard<mutex> lock(area_mutex);
    area_total += area;

    cout << "Hilo " << i << ": Area del trapecio = " << area << endl;
}

int main() {
    // Calculo de la integral por el metodo del trapecio
    // f(x) = 2x^2 + 3x + 1/2

    // pedimos la cantidad de decimales al usuario
    int decimales;
    cout << "Ingrese la cantidad de decimales: ";
    cin >> decimales;

    // limites
    double a = 2.0;
    double b = 20.0;

    int n_hilos = 8;

    // Vector de hilos
    vector<thread> hilos;

    // creamos e iniciamos los hilos
    for (int i = 0; i < n_hilos; i++) {
        hilos.emplace_back(calcularTrapecio, a, b, i, n_hilos);
    }

    // se espera a los hilos
    for (auto& hilo : hilos) {
        hilo.join();
    }
    cout << "Usando " << n_hilos << " hilos" << endl;
    cout << "El area es: " << area_total << endl;
    
    return 0;
}