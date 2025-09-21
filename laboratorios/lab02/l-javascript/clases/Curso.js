import { Horario } from "./Horario.js";

export class Curso {
  #id
  #nombre
  #horario

  constructor(id, nombre, horario) {
    this.#id = id
    this.#nombre = nombre
    // Composicion: un curso tiene un horario
    this.#horario = horario instanceof Horario ? horario : null
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

  get horario() {
    return this.#horario
  }
  set horario(horario) {
    if (horario instanceof Horario) {
      this.#horario = horario
    }
  }
}