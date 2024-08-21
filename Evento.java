import java.io.Serializable;

public class Evento implements Serializable {
    private String deporte;
    private String fecha;
    private String hora;

    public Evento(String deporte, String fecha, String hora) {
        this.deporte = deporte;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getDeporte() {
        return deporte;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }
}
