package uniandes.dpo.cds.modelo;

public abstract class Instalacion {
    protected String nombre;
    protected int capacidadJugadores;

    public Instalacion(String nombre, int capacidadJugadores) {
        this.nombre = nombre;
        this.capacidadJugadores = capacidadJugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadJugadores() {
        return capacidadJugadores;
    }

    public abstract String getNombreDisciplina();

    public abstract boolean admiteModalidadYJugadores(String mod, int cant);

    public abstract boolean permiteBebidaCaliente();
}
