import random

class ControlJuego:
    _instancia = None
    _inicializado = False

    def __new__(cls):
        if cls._instancia is None:
            cls._instancia = super(ControlJuego, cls).__new__(cls)
        return cls._instancia

    def __init__(self):
        if not self._inicializado:
            self.nivel_actual = 1
            self.puntaje = 0
            self.vidas = 3
            self._inicializado = True
        
    def mostrar_estado(self):
        print("\n--- Estado del Juego ---")
        print(f"Nivel Actual: {self.nivel_actual}")
        print(f"Puntaje: {self.puntaje}")
        print(f"Vidas: {self.vidas}")
        print("------------------------")

    def subir_nivel(self):
        self.nivel_actual += 1
        print(f"Subiste al nivel {self.nivel_actual}.")

    def sumar_puntaje(self, puntos):
        self.puntaje += puntos
        print(f"Ganaste {puntos} puntos. Puntaje total: {self.puntaje}")

    def perder_vida(self):
        self.vidas -= 1
        print(f"Perdiste una vida. Vidas restantes: {self.vidas}")
        if self.vidas <= 0:
            print("Juego Terminado.")
            exit()

class Jugador:
    def __init__(self, nombre):
        self.nombre = nombre
        self.control_juego = ControlJuego()
        self.salud_max = 100
        self.salud = self.salud_max

    def daño_base(self):
        # Daño crece con el nivel actual
        nivel = self.control_juego.nivel_actual
        return 10 + (nivel - 1) * 5

    def atacar_enemigo(self, enemigo):
        daño = random.randint(self.daño_base(), self.daño_base() + 10)
        print(f"{self.nombre} ataca a {enemigo.tipo} con {daño} de daño.")
        enemigo.recibir_daño(daño)
        if not enemigo.esta_vivo():
            self.control_juego.sumar_puntaje(enemigo.puntos_recompensa)
            print(f"{enemigo.tipo} ha sido derrotado.")

    def recibir_daño(self, daño):
        self.salud -= daño
        if self.salud < 0:
            self.salud = 0
        print(f"{self.nombre} recibe {daño} de daño. Salud restante: {self.salud}")
        if self.salud <= 0:
            print(f"{self.nombre} ha sido derrotado.")
            self.control_juego.perder_vida()
            self.salud = self.salud_max  # Revive con salud completa

    def mostrar_estado(self):
        print(f"\nHéroe: {self.nombre} | Salud: {self.salud}/{self.salud_max} | Daño: {self.daño_base()} - {self.daño_base()+10}")

    def esta_vivo(self):
        return self.salud > 0

class Enemigo:
    def __init__(self, tipo, salud_inicial, puntos_recompensa, daño_min=5, daño_max=15):
        self.tipo = tipo
        self.control_juego = ControlJuego()
        self.salud_max = salud_inicial
        self.salud = salud_inicial
        self.puntos_recompensa = puntos_recompensa
        self.daño_min = daño_min
        self.daño_max = daño_max

    def infligir_daño(self, jugador):
        daño = random.randint(self.daño_min, self.daño_max)
        print(f"El {self.tipo} inflige {daño} de daño a {jugador.nombre}.")
        jugador.recibir_daño(daño)

    def recibir_daño(self, daño):
        self.salud -= daño
        if self.salud < 0:
            self.salud = 0
        print(f"El {self.tipo} recibe {daño} de daño. Salud restante: {self.salud}")

    def esta_vivo(self):
        return self.salud > 0

    def mostrar_caracteristicas(self):
        print(f"Tipo: {self.tipo} | Salud: {self.salud}/{self.salud_max} | Daño: {self.daño_min}-{self.daño_max} | Recompensa: {self.puntos_recompensa}")

class InterfazConsola:
    def __init__(self):
        self.control_juego = ControlJuego()

    def actualizar_hud(self, jugador):
        self.control_juego.mostrar_estado()
        jugador.mostrar_estado()

def mostrar_enemigos_vivos(enemigos):
    print("\n--- Enemigos Vivos ---")
    for i, e in enumerate(enemigos):
        if e.esta_vivo():
            print(f"{i+1}. {e.tipo} (Salud: {e.salud}/{e.salud_max}, Daño: {e.daño_min}-{e.daño_max})")
    print("----------------------")

def todos_derrotados(enemigos):
    return all(not e.esta_vivo() for e in enemigos)

def iniciar_combate_multiple(jugador, enemigos, interfaz):
    print("\nComienza el combate múltiple.")
    print("Los enemigos aparecen en el campo de batalla:")
    for e in enemigos:
        e.mostrar_caracteristicas()

    while jugador.esta_vivo() and not todos_derrotados(enemigos):
        interfaz.actualizar_hud(jugador)
        mostrar_enemigos_vivos(enemigos)

        # Elegir enemigo a atacar
        eleccion = input("Elige el número del enemigo a atacar: ")
        if not eleccion.isdigit():
            print("Por favor, ingresa un número válido.")
            continue
        indice = int(eleccion) - 1
        if indice < 0 or indice >= len(enemigos) or not enemigos[indice].esta_vivo():
            print("Opción inválida. Intenta nuevamente.")
            continue

        jugador.atacar_enemigo(enemigos[indice])

        # Enemigos vivos contraatacan
        enemigos_vivos = [e for e in enemigos if e.esta_vivo()]
        for enemigo in enemigos_vivos:
            enemigo.infligir_daño(jugador)
            if not jugador.esta_vivo():
                break

    if jugador.esta_vivo():
        print("\nHas derrotado a todos los enemigos del nivel.")
    else:
        print("\nHas sido derrotado en el combate.")

if __name__ == "__main__":
    print("=== BIENVENIDO AL RPG DE CONSOLA ===")
    nombre = input("Ingresa el nombre de tu héroe: ")
    jugador = Jugador(nombre)
    interfaz = InterfazConsola()

    niveles = [
        [Enemigo("Orco", 50, 100, 8, 18)],
        [Enemigo("Goblin", 30, 50, 3, 10), Enemigo("Orco", 50, 100, 8, 18)],
        [Enemigo("Dragón", 150, 500, 20, 40), Enemigo("Goblin", 40, 60, 5, 12)]
    ]

    for i, enemigos in enumerate(niveles, start=1):
        if jugador.esta_vivo():
            print(f"\n--- Nivel {i} ---")
            iniciar_combate_multiple(jugador, enemigos, interfaz)
            if jugador.esta_vivo():
                jugador.control_juego.subir_nivel()
        else:
            break

    if jugador.esta_vivo():
        print("\nFelicidades, has completado todos los niveles.")
        interfaz.actualizar_hud(jugador)
    else:
        print("\nEl juego ha terminado.")
