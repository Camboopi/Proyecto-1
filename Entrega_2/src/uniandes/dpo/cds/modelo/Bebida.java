package uniandes.dpo.cds.modelo;

public class Bebida extends ProductoCafeteria {
    private boolean esCaliente;

    public Bebida(String nombre, double precio, boolean esCaliente) {
        super(nombre, precio);
        this.esCaliente = esCaliente;
    }

    public boolean isEsCaliente() {
        return esCaliente;
    }

    public boolean prohibidaEnPadelOMesaTechada(Instalacion inst) {
        if (!esCaliente || inst == null) {
            return false;
        }
        return !inst.permiteBebidaCaliente();
    }

    @Override
    public boolean puedeDespacharseEn(Instalacion inst) {
        return !prohibidaEnPadelOMesaTechada(inst);
    }
}
