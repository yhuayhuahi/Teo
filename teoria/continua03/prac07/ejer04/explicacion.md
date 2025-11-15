# Explicación del Patrón Singleton en mi_juego.py

## ¿Qué es el Patrón Singleton?

El patrón Singleton es un patrón de diseño que **garantiza que una clase tenga una sola instancia** en todo el programa y proporciona un punto de acceso global a esa instancia.

## Implementación en ControlJuego

En el juego, la clase `ControlJuego` usa el patrón Singleton para asegurar que **solo exista un control de juego** durante toda la ejecución.

### Componentes del Singleton:

```python
class ControlJuego:
    _instancia = None      # Variable de clase que guarda la única instancia
    _inicializado = False  # Evita reinicializar la instancia
```

### Método `__new__`:
- Se ejecuta **antes** que `__init__`
- Controla la creación de la instancia
- Si no existe una instancia (`_instancia is None`), crea una nueva
- Si ya existe, devuelve la misma instancia

```python
def __new__(cls):
    if cls._instancia is None:
        cls._instancia = super(ControlJuego, cls).__new__(cls)
    return cls._instancia
```

### Método `__init__`:
- Solo inicializa los datos **la primera vez**
- La bandera `_inicializado` evita reinicializar

```python
def __init__(self):
    if not self._inicializado:
        self.nivel_actual = 1
        self.puntaje = 0
        self.vidas = 3
        self._inicializado = True
```

## ¿Por qué usar Singleton aquí?

1. **Estado único del juego**: El nivel, puntaje y vidas deben ser consistentes en toda la aplicación
2. **Acceso global**: Cualquier clase (`Jugador`, `Enemigo`, `InterfazConsola`) puede acceder al mismo estado de juego
3. **Evita duplicación**: No queremos múltiples controles de juego con estados diferentes

## Ejemplo de uso:

```python
# Estas dos líneas crean/acceden a la MISMA instancia
control1 = ControlJuego()  # Primera vez: crea la instancia
control2 = ControlJuego()  # Siguientes veces: devuelve la misma instancia

# control1 y control2 son el mismo objeto
print(control1 is control2)  # True
```

## Ventajas en este contexto:

- Estado de juego centralizado
- Consistencia de datos entre clases
- Fácil acceso desde cualquier parte del código
- Evita conflictos de estado

## Resumen:

El Singleton en `ControlJuego` asegura que **todos los objetos del juego** (jugador, enemigos, interfaz) **compartan el mismo estado de juego**, manteniendo la coherencia y evitando inconsistencias.
