package uniandes.dpo.cds.modelo;

public class PartidoInterno extends Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private String marcadorFinal;

    public PartidoInterno(String fecha, Equipo equipoLocal, Equipo equipoVisitante) {
        super(fecha);
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }

    public boolean validarMismaCategoriaYDeporte() {
        if (equipoLocal == null || equipoVisitante == null) {
            return false;
        }
        if (equipoLocal == equipoVisitante) {
            return false;
        }
        boolean mismoDeporte = equipoLocal.getDeporte().getNombre().equalsIgnoreCase(equipoVisitante.getDeporte().getNombre());
        boolean mismaCategoria = equipoLocal.getCategoria().equalsIgnoreCase(equipoVisitante.getCategoria());
        return mismoDeporte && mismaCategoria;
    }

    public boolean validarMinimoJugadoresHabilitados() {
        DeporteConjunto dep = equipoLocal.getDeporte();
        int habLocal = equipoLocal.obtenerJugadoresHabilitados().size();
        int habVis = equipoVisitante.obtenerJugadoresHabilitados().size();
        return dep.alcanzaMinimoJugadores(habLocal) && dep.alcanzaMinimoJugadores(habVis);
    }

    @Override
    public boolean puedeDisputarse() {
        return validarMismaCategoriaYDeporte() && validarMinimoJugadoresHabilitados();
    }

    public boolean registrarResultado(int ptsLocal, int ptsVisitante) {
        if (!puedeDisputarse()) {
            return false;
        }
        DeporteConjunto dep = equipoLocal.getDeporte();
        if (!dep.isPermiteEmpates() && ptsLocal == ptsVisitante) {
            return false;
        }
        for (int i = 0; i < equipoLocal.getNomina().size(); i++) {
            equipoLocal.getNomina().get(i).cumplirSuspensionesActivas();
        }
        for (int i = 0; i < equipoVisitante.getNomina().size(); i++) {
            equipoVisitante.getNomina().get(i).cumplirSuspensionesActivas();
        }
        this.marcadorFinal = ptsLocal + " - " + ptsVisitante;
        this.disputado = true;
        return true;
    }

    public void aplicarSuspensionPorTarjetas() {
        for (int i = 0; i < estadisticas.size(); i++) {
            EstadisticaJugadorPartido est = estadisticas.get(i);
            if (est instanceof EstadisticaFutbol) {
                EstadisticaFutbol ef = (EstadisticaFutbol) est;
                if (ef.generaSuspensionSiguientePartido()) {
                    InscripcionEquipo insc = equipoLocal.buscarInscripcion(ef.getSocio());
                    if (insc == null) {
                        insc = equipoVisitante.buscarInscripcion(ef.getSocio());
                    }
                    if (insc != null) {
                        String motivo = "2 Amarillas";
                        if (ef.getTarjetasRojas() >= 1) {
                            motivo = "1 Roja";
                        }
                        insc.agregarSancion(new SancionDisciplinaria(motivo));
                    }
                }
            }
        }
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public String getMarcadorFinal() {
        return marcadorFinal;
    }
}
