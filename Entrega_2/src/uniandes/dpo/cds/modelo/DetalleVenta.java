package uniandes.dpo.cds.modelo;

public class DetalleVenta {
    private int cantidad;
    private double precioUnitario;
    private double subtotalRubro;

    public DetalleVenta(int cantidad, double precioUnitario) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotalRubro = cantidad * precioUnitario;
    }

    public double calcularSubtotal() {
        return subtotalRubro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }
}
