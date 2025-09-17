import Composicion_y_Agregacion.*;

public class Main {
    public static void main(String[] args) {
        Horario horario = new Horario("Lunes", "08:00", "10:00");
        Curso curso = new Curso("CS101", "Introducción a la Programación", horario);

        System.out.println("Curso: " + curso.getNombre());
        System.out.println("Horario: " + curso.getHorario().getDia() + " de " + curso.getHorario().getHoraInicio() + " a " + curso.getHorario().getHoraFin());
    }
}
