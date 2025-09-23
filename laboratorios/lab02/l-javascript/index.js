import { Profesor } from './clases/Profesor.js'
import { Estudiante } from './clases/Estudiante.js'
import { Curso } from './clases/Curso.js'
import { Horario } from './clases/Horario.js'
import { Universidad } from './clases/Universidad.js'
import { Reporte } from './clases/Reporte.js'

console.log('=== SISTEMA UNIVERSITARIO ===\n')

// 1. Crear 2 profesores
const profesor1 = new Profesor('29386481','Dr. García', 45)
const profesor2 = new Profesor('29386489','Dra. Martínez', 38)

console.log('Profesores creados:')
console.log('- ' + profesor1.nombre + ', ' + profesor1.edad + ' años')
console.log('- ' + profesor2.nombre + ', ' + profesor2.edad + ' años\n')

// 2. Crear 3 estudiantes
const estudiante1 = new Estudiante('12345678','Ana López', 20)
const estudiante2 = new Estudiante('87654321','Carlos Ruiz', 22)
const estudiante3 = new Estudiante('11223344','María González', 19)

console.log('Estudiantes creados:')
console.log('- ' + estudiante1.nombre + ', ' + estudiante1.edad + ' años')
console.log('- ' + estudiante2.nombre + ', ' + estudiante2.edad + ' años')
console.log('- ' + estudiante3.nombre + ', ' + estudiante3.edad + ' años\n')

// 3. Crear 2 cursos (cada uno con un horario)
const horario1 = new Horario('Lunes', '08:00', '10:00')
const curso1 = new Curso("CS101", "Introducción a la Programación", horario1)

const horario2 = new Horario('Miércoles', '14:00', '16:00')
const curso2 = new Curso("MAT201", "Matemáticas Discretas", horario2)

console.log('Cursos creados:')
console.log('- ' + curso1.nombre + ' (' + curso1.id + '), ' + curso1.horario.dia + ' ' + curso1.horario.horaInicio + '-' + curso1.horario.horaFin)
console.log('- ' + curso2.nombre + ' (' + curso2.id + '), ' + curso2.horario.dia + ' ' + curso2.horario.horaInicio + ' a ' + curso2.horario.horaFin + '\n')

// 4. Crear universidad y agregar los cursos
const universidad = new Universidad("Universidad Tecnológica Nacional")
universidad.agregarCursos(curso1)
universidad.agregarCursos(curso2)

console.log("Universidad: " + universidad.nombre)
console.log('Cursos agregados a la universidad:')
universidad.cursos.forEach(curso => {
  console.log('- ' + curso.nombre + ' (' + curso.id + ')')
})

console.log("\n");

// 5. Generar un reporte de un estudiante
const reporte = new Reporte(profesor1)
console.log("=== GENERACIÓN DE REPORTE ===")
console.log(reporte.generarReporte(estudiante1))

const reporte2 = new Reporte(profesor2)
console.log("\n=== GENERACIÓN DE REPORTE ===")
console.log(reporte2.generarReporte(estudiante2))

console.log("\n === FIN DEL PROGRAMA ===");