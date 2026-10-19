package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class DisciplinaIndividual extends Deporte {
    protected int maxInstalacionesSimultaneas;
    protected List<String> niveles;

    public DisciplinaIndividual(String nombre, int maxInstalacionesSimultaneas) {
        super(nombre);
        this.maxInstalacionesSimultaneas = maxInstalacionesSimultaneas;
        this.niveles = new ArrayList<String>();
        this.niveles.add("Principiante");
        this.niveles.add("Intermedio");
        this.niveles.add("Avanzado");
    }

    public int getMaxInstalacionesSimultaneas() {
        return maxInstalacionesSimultaneas;
    }

    public List<String> getNiveles() {
        return niveles;
    }

    @Override
    public boolean esDeporteDeConjunto() {
        return false;
    }

    private int obtenerIndiceNivel(String nivel) {
        if ("Principiante".equalsIgnoreCase(nivel)) {
            return 0;
        }
        if ("Intermedio".equalsIgnoreCase(nivel)) {
            return 1;
        }
        if ("Avanzado".equalsIgnoreCase(nivel)) {
            return 2;
        }
        return -1;
    }

    public boolean nivelesIgualesOAdyacentes(String n1, String n2, boolean hayCoach) {
        if (hayCoach) {
            return true;
        }
        int i1 = obtenerIndiceNivel(n1);
        int i2 = obtenerIndiceNivel(n2);
        if (i1 == -1 || i2 == -1) {
            return false;
        }
        int diferencia = Math.abs(i1 - i2);
        return diferencia <= 1;
    }

    // Calculo de ranking ELO simplificado
    public double actualizarRankingELO(double eloA, double eloB, double res) {
        double k = 32.0;
        double esperado = 1.0 / (1.0 + Math.pow(10.0, (eloB - eloA) / 400.0));
        double nuevoElo = eloA + k * (res - esperado);
        return Math.round(nuevoElo);
    }
}
