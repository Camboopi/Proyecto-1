package uniandes.dpo.cds.consola;

import uniandes.dpo.cds.modelo.*;
import uniandes.dpo.cds.persistencia.ManejadorPersistencia;

public class DemoAdministracion {
    public static void main(String[] args) {
        System.out.println("=== DEMO 4: ADMINISTRACION, TURNOS, INVENTARIO Y REPORTES ===");

        ManejadorPersistencia persistencia = new ManejadorPersistencia("data");
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        persistencia.cargarTodo(club);

        Administrador admin = (Administrador) club.buscarUsuario("admin");
        Entrenador profeJuan = (Entrenador) club.buscarUsuario("profe_juan");
        Fisioterapeuta fisioLaura = (Fisioterapeuta) club.buscarUsuario("fisio_laura");

        TurnoSemanal turnoLunes = club.obtenerOCrearTurno("Lunes", "08:00-12:00");
        TurnoSemanal turnoMartes = club.obtenerOCrearTurno("Martes", "08:00-12:00");

        System.out.println("\n1. Solicitudes de cambio de turno (minimo 2 entrenadores y 1 fisioterapeuta):");
        SolicitudCambioTurno solFisioUnica = fisioLaura.solicitarCambioTurno(turnoLunes, turnoMartes);
        boolean aprobFisio = admin.aprobarCambioTurno(solFisioUnica);
        System.out.println(" - Cambio de turno de Laura (unica fisioterapeuta del Lunes): " + aprobFisio);

        SolicitudCambioTurno solEntrenadorOk = profeJuan.solicitarCambioTurno(turnoLunes, turnoMartes);
        boolean aprobEnt = admin.aprobarCambioTurno(solEntrenadorOk);
        System.out.println(" - Cambio de turno de Juan (quedando 2 entrenadores y 1 fisio el Lunes): " + aprobEnt);

        System.out.println("\n2. Movimiento de articulos entre ubicaciones de inventario y reabastecimiento:");
        UbicacionInventario bodega = club.getUbicacionesInventario().get(0);
        UbicacionInventario mostrador = club.getUbicacionesInventario().get(1);
        ArticuloTienda raqueta = club.buscarArticuloTienda("Raqueta de Tenis");

        System.out.println(" - Inicial -> Bodega: " + bodega.getCantidadesPorArticulo().get(raqueta) +
                           " | Mostrador: " + mostrador.getCantidadesPorArticulo().get(raqueta));
        admin.moverArticulosUbicacionInventario(bodega, mostrador, raqueta, 3);
        System.out.println(" - Despues de mover 3 al mostrador -> Bodega: " + bodega.getCantidadesPorArticulo().get(raqueta) +
                           " | Mostrador: " + mostrador.getCantidadesPorArticulo().get(raqueta));
        admin.reabastecerTienda(bodega, raqueta, 5);
        System.out.println(" - Stock total de raquetas tras reabastecer 5 unidades: " + raqueta.getCantidadDisponible());

        System.out.println("\n3. Aprobacion de sugerencias de empleados:");
        SugerenciaProducto sug = profeJuan.sugerirArticuloOSnack("Banda Elastica", "Articulo Tienda");
        club.getSugerencias().add(sug);
        admin.aprobarSugerenciaEmpleado(sug);
        System.out.println(" - Sugerencia '" + sug.getNombrePropuesto() + "' aprobada por administrador: " + sug.isAprobada());

        System.out.println("\n4. Reportes administrativos y deportivos:");
        Socio mateo = (Socio) club.buscarUsuario("socio_mateo");
        VentaTienda v = new VentaTienda("2026-10-25", mateo);
        v.agregarArticulo(raqueta, 1);
        club.registrarVenta(v);

        Instalacion canchaTenis = club.buscarInstalacion("Cancha de Tenis Principal");
        SesionEntrenamiento ses = profeJuan.planearSesionEntrenamiento("2026-10-25", "09:00", 60, canchaTenis);
        profeJuan.registrarAsistencia(ses, mateo, "Asistió");
        club.getSesionesEntrenamiento().add(ses);
        club.getPartidos().add(new PartidoInterno("2026-10-25", club.buscarEquipo("Halcones CDS"), club.buscarEquipo("Cóndores CDS")));

        ReporteAdministrativo rep = new ReporteAdministrativo("Tienda", "Mensual");
        System.out.println(" - " + rep.generarReporteVentasPorRubroYPeriodo(club.getVentas()));
        System.out.println(" - " + rep.generarReporteDeportivoAsistenciaYPartidos(club.getSesionesEntrenamiento(), club.getPartidos()));
        System.out.println(" - " + rep.generarReporteEvolucionRankings(club.getUsuarios()));
    }
}
