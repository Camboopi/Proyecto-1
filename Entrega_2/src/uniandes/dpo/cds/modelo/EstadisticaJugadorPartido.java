package uniandes.dpo.cds.modelo;

public abstract class EstadisticaJugadorPartido {
    protected Partido partido;
    protected Socio socio;

    public EstadisticaJugadorPartido(Partido partido, Socio socio) {
        this.partido = partido;
        this.socio = socio;
    }

    public void registrarEnPartido(Partido p, Socio s) {
        this.partido = p;
        this.socio = s;
        p.registrarEstadisticasIndividuales(this);
    }

    public Partido getPartido() {
        return partido;
    }

    public Socio getSocio() {
        return socio;
    }

    public abstract int obtenerValorDestacado();
}
