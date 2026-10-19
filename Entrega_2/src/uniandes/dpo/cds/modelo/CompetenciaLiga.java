package uniandes.dpo.cds.modelo;

import java.util.HashMap;
import java.util.Map;

public class CompetenciaLiga extends Competencia {
    private String formato;
    private Map<String, Integer> tablaPosiciones;

    public CompetenciaLiga(String nombre, String anioTemporada, int nominaMinimaHabilitados) {
        super(nombre, "Torneo de los Cerros", anioTemporada, nominaMinimaHabilitados);
        this.formato = "Liga todos contra todos";
        this.tablaPosiciones = new HashMap<String, Integer>();
    }

    public void otorgarPuntosSegunDeporte(PartidoCompetencia p, boolean victoriaClub, boolean empate) {
        if (equipoInscrito == null) {
            return;
        }
        DeporteConjunto dep = equipoInscrito.getDeporte();
        int ptsClub = dep.otorgarPuntosLiga(victoriaClub, empate);
        int ptsRival = 0;
        if (empate) {
            ptsRival = dep.otorgarPuntosLiga(false, true);
        } else {
            ptsRival = dep.otorgarPuntosLiga(!victoriaClub, false);
        }

        int actualClub = 0;
        if (tablaPosiciones.containsKey(equipoInscrito.getNombre())) {
            actualClub = tablaPosiciones.get(equipoInscrito.getNombre());
        }
        tablaPosiciones.put(equipoInscrito.getNombre(), actualClub + ptsClub);

        int actualRival = 0;
        if (tablaPosiciones.containsKey(p.getInstitucionRival())) {
            actualRival = tablaPosiciones.get(p.getInstitucionRival());
        }
        tablaPosiciones.put(p.getInstitucionRival(), actualRival + ptsRival);
    }

    public Map<String, Integer> consultarTablaPosiciones() {
        return tablaPosiciones;
    }

    public String getFormato() {
        return formato;
    }
}
