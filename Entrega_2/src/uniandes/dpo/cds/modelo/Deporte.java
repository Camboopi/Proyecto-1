package uniandes.dpo.cds.modelo;

public abstract class Deporte {
    protected String nombre;

    public Deporte(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract boolean esDeporteDeConjunto();
}
