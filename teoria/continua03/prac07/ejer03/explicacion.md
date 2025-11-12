# Ejercicio 03 – Conexión simulada a base de datos (Singleton)

## Objetivos

* Comprender la estructura y propósito del patrón de diseño **Singleton**.
* Implementar una clase que controle un recurso global compartido (una conexión).
* Simular una conexión a una base de datos, asegurando que solo exista una instancia activa.
* Aplicar persistencia de datos mediante el uso de un archivo **JSON**.
* Proteger el acceso al recurso en escenarios concurrentes (multihilo).

---

## Concepto del patrón Singleton

El patrón **Singleton** garantiza que solo exista una instancia de una clase durante toda la ejecución del programa y que exista un punto de acceso global a esa instancia.

Se utiliza cuando un recurso debe ser único y compartido, como por ejemplo:

* Una conexión a base de datos.
* Un gestor de configuración o registro.
* Un controlador de hardware o sistema de archivos.

El Singleton asegura que todas las partes del sistema utilicen la misma instancia, evitando duplicidad, errores y consumo innecesario de recursos.

---

## Descripción del ejercicio

El ejercicio consiste en crear una clase denominada **ConexionBD** que simule una conexión a una base de datos. Esta clase debe cumplir con los siguientes requisitos:

1. **Única conexión activa (Singleton):**
   Si se intenta crear una nueva instancia, debe devolverse la ya existente.

2. **Simulación de base de datos en JSON:**
   En lugar de una base de datos real, se utiliza un archivo **usuarios.json** para almacenar los datos de forma persistente.

3. **Principales métodos a implementar:**

   * `conectar()`: simula la apertura de la conexión y carga los datos desde el archivo JSON.
   * `desconectar()`: simula el cierre de la conexión y guarda los datos en el archivo JSON.
   * `insertar_usuario(id, nombre)`: agrega un nuevo usuario al registro.
   * `listar_usuarios()`: muestra los usuarios almacenados.
   * `obtener_usuario(id)`: busca un usuario por su identificador.

4. **Protección ante concurrencia:**
   Se utiliza un mecanismo de bloqueo para evitar que varios hilos creen instancias simultáneamente, garantizando la seguridad del patrón Singleton.

---

## Descripción del funcionamiento

Al ejecutar el programa, se crea la primera instancia de la clase **ConexionBD**, que establece una “conexión” simulada con el archivo JSON.
Si el archivo no existe, se genera uno nuevo.
A través de los métodos definidos, se pueden insertar y listar usuarios, los cuales se guardan en el archivo al desconectarse.

En un segundo acceso, el programa utiliza la misma instancia del Singleton, carga los datos previamente guardados y demuestra que la información permanece intacta.
De esta forma se comprueba que tanto la conexión como los datos son compartidos por toda la aplicación.

---

## Archivo generado

El archivo **usuarios.json** resultante almacena la información en formato estructurado.
Por ejemplo:

```json
{
    "usuarios": [
        {
            "id": 1,
            "nombre": "Ana"
        },
        {
            "id": 2,
            "nombre": "Luis"
        }
    ]
}
```

Esto demuestra la persistencia de los datos simulados en la “base de datos”.

---

## Explicación de los elementos principales

| Elemento                          | Descripción                                                                                    |
| --------------------------------- | ---------------------------------------------------------------------------------------------- |
| **Método de creación controlada** | Solo se permite crear una instancia de la clase.                                               |
| **Mecanismo de bloqueo**          | Evita la creación simultánea de instancias en entornos con varios hilos.                       |
| **Archivo JSON**                  | Actúa como una base de datos simulada que almacena la información de forma persistente.        |
| **Métodos de gestión**            | Permiten conectar, insertar, listar, consultar y desconectar, imitando una base de datos real. |
| **Instancia compartida**          | Todas las variables que representen la conexión apuntan al mismo objeto Singleton.             |

---

## Conclusión

El ejercicio demuestra cómo el patrón **Singleton** permite manejar de forma controlada un recurso compartido, en este caso una conexión a una base de datos simulada con JSON.
La implementación asegura que solo exista una instancia activa, evita conflictos en entornos concurrentes y mantiene la coherencia de los datos almacenados.

Además, al incorporar un archivo JSON, se logra una simulación práctica de persistencia, acercando el ejemplo al comportamiento real de una conexión a base de datos en aplicaciones profesionales.