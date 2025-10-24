#include <iostream>
#include "cola.h"

using namespace std;

int main() {
    // Probar con enteros
    Cola<int> cola;
    
    cola.insertarValor(10);
    cola.insertarValor(20);
    cola.insertarValor(30);
    
    cout << "Cantidad: " << cola.accederCantidad() << endl;
    cola.mostrarCola();
    
    // Buscar y eliminar
    cola.eliminarValor(20);
    cout << "Después de eliminar: " << cola.accederCantidad() << endl;
    cola.mostrarCola();
    
    return 0;
}
