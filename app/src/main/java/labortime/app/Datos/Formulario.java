package labortime.app.Datos;

import android.widget.EditText;

public class Formulario {
    private String fecha;
    private String hora;
    private String turno;
    private String inicio;
    private String termino;

    public Formulario(String fecha, String hora, String turno, String inicio, String termino) {
        this.fecha = fecha;
        this.hora = hora;
        this.turno = turno;
        this.inicio = inicio;
        this.termino = termino;
    }

    public Formulario(EditText etFecha, EditText etHora, EditText etTurno, EditText etInicio, EditText etTermino) {

    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getTurno() {
        return turno;
    }

    public String getInicio() {
        return inicio;
    }

    public String getTermino() {
        return termino;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha + "\nHora: " + hora + "\nTurno: " + turno + "\nInicio: " + inicio + "\nTermino: " + termino;
    }
}
