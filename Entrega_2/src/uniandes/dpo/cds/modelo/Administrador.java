package uniandes.dpo.cds.modelo;

public class Administrador extends Usuario {

    public Administrador(String login, String password, String nombre) {
        super(login, password, nombre);
    }

    @Override
    public boolean puedeReservarUsoPersonal() {
        return false;
    }

    public boolean puedeInscribirseAPartidoOReservarUsoPersonal() {
        return false;
    }

    public void verEstadoInventarioInstalacionesYArticulos(ClubDeportivoSeneca club) {
        System.out.println("Instalaciones registradas: " + club.getInstalaciones().size());
        System.out.println("Articulos en tienda: " + club.getArticulosTienda().size());
    }

    public void verHistorialReservasPartidosEntrenamientos(ClubDeportivoSeneca club) {
        System.out.println("Reservas: " + club.getReservas().size());
        System.out.println("Partidos: " + club.getPartidos().size());
        System.out.println("Sesiones de entrenamiento: " + club.getSesionesEntrenamiento().size());
    }

    public Equipo crearODarBajaEquipoYJugador(ClubDeportivoSeneca club, String nombreEquipo, DeporteConjunto deporte, String categoria, int cupo) {
        return club.crearEquipo(nombreEquipo, deporte, categoria, cupo);
    }

    public boolean moverArticulosUbicacionInventario(UbicacionInventario origen, UbicacionInventario destino, ArticuloTienda art, int cant) {
        return origen.moverArticulosA(destino, art, cant);
    }

    public void reabastecerTienda(UbicacionInventario ubicacion, ArticuloTienda art, int cant) {
        ubicacion.reabastecerArticulo(art, cant);
    }

    public void aprobarSugerenciaEmpleado(SugerenciaProducto s) {
        s.aprobarPorAdministrador();
    }

    public boolean aprobarCambioTurno(SolicitudCambioTurno sol) {
        return sol.aprobarSiCumpleQuorum();
    }
}
