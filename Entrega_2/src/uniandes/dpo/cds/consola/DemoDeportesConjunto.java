package uniandes.dpo.cds.consola;

import uniandes.dpo.cds.modelo.*;
import uniandes.dpo.cds.persistencia.ManejadorPersistencia;

import java.util.List;

public class DemoDeportesConjunto {
    public static void main(String[] args) {
        System.out.println("=== DEMO 1: DEPORTES DE CONJUNTO, PARTIDOS INTERNOS Y COMPETENCIAS ===");

        ManejadorPersistencia persistencia = new ManejadorPersistencia("data");
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        persistencia.cargarTodo(club);

        Equipo halcones = club.buscarEquipo("Halcones CDS");
        Equipo condores = club.buscarEquipo("Cóndores CDS");
        Equipo panterasVoley = club.buscarEquipo("Panteras CDS");

        System.out.println("\n1. Inscripcion de jugadores y validacion de reglas:");
        Socio mateo = (Socio) club.buscarUsuario("socio_mateo");
        boolean okInscripcion = halcones.inscribirJugador(mateo, "Delantero", 9);
        System.out.println(" - Inscribir a Mateo (16 anos, Sub-17) en Halcones CDS con camiseta 9: " + okInscripcion);

        boolean mismoDeporteOtroEquipo = condores.inscribirJugador(mateo, "Delantero", 19);
        System.out.println(" - Inscribir a Mateo en segundo equipo del mismo deporte (Condores CDS): " + mismoDeporteOtroEquipo);

        boolean otroDeporteSimultaneo = panterasVoley.inscribirJugador(mateo, "Opuesto", 9);
        System.out.println(" - Inscribir a Mateo en equipo de otro deporte (Voleibol Sub-17): " + otroDeporteSimultaneo);

        Socio adulto = new Socio("socio_carlos", "pass", "Carlos Mayor", 21);
        boolean edadIncorrecta = halcones.inscribirJugador(adulto, "Defensa", 4);
        System.out.println(" - Inscribir socio de 21 anos (Mayores) en Halcones Sub-17: " + edadIncorrecta);

        // Completar el minimo de 7 jugadores habilitados para Futbol en ambos equipos
        String[] posiciones = {"Portero", "Defensa", "Defensa", "Mediocampista", "Mediocampista", "Delantero"};
        for (int i = 1; i <= 6; i++) {
            Socio sLocal = new Socio("halcon_" + i, "123", "Jugador Halcon " + i, 16);
            halcones.inscribirJugador(sLocal, posiciones[i - 1], i);
        }
        for (int i = 1; i <= 7; i++) {
            Socio sVis = new Socio("condor_" + i, "123", "Jugador Condor " + i, 16);
            condores.inscribirJugador(sVis, posiciones[(i - 1) % posiciones.length], i + 10);
        }

        boolean camisetaRepetida = halcones.inscribirJugador(new Socio("extra", "123", "Extra", 16), "Defensa", 9);
        System.out.println(" - Inscribir jugador con camiseta 9 repetida en Halcones CDS: " + camisetaRepetida);

        System.out.println("\n2. Partido interno de Futbol, estadisticas y suspension por tarjetas:");
        System.out.println(" - Habilitados Halcones: " + halcones.obtenerJugadoresHabilitados().size() +
                           " | Condores: " + condores.obtenerJugadoresHabilitados().size());

        PartidoInterno partido1 = new PartidoInterno("2026-10-20", halcones, condores);
        EstadisticaFutbol estMateo = new EstadisticaFutbol(partido1, mateo, 2, 1, 2, 0);
        partido1.registrarEstadisticasIndividuales(estMateo);
        partido1.registrarResultado(2, 1);
        partido1.aplicarSuspensionPorTarjetas();

        System.out.println(" - Marcador final: " + halcones.getNombre() + " " + partido1.getMarcadorFinal() + " " + condores.getNombre());
        System.out.println(" - Estadistica Mateo: " + estMateo.getGoles() + " goles, " + estMateo.getAsistencias() +
                           " asistencias, " + estMateo.getTarjetasAmarillas() + " amarillas");
        System.out.println(" - Mateo suspendido para el siguiente partido: " + halcones.buscarInscripcion(mateo).tieneSuspensionActiva());
        System.out.println(" - Habilitados en Halcones para el siguiente partido: " + halcones.obtenerJugadoresHabilitados().size());

        PartidoInterno partido2 = new PartidoInterno("2026-10-27", halcones, condores);
        System.out.println(" - Puede jugarse el siguiente partido con 6 habilitados (minimo 7): " + partido2.puedeDisputarse());

        System.out.println("\n3. Competencias externas (Torneo de los Cerros y ASCUN):");
        Equipo titanesMayores = club.buscarEquipo("Titanes Mayores CDS");
        for (int i = 1; i <= 7; i++) {
            Socio sMay = new Socio("mayor_" + i, "123", "Titular Mayor " + i, 20);
            titanesMayores.inscribirJugador(sMay, "Mediocampista", 20 + i);
        }
        CompetenciaLiga cerros = new CompetenciaLiga("Torneo de los Cerros Futbol", "2026-II", 7);
        System.out.println(" - Equipo Sub-17 cumple requisitos de competencia externa: " + cerros.cumpleRequisitosInscripcion(halcones));

        List<Socio> nominaMayores = titanesMayores.obtenerJugadoresHabilitados();
        boolean inscMayoresOk = cerros.registrarNominaOficial(titanesMayores, nominaMayores);
        System.out.println(" - Inscripcion de Titanes Mayores CDS en Torneo de los Cerros: " + inscMayoresOk);

        PartidoCompetencia pCerros = new PartidoCompetencia(cerros, "2026-11-02", "Universidad Javeriana", "Cancha Principal CDS", nominaMayores);
        pCerros.registrarEstadisticasIndividuales(new EstadisticaFutbol(pCerros, nominaMayores.get(0), 3, 0, 0, 0));
        pCerros.registrarMarcador("3 - 1");
        cerros.agregarPartidoCompetencia(pCerros);
        cerros.otorgarPuntosSegunDeporte(pCerros, true, false);

        System.out.println(" - Tabla de posiciones Torneo de los Cerros: " + cerros.consultarTablaPosiciones());
        System.out.println(" - Goleador de la competencia: " + cerros.obtenerGoleadorODestacado().getNombre());

        CompetenciaFases ascun = new CompetenciaFases("Juegos Universitarios ASCUN", "2026-II", 7);
        ascun.registrarNominaOficial(titanesMayores, nominaMayores);
        ascun.avanzarPorPuntosYEnfrentamientoDirecto("Eliminacion Directa - Cuartos de Final");
        ascun.registrarResultadoEliminacionDirecta("Cuartos de Final", "Universidad Nacional", "2 - 0", true);
        System.out.println(" - Estado actual en ASCUN: " + ascun.getFaseActual());
    }
}
