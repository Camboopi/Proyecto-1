package uniandes.dpo.cds.modelo;

public class PartidoPracticaIndividual extends Partido {
    private DisciplinaIndividual disciplina;
    private ReservaInstalacion reserva;
    private Socio jugadorA;
    private Socio jugadorB;
    private String modalidad;
    private boolean participaEntrenador;

    public PartidoPracticaIndividual(String fecha, DisciplinaIndividual disciplina, ReservaInstalacion reserva,
                                     Socio jugadorA, Socio jugadorB, String modalidad, boolean participaEntrenador) {
        super(fecha);
        this.disciplina = disciplina;
        this.reserva = reserva;
        this.jugadorA = jugadorA;
        this.jugadorB = jugadorB;
        this.modalidad = modalidad;
        this.participaEntrenador = participaEntrenador;
    }

    public boolean validarNivelesIgualesOAdyacentes() {
        if (participaEntrenador) {
            return true;
        }
        PerfilAcademiaIndividual pA = jugadorA.buscarPerfil(disciplina.getNombre());
        PerfilAcademiaIndividual pB = jugadorB.buscarPerfil(disciplina.getNombre());
        if (pA == null || pB == null) {
            return false;
        }
        return disciplina.nivelesIgualesOAdyacentes(pA.getNivel(), pB.getNivel(), false);
    }

    @Override
    public boolean puedeDisputarse() {
        return validarNivelesIgualesOAdyacentes();
    }

    public boolean actualizarPuntajeRankingELO(boolean ganaJugadorA) {
        if (!puedeDisputarse()) {
            return false;
        }
        this.disputado = true;
        if (!participaEntrenador) {
            PerfilAcademiaIndividual pA = jugadorA.buscarPerfil(disciplina.getNombre());
            PerfilAcademiaIndividual pB = jugadorB.buscarPerfil(disciplina.getNombre());
            double eloA = pA.getPuntajeRankingELO();
            double eloB = pB.getPuntajeRankingELO();
            double resA = 0.0;
            double resB = 1.0;
            if (ganaJugadorA) {
                resA = 1.0;
                resB = 0.0;
            }
            double nuevoA = disciplina.actualizarRankingELO(eloA, eloB, resA);
            double nuevoB = disciplina.actualizarRankingELO(eloB, eloA, resB);
            pA.actualizarPuntajeELO(nuevoA);
            pB.actualizarPuntajeELO(nuevoB);
        }
        return true;
    }

    public DisciplinaIndividual getDisciplina() {
        return disciplina;
    }

    public ReservaInstalacion getReserva() {
        return reserva;
    }

    public String getModalidad() {
        return modalidad;
    }

    public boolean isParticipaEntrenador() {
        return participaEntrenador;
    }
}
