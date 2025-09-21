
export class Persona {
  #id;
  #nombre;
  #edad;

  constructor(id, nombre, edad) {
    this.#id = id
    this.#nombre = nombre
    this.#edad = edad
  }

  get id() {
    return this.#id
  }
  set id(id) {
    this.#id = id
  }

  get nombre() {
    return this.#nombre
  }
  set nombre(nombre) {
    this.#nombre = nombre
  }

  get edad() {
    return this.#edad
  }
  set edad(edad) {
    this.#edad = edad
  }
}
