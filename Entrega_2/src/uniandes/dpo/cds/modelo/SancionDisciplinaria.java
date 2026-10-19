package uniandes.dpo.cds.modelo;

public class SancionDisciplinaria {
    private String motivo;
    private boolean suspendidoSiguientePartido;

    public SancionDisciplinaria(String motivo) {
        this.motivo = motivo;
        this.suspendidoSiguientePartido = true;
    }

    public boolean puedeSerAlineado() {
        return !suspendidoSiguientePartido;
    }

    public void cumplirSuspensionSiguientePartido() {
        this.suspendidoSiguientePartido = false;
    }

    public String getMotivo() {
        return motivo;
    }

    public boolean isSuspendidoSiguientePartido() {
        return suspendidoSiguientePartido;
    }
}
