package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Venta {
    protected String fecha;
    protected String rubroContable;
    protected Usuario comprador;
    protected double subtotal;
    protected double descuentoEmpleado;
    protected int puntosRedimidos;
    protected double valorTotal;
    protected int puntosFidelidadGenerados;
    protected List<DetalleVenta> detalles;

    public Venta(String fecha, String rubroContable, Usuario comprador) {
        this.fecha = fecha;
        this.rubroContable = rubroContable;
        this.comprador = comprador;
        this.detalles = new ArrayList<DetalleVenta>();
    }

    public void agregarDetalle(DetalleVenta det) {
        this.detalles.add(det);
        recalcularTotales();
    }

    public void aplicarCodigoDescuento(Empleado empleadoDuenoCodigo) {
        if (empleadoDuenoCodigo != null) {
            double porcentaje = empleadoDuenoCodigo.obtenerDescuento(comprador);
            this.descuentoEmpleado = this.subtotal * porcentaje;
            recalcularTotales();
        }
    }

    public int calcularPuntosFidelidad2Porciento() {
        if (comprador instanceof Socio) {
            return (int) (valorTotal * 0.02);
        }
        return 0;
    }

    public boolean redimirPuntosComoDescuento(int pts) {
        if (comprador instanceof Socio) {
            Socio s = (Socio) comprador;
            if (s.redimirPuntos(pts)) {
                this.puntosRedimidos = this.puntosRedimidos + pts;
                recalcularTotales();
                return true;
            }
        }
        return false;
    }

    public abstract void recalcularTotales();

    public void acreditarPuntosSocio() {
        if (comprador instanceof Socio) {
            this.puntosFidelidadGenerados = calcularPuntosFidelidad2Porciento();
            ((Socio) comprador).acumularPuntos2Porciento(valorTotal);
        }
    }

    public String getFecha() {
        return fecha;
    }

    public String getRubroContable() {
        return rubroContable;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDescuentoEmpleado() {
        return descuentoEmpleado;
    }

    public int getPuntosRedimidos() {
        return puntosRedimidos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public int getPuntosFidelidadGenerados() {
        return puntosFidelidadGenerados;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }
}
