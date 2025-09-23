
export class Horario {
  dia;
  horaInicio;
  horaFin;

  constructor(dia, horaInicio, horaFin) {
    this.dia = dia;
    this.horaInicio = horaInicio;
    this.horaFin = horaFin;
  }

  get dia() {
    return this.dia;
  }

  get horaInicio() {
    return this.horaInicio;
  }

  get horaFin() {
    return this.horaFin;
  }

  set dia(dia) {
    this.dia = dia;
  }
  set horaInicio(horaInicio) {
    this.horaInicio = horaInicio;
  }
  set horaFin(horaFin) {
    this.horaFin = horaFin;
  }

  toString() {
    return `Horario{dia='${this.dia}', horaInicio='${this.horaInicio}', horaFin='${this.horaFin}'}`
  }
}