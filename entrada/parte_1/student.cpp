#include <iostream>

class Student {
public:
    std::string nombres;
    std::string apellidos;
    std::string CUI;

    Student(std::string nombres, std::string apellidos, std::string CUI) {
        this->nombres = nombres;
        this->apellidos = apellidos;
        this->CUI = CUI;
    }  
};
