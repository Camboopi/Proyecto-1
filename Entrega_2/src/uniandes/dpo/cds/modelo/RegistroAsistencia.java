package uniandes.dpo.cds.modelo;

public class RegistroAsistencia {
    private Socio socio;
    private String estado;
    private EvaluacionDesempeno evaluacion;

    public RegistroAsistencia(Socio socio, String estado) {
        this.socio = socio;
        this.estado = estado;
    }

    public void agregarEvaluacionOpcional(int nota, String com) {
        EvaluacionDesempeno eval = new EvaluacionDesempeno(nota, com);
        if (eval.validarNota1A5()) {
            this.evaluacion = eval;
        }
    }

    public Socio getSocio() {
        return socio;
    }

    public String getEstado() {
        return estado;
    }

    public EvaluacionDesempeno getEvaluacion() {
        return evaluacion;
    }
}
