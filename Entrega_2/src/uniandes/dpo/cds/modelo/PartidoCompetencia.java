package uniandes.dpo.cds.modelo;

import java.util.List;

public class PartidoCompetencia extends Partido {
    private Competencia competencia;
    private String institucionRival;
    private String escenario;
    private String marcadorFinal;
    private List<Socio> alineados;

    public PartidoCompetencia(Competencia competencia, String fecha, String institucionRival, String escenario, List<Socio> alineados) {
        super(fecha);
        this.competencia = competencia;
        this.institucionRival = institucionRival;
        this.escenario = escenario;
        this.alineados = alineados;
    }

    public boolean validarEnNominaCompetencia() {
        if (competencia == null) {
            return false;
        }
        return competencia.getNominaOficial().containsAll(alineados);
    }

    public boolean verificarSinSuspensionActiva(Equipo eq) {
        for (int i = 0; i < alineados.size(); i++) {
            Socio s = alineados.get(i);
            InscripcionEquipo insc = eq.buscarInscripcion(s);
            if (insc == null || insc.tieneSuspensionActiva()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean puedeDisputarse() {
        return validarEnNominaCompetencia() && alineados.size() >= competencia.nominaMinimaHabilitados;
    }

    public boolean registrarMarcador(String marcador) {
        if (!puedeDisputarse()) {
            return false;
        }
        this.marcadorFinal = marcador;
        this.disputado = true;
        return true;
    }

    public String getInstitucionRival() {
        return institucionRival;
    }

    public String getEscenario() {
        return escenario;
    }

    public String getMarcadorFinal() {
        return marcadorFinal;
    }
}
