#include <iostream>
#include <string>

int invertir(int num);

int main() {
    std::cout << invertir(123) << std::endl;
    return 0;
}

int invertir(int num) {
    int unidad = num % 10;
    int descena = ((num % 100) - unidad) / 10;
    int centena = (num - (descena + unidad)) / 100;

    int numFinal = unidad * 100 + descena * 10 + centena;
    return numFinal;
}
