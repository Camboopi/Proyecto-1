package uniandes.dpo.cds.modelo;

public class EstadisticaVoleibol extends EstadisticaJugadorPartido {
    private int aces;
    private int bloqueos;

    public EstadisticaVoleibol(Partido partido, Socio socio, int aces, int bloqueos) {
        super(partido, socio);
        this.aces = aces;
        this.bloqueos = bloqueos;
    }

    public int getAces() {
        return aces;
    }

    public int getBloqueos() {
        return bloqueos;
    }

    @Override
    public int obtenerValorDestacado() {
        return aces + bloqueos;
    }
}
