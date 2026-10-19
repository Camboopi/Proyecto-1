package uniandes.dpo.cds.modelo;

public class EstadisticaBaloncesto extends EstadisticaJugadorPartido {
    private int puntos;
    private int rebotes;

    public EstadisticaBaloncesto(Partido partido, Socio socio, int puntos, int rebotes) {
        super(partido, socio);
        this.puntos = puntos;
        this.rebotes = rebotes;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getRebotes() {
        return rebotes;
    }

    @Override
    public int obtenerValorDestacado() {
        return puntos;
    }
}
