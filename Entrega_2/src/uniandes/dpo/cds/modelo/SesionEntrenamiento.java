package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public class SesionEntrenamiento {
    private String fecha;
    private String hora;
    private int duracion;
    private Entrenador entrenadorResponsable;
    private Instalacion instalacion;
    private List<Socio> convocados;
    private List<RegistroAsistencia> asistencias;

    public SesionEntrenamiento(String fecha, String hora, int duracion, Entrenador entrenadorResponsable, Instalacion instalacion) {
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.entrenadorResponsable = entrenadorResponsable;
        this.instalacion = instalacion;
        this.convocados = new ArrayList<Socio>();
        this.asistencias = new ArrayList<RegistroAsistencia>();
    }

    public void convocarEquipo(Equipo eq) {
        for (int i = 0; i < eq.getNomina().size(); i++) {
            Socio s = eq.getNomina().get(i).getSocio();
            if (!convocados.contains(s)) {
                convocados.add(s);
            }
        }
    }

    public void convocarSubconjuntoSocios(List<Socio> socios) {
        for (int i = 0; i < socios.size(); i++) {
            Socio s = socios.get(i);
            if (!convocados.contains(s)) {
                convocados.add(s);
            }
        }
    }

    public RegistroAsistencia registrarAsistenciaSocio(Socio socio, String estado) {
        if (!convocados.contains(socio)) {
            convocados.add(socio);
        }
        RegistroAsistencia reg = new RegistroAsistencia(socio, estado);
        asistencias.add(reg);
        return reg;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getDuracion() {
        return duracion;
    }

    public Entrenador getEntrenadorResponsable() {
        return entrenadorResponsable;
    }

    public Instalacion getInstalacion() {
        return instalacion;
    }

    public List<Socio> getConvocados() {
        return convocados;
    }

    public List<RegistroAsistencia> getAsistencias() {
        return asistencias;
    }
}
