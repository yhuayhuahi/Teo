#ifndef COLA_H
#define COLA_H

#include <iostream>

template <class T>
class Nodo {
private:
    T valor;
    Nodo<T>* siguiente;

public:
    Nodo(T valor) {
        this->valor = valor;
        this->siguiente = nullptr;
    }
    
    void actualizarValor(T valor) {
        this->valor = valor;
    }
    
    T accederValor() {
        return valor;
    }
    
    Nodo<T>* accederCola() {
        return siguiente;
    }
    
    void insertarSiguiente(T nValor) {
        this->siguiente = new Nodo<T>(nValor);
    }
    
    // NUEVO MÉTODO: para modificar el siguiente desde Cola
    void establecerSiguiente(Nodo<T>* nuevoSiguiente) {
        this->siguiente = nuevoSiguiente;
    }
    
    void eliminarCola() {
        if (this->siguiente != nullptr) {
            delete this->siguiente;
            this->siguiente = nullptr;
        }
    }
};

template <class T>
class Cola {
private:
    Nodo<T>* cabeza;
    int cant;

public:
    Cola() {
        cabeza = nullptr;
        cant = 0;
    }
    
    ~Cola() {
        while (cabeza != nullptr) {
            Nodo<T>* temp = cabeza;
            cabeza = cabeza->accederCola();
            delete temp;
        }
    }
    
    int accederCantidad() {
        return cant;
    }
    
    Nodo<T>* buscarValor(T valor) {
        if (cabeza == nullptr) return nullptr;

        Nodo<T>* tempNodo = cabeza;
        while (tempNodo != nullptr) {
            if (tempNodo->accederValor() == valor) {
                return tempNodo;
            }
            tempNodo = tempNodo->accederCola();
        }
        return nullptr;
    }
    
    void insertarValor(T valor) {
        if (cabeza == nullptr) {
            cabeza = new Nodo<T>(valor);
            cant = 1;
            return;
        }

        Nodo<T>* tempNodo = cabeza;
        while (tempNodo->accederCola() != nullptr) {
            tempNodo = tempNodo->accederCola();
        }
        tempNodo->insertarSiguiente(valor);
        cant++;
    }
    
    void eliminarValor(T valor) {
        if (cabeza == nullptr) return;
        
        // Caso 1: Eliminar cabeza
        if (cabeza->accederValor() == valor) {
            Nodo<T>* aEliminar = cabeza;
            cabeza = cabeza->accederCola();
            delete aEliminar;
            cant--;
            return;
        }

        // Caso 2: Eliminar nodo intermedio o final
        Nodo<T>* tempNodo = cabeza;
        while (tempNodo->accederCola() != nullptr) {
            if (tempNodo->accederCola()->accederValor() == valor) {
                Nodo<T>* eliminar = tempNodo->accederCola();
                // CORRECCIÓN: usar el nuevo método en lugar de acceso directo
                tempNodo->establecerSiguiente(tempNodo->accederCola()->accederCola());
                delete eliminar;
                cant--;
                return;
            }
            tempNodo = tempNodo->accederCola();
        }
    }
    
    void mostrarCola() {
        if (cabeza == nullptr) {
            std::cout << "Cola vacía" << std::endl;
            return;
        }
        
        Nodo<T>* tempNodo = cabeza;
        std::cout << "Cola: ";
        while (tempNodo != nullptr) {
            std::cout << tempNodo->accederValor() << " -> ";
            tempNodo = tempNodo->accederCola();
        }
        std::cout << "NULL" << std::endl;
    }
};

#endif

