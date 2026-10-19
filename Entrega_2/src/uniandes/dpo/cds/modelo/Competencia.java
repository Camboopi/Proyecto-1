package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Competencia {
    protected String nombre;
    protected String entidadOrganizadora;
    protected String anioTemporada;
    protected String categoriaHabilitada;
    protected int nominaMinimaHabilitados;
    protected Equipo equipoInscrito;
    protected List<Socio> nominaOficial;
    protected List<PartidoCompetencia> programacion;

    public Competencia(String nombre, String entidadOrganizadora, String anioTemporada, int nominaMinimaHabilitados) {
        this.nombre = nombre;
        this.entidadOrganizadora = entidadOrganizadora;
        this.anioTemporada = anioTemporada;
        this.categoriaHabilitada = "Mayores";
        this.nominaMinimaHabilitados = nominaMinimaHabilitados;
        this.nominaOficial = new ArrayList<Socio>();
        this.programacion = new ArrayList<PartidoCompetencia>();
    }

    public boolean cumpleRequisitosInscripcion(Equipo eq) {
        if (eq == null) {
            return false;
        }
        if (!categoriaHabilitada.equalsIgnoreCase(eq.getCategoria())) {
            return false;
        }
        return eq.obtenerJugadoresHabilitados().size() >= nominaMinimaHabilitados;
    }

    public boolean registrarNominaOficial(Equipo eq, List<Socio> jugadores) {
        if (!cumpleRequisitosInscripcion(eq) || jugadores.size() < nominaMinimaHabilitados) {
            return false;
        }
        this.equipoInscrito = eq;
        this.nominaOficial = new ArrayList<Socio>(jugadores);
        return true;
    }

    public void agregarPartidoCompetencia(PartidoCompetencia p) {
        this.programacion.add(p);
    }

    public Socio obtenerGoleadorODestacado() {
        Map<Socio, Integer> acumulado = new HashMap<Socio, Integer>();
        Socio mejor = null;
        int maxVal = -1;
        for (int i = 0; i < programacion.size(); i++) {
            PartidoCompetencia p = programacion.get(i);
            for (int j = 0; j < p.getEstadisticas().size(); j++) {
                EstadisticaJugadorPartido est = p.getEstadisticas().get(j);
                Socio s = est.getSocio();
                int anterior = 0;
                if (acumulado.containsKey(s)) {
                    anterior = acumulado.get(s);
                }
                int total = anterior + est.obtenerValorDestacado();
                acumulado.put(s, total);
                if (total > maxVal) {
                    maxVal = total;
                    mejor = s;
                }
            }
        }
        return mejor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEntidadOrganizadora() {
        return entidadOrganizadora;
    }

    public String getAnioTemporada() {
        return anioTemporada;
    }

    public List<Socio> getNominaOficial() {
        return nominaOficial;
    }

    public List<PartidoCompetencia> getProgramacion() {
        return programacion;
    }
}
