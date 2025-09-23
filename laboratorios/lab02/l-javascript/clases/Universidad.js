import { Curso } from "./Curso.js"

export class Universidad {
  #nombre
  cursos = []

  constructor(nombre) {
    this.#nombre = nombre
  }

  get nombre() {
    return this.#nombre
  }
  set nombre(nombre) {
    this.#nombre = nombre
  }

  agregarCursos(curso) {
    if (curso instanceof Curso) {
      this.cursos.push(curso)
    }
  }
  get cursos() {
    return this.cursos
  }

  toString() {
    return `Universidad{nombre='${this.#nombre}', cursos=${this.cursos}}`
  }
}