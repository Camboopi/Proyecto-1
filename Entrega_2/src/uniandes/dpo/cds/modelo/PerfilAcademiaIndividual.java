package uniandes.dpo.cds.modelo;

public class PerfilAcademiaIndividual {
    private Socio socio;
    private DisciplinaIndividual disciplina;
    private String nivel;
    private double puntajeRankingELO;

    public PerfilAcademiaIndividual(Socio socio, DisciplinaIndividual disciplina, String nivel, double puntajeRankingELO) {
        this.socio = socio;
        this.disciplina = disciplina;
        this.nivel = nivel;
        this.puntajeRankingELO = puntajeRankingELO;
    }

    public Socio getSocio() {
        return socio;
    }

    public DisciplinaIndividual getDisciplina() {
        return disciplina;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public double getPuntajeRankingELO() {
        return puntajeRankingELO;
    }

    public boolean esIgualOAdyacente(PerfilAcademiaIndividual otro) {
        if (otro == null) {
            return false;
        }
        return disciplina.nivelesIgualesOAdyacentes(this.nivel, otro.getNivel(), false);
    }

    public void actualizarPuntajeELO(double nuevoELO) {
        this.puntajeRankingELO = nuevoELO;
    }
}
