package uniandes.dpo.cds.modelo;

public class EstadisticaTenisPadel extends EstadisticaJugadorPartido {
    private int setsGanados;
    private int gamesGanados;
    private int aces;
    private int erroresNoForzados;

    public EstadisticaTenisPadel(Partido partido, Socio socio, int setsGanados, int gamesGanados, int aces, int erroresNoForzados) {
        super(partido, socio);
        this.setsGanados = setsGanados;
        this.gamesGanados = gamesGanados;
        this.aces = aces;
        this.erroresNoForzados = erroresNoForzados;
    }

    public int getSetsGanados() {
        return setsGanados;
    }

    public int getGamesGanados() {
        return gamesGanados;
    }

    public int getAces() {
        return aces;
    }

    public int getErroresNoForzados() {
        return erroresNoForzados;
    }

    @Override
    public int obtenerValorDestacado() {
        return setsGanados;
    }
}
