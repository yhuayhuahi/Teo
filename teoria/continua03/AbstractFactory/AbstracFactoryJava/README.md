# Patrón de Diseño: Abstract Factory (Fábrica Abstracta)

Este repositorio contiene un ejemplo de implementación del patrón de diseño creacional **Abstract Factory** en Java. El objetivo es proporcionar una interfaz para crear familias de objetos relacionados o dependientes sin especificar sus clases concretas.

## Concepto del Ejemplo

El ejemplo simula un sistema que necesita crear componentes de **Interfaz de Usuario (UI)**, específicamente **Botones** y **Ventanas**, para diferentes **Sistemas Operativos (SO)**, como **Windows** y **macOS**.

El patrón nos permite cambiar fácilmente la apariencia de la UI (cambiar de Windows a Mac) con solo cambiar la fábrica concreta utilizada, sin modificar el código del cliente (`Application`).

---

## Estructura del Patrón

La implementación sigue la estructura clásica del patrón Abstract Factory:

### 1. Interfaces de Productos Abstractos (`Abstract Products`)

Estas interfaces definen los métodos que deben tener todos los productos dentro de la familia.

| Elemento | Tipo | Descripción |
| :--- | :--- | :--- |
| `Boton` | `interface` | Producto abstracto A. |
| `Ventana` | `interface` | Producto abstracto B. |

### 2. Productos Concretos (`Concrete Products`)

Son las implementaciones específicas de los productos. Cada conjunto de productos concretos forma una **familia** (Windows o Mac).

#### **Familia Windows**
* `BotonWindows`
* `VentanaWindows`

#### **Familia macOS**
* `BotonMac`
* `VentanaMac`

> **Nota:** La clase `BotonMac` es la implementación correcta para macOS, a pesar de un posible error tipográfico en la lista inicial.

### 3. Interfaz de Fábrica Abstracta (`Abstract Factory`)

Declara un conjunto de métodos de creación que devuelven los productos abstractos.

* `interface UIFactory`
    * Métodos: `crearBoton()`, `crearVentana()`

### 4. Fábricas Concretas (`Concrete Factories`)

Cada fábrica concreta implementa la interfaz `UIFactory` y es responsable de crear una familia específica de productos.

* `class WindowsFactory`: Crea productos de estilo Windows.
* `class MacFactory`: Crea productos de estilo macOS.

---

## Uso del Patrón

### Clase Cliente (`Application`)

La clase cliente **depende únicamente de las interfaces abstractas** (`Boton`, `Ventana`, `UIFactory`). Esto es clave para el aislamiento.

### Clase `DemoAbstractFactory` (`Main`)

Es el punto de entrada que decide dinámicamente qué fábrica concreta utilizar, y pasa esa fábrica a la aplicación.
