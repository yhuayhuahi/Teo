import { Persona } from "./Persona.js"

export class Estudiante extends Persona {
  constructor(id, nombre, edad) {
    super(id, nombre, edad)
  }

  toString() {
    return `Estudiante{id='${this.id}', nombre='${this.nombre}', edad=${this.edad}}`
  }
}