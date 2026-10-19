package uniandes.dpo.cds.modelo;

public abstract class Empleado extends Usuario {
    protected String codigoDescuento;
    protected double descuentoPropio;
    protected double descuentoSocios;

    public Empleado(String login, String password, String nombre, String codigoDescuento) {
        super(login, password, nombre);
        this.codigoDescuento = codigoDescuento;
        this.descuentoPropio = 0.15;
        this.descuentoSocios = 0.08;
    }

    @Override
    public boolean puedeReservarUsoPersonal() {
        return true;
    }

    public String getCodigoDescuento() {
        return codigoDescuento;
    }

    public double obtenerDescuento(Usuario comprador) {
        if (comprador != null && comprador.getLogin().equals(this.login)) {
            return descuentoPropio;
        }
        return descuentoSocios;
    }

    public SugerenciaProducto sugerirArticuloOSnack(String nom, String tipo) {
        return new SugerenciaProducto(this, nom, tipo);
    }

    public SolicitudCambioTurno solicitarCambioTurno(TurnoSemanal act, TurnoSemanal nvo) {
        return new SolicitudCambioTurno(this, act, nvo);
    }
}
