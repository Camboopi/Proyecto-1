package uniandes.dpo.cds.modelo;

public class EvaluacionDesempeno {
    private int nota;
    private String comentario;

    public EvaluacionDesempeno(int nota, String comentario) {
        this.nota = nota;
        this.comentario = comentario;
    }

    public boolean validarNota1A5() {
        return nota >= 1 && nota <= 5;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }
}
