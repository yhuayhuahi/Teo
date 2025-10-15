#include <iostream>
#include <vector>
#include <algorithm> // std::count_if
#include <cstdlib>   
#include <ctime>     

int main() {
    //para generar números aleatorios
    srand(static_cast<unsigned int>(time(nullptr)));

    // Generamos un vector de numeros enteros aleatorios
    std::vector<int> numeros;
    int n = 20;

    for (int i = 0; i < n; ++i) {
        numeros.push_back(rand() % 50); //números aleatorios entre 0 y 49
    }

    //mostramos los números generados
    std::cout << "Numeros generados a continuación: ";
    for (int num : numeros) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    // Contar cuántos son impares y menores de 20 ! usando count_if
    int conteo = std::count_if(numeros.begin(), numeros.end(), [](int x) {
        return (x % 2 != 0) && (x < 20);
    });

    std::cout << "Cantidad de numeros impares y menores de 20 encon: " << conteo << std::endl;

    return 0;
}

