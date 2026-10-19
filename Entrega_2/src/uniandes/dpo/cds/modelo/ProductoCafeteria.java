package uniandes.dpo.cds.modelo;

public abstract class ProductoCafeteria {
    protected String nombre;
    protected double precio;

    public ProductoCafeteria(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public abstract boolean puedeDespacharseEn(Instalacion inst);
}
