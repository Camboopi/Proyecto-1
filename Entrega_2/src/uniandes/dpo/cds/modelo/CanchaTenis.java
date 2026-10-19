package uniandes.dpo.cds.modelo;

public class CanchaTenis extends Instalacion {
    private int capacidadSencillos;
    private int capacidadDobles;

    public CanchaTenis(String nombre) {
        super(nombre, 4);
        this.capacidadSencillos = 2;
        this.capacidadDobles = 4;
    }

    @Override
    public String getNombreDisciplina() {
        return "Tenis";
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
        return true;
    }
}
