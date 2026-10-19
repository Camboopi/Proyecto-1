package uniandes.dpo.cds.modelo;

public class Fisioterapeuta extends Empleado {

    public Fisioterapeuta(String login, String password, String nombre, String codigoDescuento) {
        super(login, password, nombre, codigoDescuento);
    }

    public boolean disponibleEnTurno(TurnoSemanal t) {
        if (t == null) {
            return false;
        }
        return t.getEmpleadosAsignados().contains(this);
    }
}
