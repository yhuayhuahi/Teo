#!/bin/bash

echo "Inicializando proyecto Go..."

# se crea go.mod si no existe
if [ ! -f "go.mod" ]; then
    go mod init trapecio
fi

# dependencias
go get github.com/Pramod-Devireddy/go-exprtk
go mod tidy

if [ $? -eq 0 ]; then
    echo "Dependencias instaladas correctamente"
    echo ""
    go run trapecio.go
    
    echo ""
    go run trapecioPool.go
else
    echo "Error al instalar dependencias"
fi