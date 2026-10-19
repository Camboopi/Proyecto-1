package uniandes.dpo.cds.modelo;

public class CompetenciaFases extends Competencia {
    private String faseActual;

    public CompetenciaFases(String nombre, String anioTemporada, int nominaMinimaHabilitados) {
        super(nombre, "ASCUN", anioTemporada, nominaMinimaHabilitados);
        this.faseActual = "Fase de Grupos";
    }

    public void avanzarPorPuntosYEnfrentamientoDirecto(String nuevaFase) {
        this.faseActual = nuevaFase;
    }

    public void registrarResultadoEliminacionDirecta(String fase, String rival, String marcador, boolean clasifica) {
        if (clasifica) {
            this.faseActual = fase + " superada (" + marcador + " vs " + rival + ")";
        } else {
            this.faseActual = "Eliminado en " + fase + " (" + marcador + " vs " + rival + ")";
        }
    }

    public String getFaseActual() {
        return faseActual;
    }
}
