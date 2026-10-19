package uniandes.dpo.cds.modelo;

public class SugerenciaProducto {
    private Empleado empleadoProponente;
    private String nombrePropuesto;
    private String tipo;
    private boolean aprobada;

    public SugerenciaProducto(Empleado empleadoProponente, String nombrePropuesto, String tipo) {
        this.empleadoProponente = empleadoProponente;
        this.nombrePropuesto = nombrePropuesto;
        this.tipo = tipo;
        this.aprobada = false;
    }

    public void aprobarPorAdministrador() {
        this.aprobada = true;
    }

    public Empleado getEmpleadoProponente() {
        return empleadoProponente;
    }

    public String getNombrePropuesto() {
        return nombrePropuesto;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isAprobada() {
        return aprobada;
    }
}
