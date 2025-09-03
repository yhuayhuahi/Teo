#include <iostream>
#include "student.cpp"

int main() {
    Student miEstudiante("Yourdyy Yossimar", "Huayhua Hillpa", "20224233");
    std::cout << "Hola mis nombres son " << miEstudiante.nombres << ", mis apellidos son "<< miEstudiante.apellidos << " y tengo asignado el siguiente CUI: " << miEstudiante.CUI << std::endl; 
    return 0;
}
