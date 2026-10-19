package uniandes.dpo.cds.modelo;

public class MesaTenisDeMesa extends Instalacion {
    private int capacidadSencillos;
    private int capacidadDobles;
    private boolean esTechada;

    public MesaTenisDeMesa(String nombre, boolean esTechada) {
        super(nombre, 4);
        this.capacidadSencillos = 2;
        this.capacidadDobles = 4;
        this.esTechada = esTechada;
    }

    public boolean isEsTechada() {
        return esTechada;
    }

    @Override
    public String getNombreDisciplina() {
        return "Tenis de Mesa";
    }

    @Override
    public boolean admiteModalidadYJugadores(String mod, int cant) {
        if ("Sencillos".equalsIgnoreCase(mod)) {
            return cant == capacidadSencillos;
        }
        if ("Dobles".equalsIgnoreCase(mod)) {
            return cant == capacidadDobles;
        }
        return false;
    }

    @Override
    public boolean permiteBebidaCaliente() {
        return !esTechada;
    }
}
