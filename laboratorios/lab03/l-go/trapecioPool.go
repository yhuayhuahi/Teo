package main

import (
    "bufio"
    "fmt"
    "os"
    "runtime"
    "strings"
    "sync"
    "github.com/Pramod-Devireddy/go-exprtk"
)

var (
    areaTotalPool float64
    areaMutexPool sync.Mutex
    funcionMatPool string
)

// ThreadPool estructura
type ThreadPool struct {
    tasks    chan func()
    wg       sync.WaitGroup
    numWorkers int
}

// NewThreadPool crea un nuevo pool de hilos
func NewThreadPool(numWorkers int) *ThreadPool {
    if numWorkers <= 0 {
        numWorkers = runtime.NumCPU()
    }
    
    pool := &ThreadPool{
        tasks:      make(chan func(), 100),
        numWorkers: numWorkers,
    }
    
    // Iniciar workers
    for i := 0; i < numWorkers; i++ {
        go pool.worker()
    }
    
    return pool
}

func (p *ThreadPool) worker() {
    for task := range p.tasks {
        task()
        p.wg.Done()
    }
}

func (p *ThreadPool) Submit(task func()) {
    p.wg.Add(1)
    p.tasks <- task
}

func (p *ThreadPool) Wait() {
    p.wg.Wait()
}

func (p *ThreadPool) Close() {
    close(p.tasks)
}

func evaluarFuncionPool(expresion string, valorX float64) float64 {
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

func calcularAreaTrapecioPool(baseMenor, baseMayor, altura float64) float64 {
    return (baseMenor + baseMayor) * altura / 2.0
}

func calcularTrapecioPool(limiteA, limiteB float64, indiceHilo, totalHilos int) {
    altura := (limiteB - limiteA) / float64(totalHilos)
    x0 := limiteA + float64(indiceHilo)*altura
    x1 := limiteA + float64(indiceHilo+1)*altura
    
    fX0 := evaluarFuncionPool(funcionMatPool, x0)
    fX1 := evaluarFuncionPool(funcionMatPool, x1)
    
    areaTrapecio := calcularAreaTrapecioPool(fX0, fX1, altura)
    
    areaMutexPool.Lock()
    areaTotalPool += areaTrapecio
    areaMutexPool.Unlock()
    
    fmt.Printf("=> Hilo %d: [%f, %f] -> Area = %f\n", indiceHilo, x0, x1, areaTrapecio)
}

func main() {
    fmt.Println("---------- CALCULADORA DE INTEGRALES CON POOL DE HILOS ----------")
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
    funcionMatPool = strings.TrimSpace(scanner.Text())
    
    if funcionMatPool == "" {
        fmt.Println("Error: No se ingreso ninguna funcion")
        return
    }
    
    fmt.Printf("Funcion ingresada: '%s'\n", funcionMatPool)
    
    // Validar funcion
    test1 := evaluarFuncionPool(funcionMatPool, 1.0)
    test2 := evaluarFuncionPool(funcionMatPool, 2.0)
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
    
    // se crea el pool de hilos
    poolSize := numeroHilos
    if poolSize > runtime.NumCPU() {
        poolSize = runtime.NumCPU()
    }
    pool := NewThreadPool(poolSize)
    
    // se agreagn tareas al pool
    for i := 0; i < numeroHilos; i++ {
        i := i 
        pool.Submit(func() {
            calcularTrapecioPool(limiteInferior, limiteSuperior, i, numeroHilos)
        })
    }
    
    pool.Wait()
    pool.Close()
    
    fmt.Println()
    fmt.Println("RESULTADO FINAL:")
    fmt.Printf("   Area = %.*f\n", decimales, areaTotalPool)
    fmt.Println()
}