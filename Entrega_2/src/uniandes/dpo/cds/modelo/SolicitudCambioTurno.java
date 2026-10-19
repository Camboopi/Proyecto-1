package uniandes.dpo.cds.modelo;

public class SolicitudCambioTurno {
    private Empleado solicitante;
    private TurnoSemanal turnoOrigen;
    private TurnoSemanal turnoDestino;
    private boolean aprobada;

    public SolicitudCambioTurno(Empleado solicitante, TurnoSemanal turnoOrigen, TurnoSemanal turnoDestino) {
        this.solicitante = solicitante;
        this.turnoOrigen = turnoOrigen;
        this.turnoDestino = turnoDestino;
        this.aprobada = false;
    }

    public boolean mantieneMin2EntrenadoresY1Fisio() {
        int entrenadoresRestantes = 0;
        int fisiosRestantes = 0;
        for (int i = 0; i < turnoOrigen.getEmpleadosAsignados().size(); i++) {
            Empleado e = turnoOrigen.getEmpleadosAsignados().get(i);
            if (!e.getLogin().equals(solicitante.getLogin())) {
                if (e instanceof Entrenador) {
                    entrenadoresRestantes++;
                } else if (e instanceof Fisioterapeuta) {
                    fisiosRestantes++;
                }
            }
        }
        return entrenadoresRestantes >= 2 && fisiosRestantes >= 1;
    }

    public boolean aprobarSiCumpleQuorum() {
        if (!mantieneMin2EntrenadoresY1Fisio()) {
            this.aprobada = false;
            return false;
        }
        turnoOrigen.removerEmpleado(solicitante);
        turnoDestino.asignarEmpleado(solicitante);
        this.aprobada = true;
        return true;
    }

    public Empleado getSolicitante() {
        return solicitante;
    }

    public TurnoSemanal getTurnoOrigen() {
        return turnoOrigen;
    }

    public TurnoSemanal getTurnoDestino() {
        return turnoDestino;
    }

    public boolean isAprobada() {
        return aprobada;
    }
}
