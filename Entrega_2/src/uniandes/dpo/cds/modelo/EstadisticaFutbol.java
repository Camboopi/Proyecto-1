package uniandes.dpo.cds.modelo;

public class EstadisticaFutbol extends EstadisticaJugadorPartido {
    private int goles;
    private int asistencias;
    private int tarjetasAmarillas;
    private int tarjetasRojas;

    public EstadisticaFutbol(Partido partido, Socio socio, int goles, int asistencias, int tarjetasAmarillas, int tarjetasRojas) {
        super(partido, socio);
        this.goles = goles;
        this.asistencias = asistencias;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
    }

    public boolean generaSuspensionSiguientePartido() {
        if (tarjetasAmarillas >= 2 || tarjetasRojas >= 1) {
            return true;
        }
        return false;
    }

    public int getGoles() {
        return goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }

    @Override
    public int obtenerValorDestacado() {
        return goles;
    }
}
