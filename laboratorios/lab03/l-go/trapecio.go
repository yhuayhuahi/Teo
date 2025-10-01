package main

import (
    "bufio"
    "fmt"
    "os"
    "strings"
    "sync"
    "github.com/Pramod-Devireddy/go-exprtk"
)

var (
    areaTotal   float64
    areaMutex   sync.Mutex
    funcionMat  string
)

func evaluarFuncion(expresion string, valorX float64) float64 {
    exprtkObj := exprtk.NewExprtk()
    defer exprtkObj.Delete()

    exprtkObj.SetExpression(expresion)
    exprtkObj.AddDoubleVariable("x")

    err := exprtkObj.CompileExpression()
    if err != nil {
        fmt.Printf("Error al compilar la funcion: '%s' - %s\n", expresion, err.Error())
        return 0.0
    }

    exprtkObj.SetDoubleVariableValue("x", valorX)
    return exprtkObj.GetEvaluatedValue()
}

func calcularAreaTrapecio(baseMenor, baseMayor, altura float64) float64 {
    return (baseMenor + baseMayor) * altura / 2.0
}

func calcularTrapecio(limiteA, limiteB float64, indiceHilo, totalHilos int, wg *sync.WaitGroup) {
    defer wg.Done()
    
    altura := (limiteB - limiteA) / float64(totalHilos)
    x0 := limiteA + float64(indiceHilo)*altura
    x1 := limiteA + float64(indiceHilo+1)*altura
    
    fX0 := evaluarFuncion(funcionMat, x0)
    fX1 := evaluarFuncion(funcionMat, x1)
    
    areaTrapecio := calcularAreaTrapecio(fX0, fX1, altura)
    
    areaMutex.Lock()
    areaTotal += areaTrapecio
    areaMutex.Unlock()
    
    fmt.Printf("=> Hilo %d: [%f, %f] -> Area = %f\n", indiceHilo, x0, x1, areaTrapecio)
}

func main() {
    fmt.Println("---------- CALCULADORA DE INTEGRALES CON HILOS NORMALES ----------")
    fmt.Println()
    
    fmt.Println("Ingrese la función f(x) a integrar:")
    fmt.Println("   Ejemplos:")
    fmt.Println("   - 2*x^2 + 3*x + 0.5")
    fmt.Println("   - sin(x)")
    fmt.Println("   - cos(x) + x^2")
    fmt.Println("   - exp(x)")
    fmt.Println("   - log(x)")
    fmt.Println("   - sqrt(x)")
    fmt.Println()
    fmt.Print("f(x) = ")
    
    scanner := bufio.NewScanner(os.Stdin)
    scanner.Scan()
    funcionMat = strings.TrimSpace(scanner.Text())
    
    if funcionMat == "" {
        fmt.Println("Error: No se ingreso ninguna funcion")
        return
    }
    
    fmt.Printf("Funcion ingresada: '%s'\n", funcionMat)
    
    // Validar funcion
    test1 := evaluarFuncion(funcionMat, 1.0)
    test2 := evaluarFuncion(funcionMat, 2.0)
    fmt.Printf("f(1) = %f, f(2) = %f\n", test1, test2)
    fmt.Println("Funcion valida")
    
    var limiteInferior, limiteSuperior float64
    var decimales, numeroHilos int
    
    fmt.Print("Limite inferior (a): ")
    fmt.Scanf("%f", &limiteInferior)
    fmt.Print("Limite superior (b): ")
    fmt.Scanf("%f", &limiteSuperior)
    fmt.Print("Numero de hilos (trapecios): ")
    fmt.Scanf("%d", &numeroHilos)
    fmt.Print("Decimales en el resultado: ")
    fmt.Scanf("%d", &decimales)
    fmt.Println()
    
    // Validaciones
    if limiteSuperior <= limiteInferior {
        fmt.Println("Error: El limite superior debe ser mayor al inferior")
        return
    }
    
    if numeroHilos <= 0 {
        fmt.Println("Error: El numero de hilos debe ser positivo")
        return
    }
    
    if numeroHilos > 1000 {
        fmt.Println("Error: Muchos hilos pueden afectar el rendimiento")
    }
    
    if decimales < 0 || decimales > 10 {
        fmt.Println("Error: El numero de decimales debe estar entre 0 y 10")
        return
    }
    
    fmt.Println("Iniciando calculo...")
    
    var wg sync.WaitGroup
    
    for i := 0; i < numeroHilos; i++ {
        wg.Add(1)
        go calcularTrapecio(limiteInferior, limiteSuperior, i, numeroHilos, &wg)
    }
    
    wg.Wait()
    
    fmt.Println()
    fmt.Println("RESULTADO FINAL:")
    fmt.Printf("   Area = %.*f\n", decimales, areaTotal)
    fmt.Println()
}