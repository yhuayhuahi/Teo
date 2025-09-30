#!/bin/bash

echo "Construyendo ..."

if [ ! -d "build" ]; then
    mkdir build
fi

cd build

# Configurar y compilar
cmake .. && make

if [ $? -eq 0 ]; then
    echo "Compilación exitosa"
    echo ""
    ./trapecio
    
    echo ""
    ./trapecioPool
else
    echo "Error en la compilación"
fi
