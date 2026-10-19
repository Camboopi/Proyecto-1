package uniandes.dpo.cds.modelo;

public class VentaTienda extends Venta {
    private double iva;

    public VentaTienda(String fecha, Usuario comprador) {
        super(fecha, "Tienda", comprador);
        this.iva = 0.19;
    }

    public boolean agregarArticulo(ArticuloTienda art, int cantidad) {
        if (art.getCantidadDisponible() < cantidad || cantidad <= 0) {
            return false;
        }
        art.descontarInventario(cantidad);
        agregarDetalle(new DetalleVenta(cantidad, art.getPrecio()));
        return true;
    }

    public double calcularTotalConIVA() {
        recalcularTotales();
        return valorTotal;
    }

    @Override
    public void recalcularTotales() {
        this.subtotal = 0;
        for (int i = 0; i < detalles.size(); i++) {
            this.subtotal = this.subtotal + detalles.get(i).calcularSubtotal();
        }
        double base = subtotal - descuentoEmpleado;
        if (base < 0) {
            base = 0;
        }
        double valorIVA = base * iva;
        this.valorTotal = base + valorIVA - puntosRedimidos;
        if (this.valorTotal < 0) {
            this.valorTotal = 0;
        }
    }

    public double getIva() {
        return iva;
    }
}
