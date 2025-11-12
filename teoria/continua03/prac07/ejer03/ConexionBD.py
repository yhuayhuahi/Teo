import json
import os
import threading

class ConexionBD:
    _instancia = None
    _lock = threading.Lock()

    def __new__(cls):
        with cls._lock:
            if cls._instancia is None:
                cls._instancia = super().__new__(cls)
                cls._instancia._conectado = False
                cls._instancia._datos = {"usuarios": []}
                cls._instancia._archivo = "usuarios.json"
                print("Nueva conexión a la base de datos JSON creada.")
            else:
                print("Usando conexión existente.")
        return cls._instancia

    def conectar(self):
        if not self._conectado:
            if os.path.exists(self._archivo):
                with open(self._archivo, "r", encoding="utf-8") as f:
                    self._datos = json.load(f)
                print(f"Datos cargados desde {self._archivo}")
            else:
                self._datos = {"usuarios": []}
                print("No existe el archivo, se creará uno nuevo.")
            self._conectado = True
            print("Conectado a la base de datos JSON.")
        else:
            print("Ya está conectado.")

    def desconectar(self):
        if self._conectado:
            with open(self._archivo, "w", encoding="utf-8") as f:
                json.dump(self._datos, f, indent=4, ensure_ascii=False)
            self._conectado = False
            print(f"Datos guardados en {self._archivo}")
            print("Desconectado de la base de datos.")
        else:
            print("No hay conexión activa.")

    def insertar_usuario(self, id_usuario, nombre):
        if not self._conectado:
            print("No se puede insertar. No hay conexión.")
            return
        # Verificar si el usuario ya existe
        for u in self._datos["usuarios"]:
            if u["id"] == id_usuario:
                print(f"El ID {id_usuario} ya existe. No se insertó.")
                return
        self._datos["usuarios"].append({"id": id_usuario, "nombre": nombre})
        print(f"Usuario '{nombre}' insertado con ID {id_usuario}.")

    def listar_usuarios(self):
        if not self._conectado:
            print("No se puede listar. No hay conexión.")
            return
        print("\nLista de usuarios:")
        if not self._datos["usuarios"]:
            print("(sin usuarios)")
        for u in self._datos["usuarios"]:
            print(f" - ID {u['id']}: {u['nombre']}")

    def obtener_usuario(self, id_usuario):
        if not self._conectado:
            print("No se puede consultar. No hay conexión.")
            return None
        for u in self._datos["usuarios"]:
            if u["id"] == id_usuario:
                return u
        print(f"Usuario con ID {id_usuario} no encontrado.")
        return None


# === Ejemplo de uso ===
if __name__ == "__main__":
    # Primer acceso
    db1 = ConexionBD()
    db1.conectar()
    db1.insertar_usuario(1, "Ana")
    db1.insertar_usuario(2, "Luis")
    db1.listar_usuarios()
    db1.desconectar()

    # Segundo acceso (debe usar la misma instancia)
    print("\n--- Segundo acceso ---")
    db2 = ConexionBD()
    db2.conectar()
    db2.listar_usuarios()
    print("¿db1 y db2 son la misma instancia?", db1 is db2)
    db2.desconectar()
