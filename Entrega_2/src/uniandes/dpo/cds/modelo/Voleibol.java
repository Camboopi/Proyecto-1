package uniandes.dpo.cds.modelo;

public class Voleibol extends DeporteConjunto {
    private boolean marcadorPorSets;

    public Voleibol() {
        super("Voleibol", 6, false);
        this.marcadorPorSets = true;
    }

    public boolean isMarcadorPorSets() {
        return marcadorPorSets;
    }

    public boolean validarGanadorPorSets(int sA, int sB) {
        return sA != sB;
    }

    @Override
    public int otorgarPuntosLiga(boolean victoria, boolean empate) {
        if (victoria) {
            return 2;
        }
        return 0;
    }
}
