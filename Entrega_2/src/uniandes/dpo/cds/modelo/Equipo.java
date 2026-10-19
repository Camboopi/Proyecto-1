package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private DeporteConjunto deporte;
    private String categoria;
    private int cupoMaximoJugadores;
    private List<InscripcionEquipo> nomina;

    public Equipo(String nombre, DeporteConjunto deporte, String categoria, int cupoMaximoJugadores) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.categoria = categoria;
        this.cupoMaximoJugadores = cupoMaximoJugadores;
        this.nomina = new ArrayList<InscripcionEquipo>();
    }

    public boolean camisetaDisponible(int camiseta) {
        for (int i = 0; i < nomina.size(); i++) {
            if (nomina.get(i).getNumeroCamisetaUnico() == camiseta) {
                return false;
            }
        }
        return true;
    }

    public boolean inscribirJugador(Socio s, String pos, int cam) {
        if (nomina.size() >= cupoMaximoJugadores) {
            return false;
        }
        if (!s.correspondeACategoria(this.categoria)) {
            return false;
        }
        if (s.perteneceAEquipoEnDeporte(this.deporte)) {
            return false;
        }
        if (!camisetaDisponible(cam)) {
            return false;
        }

        InscripcionEquipo insc = new InscripcionEquipo(s, this, pos, cam);
        nomina.add(insc);
        s.agregarInscripcionEquipo(insc);
        return true;
    }

    public List<Socio> obtenerJugadoresHabilitados() {
        List<Socio> habilitados = new ArrayList<Socio>();
        for (int i = 0; i < nomina.size(); i++) {
            InscripcionEquipo insc = nomina.get(i);
            if (!insc.tieneSuspensionActiva()) {
                habilitados.add(insc.getSocio());
            }
        }
        return habilitados;
    }

    public InscripcionEquipo buscarInscripcion(Socio s) {
        for (int i = 0; i < nomina.size(); i++) {
            InscripcionEquipo insc = nomina.get(i);
            if (insc.getSocio().getLogin().equals(s.getLogin())) {
                return insc;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public DeporteConjunto getDeporte() {
        return deporte;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCupoMaximoJugadores() {
        return cupoMaximoJugadores;
    }

    public List<InscripcionEquipo> getNomina() {
        return nomina;
    }
}
