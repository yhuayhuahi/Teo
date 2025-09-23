import { Profesor } from "./Profesor.js"
import { Estudiante } from "./Estudiante.js";

export class Reporte {
  profesor

  constructor(profesor) {
    this.profesor = profesor instanceof Profesor ? profesor : null
  }

  // Dependencia con estudiante
  generarReporte(estudiante) {
    if (!(estudiante instanceof Estudiante)) {
      return "Reporte no disponible"
    }
    let reporte = `Reporte del Profesor: ${this.profesor.nombre}\n`
    reporte += `, acerca del Estudiante: ${estudiante.nombre}`
    return reporte
  }

  toString() {
    return `Reporte{profesor=${this.profesor}}`
  }
}