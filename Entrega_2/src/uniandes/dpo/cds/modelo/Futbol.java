package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public class Futbol extends DeporteConjunto {
    private int puntosVictoria;
    private int puntosEmpate;
    private List<String> posiciones;

    public Futbol() {
        super("Fútbol", 7, true);
        this.puntosVictoria = 3;
        this.puntosEmpate = 1;
        this.posiciones = new ArrayList<String>();
        this.posiciones.add("Portero");
        this.posiciones.add("Defensa");
        this.posiciones.add("Mediocampista");
        this.posiciones.add("Delantero");
    }

    public List<String> getPosiciones() {
        return posiciones;
    }

    public boolean generaSuspension(int amarillas, int rojas) {
        if (amarillas >= 2 || rojas >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public int otorgarPuntosLiga(boolean victoria, boolean empate) {
        if (victoria) {
            return puntosVictoria;
        }
        if (empate) {
            return puntosEmpate;
        }
        return 0;
    }
}
