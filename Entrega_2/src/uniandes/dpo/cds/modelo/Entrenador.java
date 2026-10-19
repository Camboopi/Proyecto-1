package uniandes.dpo.cds.modelo;

public class Entrenador extends Empleado {

    public Entrenador(String login, String password, String nombre, String codigoDescuento) {
        super(login, password, nombre, codigoDescuento);
    }

    public SesionEntrenamiento planearSesionEntrenamiento(String fecha, String hora, int duracion, Instalacion instalacion) {
        return new SesionEntrenamiento(fecha, hora, duracion, this, instalacion);
    }

    public RegistroAsistencia registrarAsistencia(SesionEntrenamiento ses, Socio socio, String estado) {
        return ses.registrarAsistenciaSocio(socio, estado);
    }

    public void registrarEvaluacionOpcional(RegistroAsistencia asist, int nota, String com) {
        asist.agregarEvaluacionOpcional(nota, com);
    }

    public boolean puedeJugarConCualquierNivel() {
        return true;
    }
}
