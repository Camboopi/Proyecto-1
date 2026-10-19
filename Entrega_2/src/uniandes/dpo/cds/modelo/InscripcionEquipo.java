package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public class InscripcionEquipo {
    private Socio socio;
    private Equipo equipo;
    private String posicion;
    private int numeroCamisetaUnico;
    private List<SancionDisciplinaria> sanciones;

    public InscripcionEquipo(Socio socio, Equipo equipo, String posicion, int numeroCamisetaUnico) {
        this.socio = socio;
        this.equipo = equipo;
        this.posicion = posicion;
        this.numeroCamisetaUnico = numeroCamisetaUnico;
        this.sanciones = new ArrayList<SancionDisciplinaria>();
    }

    public Socio getSocio() {
        return socio;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public int getNumeroCamisetaUnico() {
        return numeroCamisetaUnico;
    }

    public List<SancionDisciplinaria> getSanciones() {
        return sanciones;
    }

    public void agregarSancion(SancionDisciplinaria s) {
        this.sanciones.add(s);
    }

    public boolean tieneSuspensionActiva() {
        for (int i = 0; i < sanciones.size(); i++) {
            if (sanciones.get(i).isSuspendidoSiguientePartido()) {
                return true;
            }
        }
        return false;
    }

    public void cumplirSuspensionesActivas() {
        for (int i = 0; i < sanciones.size(); i++) {
            if (sanciones.get(i).isSuspendidoSiguientePartido()) {
                sanciones.get(i).cumplirSuspensionSiguientePartido();
            }
        }
    }
}
