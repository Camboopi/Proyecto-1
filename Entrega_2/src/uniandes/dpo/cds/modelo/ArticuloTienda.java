package uniandes.dpo.cds.modelo;

public class ArticuloTienda {
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidadDisponible;

    public ArticuloTienda(String nombre, String categoria, double precio, int cantidadDisponible) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

    public double calcularPrecioConIVA19() {
        return precio * 1.19;
    }

    public void descontarInventario(int cantidad) {
        this.cantidadDisponible = this.cantidadDisponible - cantidad;
    }

    public void incrementarInventario(int cantidad) {
        this.cantidadDisponible = this.cantidadDisponible + cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }
}
