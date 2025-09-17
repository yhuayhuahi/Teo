package Composicion_y_Agregacion;

public class Curso {
    String id;
    String nombre;
    Horario horario;

    public Curso(String id, String nombre, Horario horario) {
        this.id = id;
        this.nombre = nombre;
        this.horario = horario;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
