package uniandes.dpo.cds.modelo;

public class Baloncesto extends DeporteConjunto {

    public Baloncesto() {
        super("Baloncesto", 5, false);
    }

    public boolean validarSinEmpate(int ptsA, int ptsB) {
        return ptsA != ptsB;
    }

    @Override
    public int otorgarPuntosLiga(boolean victoria, boolean empate) {
        if (victoria) {
            return 2;
        }
        return 0;
    }
}
