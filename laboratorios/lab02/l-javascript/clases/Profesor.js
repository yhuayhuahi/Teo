import { Persona } from "./Persona.js"

export class Profesor extends Persona {
  constructor(id, nombre, edad) {
    super(id, nombre, edad)
  }

  toString() {
    return `Profesor{id='${this.id}', nombre='${this.nombre}', edad=${this.edad}}`
  }
}