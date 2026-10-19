package uniandes.dpo.cds.modelo;

public class EstadisticaTenisDeMesa extends EstadisticaJugadorPartido {
    private int setsGanados;
    private int puntosTotales;

    public EstadisticaTenisDeMesa(Partido partido, Socio socio, int setsGanados, int puntosTotales) {
        super(partido, socio);
        this.setsGanados = setsGanados;
        this.puntosTotales = puntosTotales;
    }

    public int getSetsGanados() {
        return setsGanados;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    @Override
    public int obtenerValorDestacado() {
        return puntosTotales;
    }
}
