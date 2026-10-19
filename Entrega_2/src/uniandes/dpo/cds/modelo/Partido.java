package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Partido {
    protected String fecha;
    protected boolean disputado;
    protected List<EstadisticaJugadorPartido> estadisticas;

    public Partido(String fecha) {
        this.fecha = fecha;
        this.disputado = false;
        this.estadisticas = new ArrayList<EstadisticaJugadorPartido>();
    }

    public abstract boolean puedeDisputarse();

    public void registrarEstadisticasIndividuales(EstadisticaJugadorPartido est) {
        this.estadisticas.add(est);
    }

    public String getFecha() {
        return fecha;
    }

    public boolean isDisputado() {
        return disputado;
    }

    public List<EstadisticaJugadorPartido> getEstadisticas() {
        return estadisticas;
    }
}
