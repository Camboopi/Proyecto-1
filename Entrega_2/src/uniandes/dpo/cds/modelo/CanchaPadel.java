package uniandes.dpo.cds.modelo;

public class CanchaPadel extends Instalacion {
    private int capacidadDoblesFija;
    private boolean soloModalidadDobles;

    public CanchaPadel(String nombre) {
        super(nombre, 4);
        this.capacidadDoblesFija = 4;
        this.soloModalidadDobles = true;
    }

    @Override
    public String getNombreDisciplina() {
        return "Pádel";
    }

    @Override
    public boolean admiteModalidadYJugadores(String mod, int cant) {
        if (soloModalidadDobles && "Dobles".equalsIgnoreCase(mod) && cant == capacidadDoblesFija) {
            return true;
        }
        return false;
    }

    @Override
    public boolean permiteBebidaCaliente() {
        return false;
    }
}
