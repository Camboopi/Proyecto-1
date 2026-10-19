package uniandes.dpo.cds.modelo;

import java.util.List;

public class ReservaInstalacion {
    private Socio socioResponsable;
    private Instalacion instalacion;
    private String bloqueHorario;
    private int cantidadJugadores;
    private String modalidad;

    public ReservaInstalacion(Socio socioResponsable, Instalacion instalacion, String bloqueHorario, int cantidadJugadores, String modalidad) {
        this.socioResponsable = socioResponsable;
        this.instalacion = instalacion;
        this.bloqueHorario = bloqueHorario;
        this.cantidadJugadores = cantidadJugadores;
        this.modalidad = modalidad;
    }

    public boolean hayCupoSimultaneoEnDisciplina(List<ReservaInstalacion> reservasExistentes, int maxSimultaneas) {
        int ocupadas = 0;
        for (int i = 0; i < reservasExistentes.size(); i++) {
            ReservaInstalacion r = reservasExistentes.get(i);
            if (r.getBloqueHorario().equalsIgnoreCase(this.bloqueHorario) &&
                r.getInstalacion().getNombreDisciplina().equalsIgnoreCase(instalacion.getNombreDisciplina())) {
                ocupadas++;
                if (r.getInstalacion().getNombre().equalsIgnoreCase(instalacion.getNombre())) {
                    return false;
                }
            }
        }
        return ocupadas < maxSimultaneas;
    }

    public boolean validarSinBebidaCaliente(Socio s) {
        if (s != null && s.isTieneBebidaCaliente() && !instalacion.permiteBebidaCaliente()) {
            return false;
        }
        return true;
    }

    public Socio getSocioResponsable() {
        return socioResponsable;
    }

    public Instalacion getInstalacion() {
        return instalacion;
    }

    public String getBloqueHorario() {
        return bloqueHorario;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public String getModalidad() {
        return modalidad;
    }
}
