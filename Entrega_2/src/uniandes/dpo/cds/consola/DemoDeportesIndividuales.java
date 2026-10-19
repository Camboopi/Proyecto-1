package uniandes.dpo.cds.consola;

import uniandes.dpo.cds.modelo.*;
import uniandes.dpo.cds.persistencia.ManejadorPersistencia;

import java.util.ArrayList;
import java.util.List;

public class DemoDeportesIndividuales {
    public static void main(String[] args) {
        System.out.println("=== DEMO 2: DISCIPLINAS INDIVIDUALES, RESERVAS, ELO Y ENTRENAMIENTOS ===");

        ManejadorPersistencia persistencia = new ManejadorPersistencia("data");
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        persistencia.cargarTodo(club);

        Socio mateo = (Socio) club.buscarUsuario("socio_mateo");
        Socio valeria = (Socio) club.buscarUsuario("socio_valeria");
        Socio lucas = (Socio) club.buscarUsuario("socio_lucas");
        Entrenador profeMarta = (Entrenador) club.buscarUsuario("profe_marta");
        Administrador admin = (Administrador) club.buscarUsuario("admin");

        Instalacion canchaTenis = club.buscarInstalacion("Cancha de Tenis Principal");
        Instalacion canchaPadel = club.buscarInstalacion("Cancha de Pádel Central");
        Tenis tenis = (Tenis) club.buscarDeporte("Tenis");

        System.out.println("\n1. Reglas de reserva de instalaciones:");
        boolean resAdmin = club.registrarReserva(admin, canchaTenis, "10:00-11:00", 2, "Sencillos");
        System.out.println(" - Reserva solicitada por el Administrador para uso personal: " + resAdmin);

        boolean resPadelSencillos = club.registrarReserva(mateo, canchaPadel, "10:00-11:00", 2, "Sencillos");
        System.out.println(" - Reserva de cancha de Padel en Sencillos (2 jugadores): " + resPadelSencillos);

        boolean resPadelDobles = club.registrarReserva(mateo, canchaPadel, "10:00-11:00", 4, "Dobles");
        System.out.println(" - Reserva de cancha de Padel en Dobles (4 jugadores): " + resPadelDobles);

        boolean resTenisOk = club.registrarReserva(mateo, canchaTenis, "14:00-15:00", 2, "Sencillos");
        System.out.println(" - Reserva de cancha de Tenis en Sencillos (2 jugadores): " + resTenisOk);

        System.out.println("\n2. Partidos de practica, niveles adyacentes y ranking ELO:");
        ReservaInstalacion reservaTenis = club.getReservas().get(club.getReservas().size() - 1);

        PartidoPracticaIndividual cruceInvalido = new PartidoPracticaIndividual(
                "2026-10-21", tenis, reservaTenis, mateo, lucas, "Sencillos", false);
        System.out.println(" - Partido sin entrenador entre Avanzado y Principiante: " + cruceInvalido.puedeDisputarse());

        PartidoPracticaIndividual cruceConEntrenador = new PartidoPracticaIndividual(
                "2026-10-21", tenis, reservaTenis, mateo, lucas, "Dobles", true);
        System.out.println(" - Partido con Entrenador entre Avanzado y Principiante: " + cruceConEntrenador.puedeDisputarse());

        double eloMateoAntes = mateo.buscarPerfil("Tenis").getPuntajeRankingELO();
        double eloValeriaAntes = valeria.buscarPerfil("Tenis").getPuntajeRankingELO();
        PartidoPracticaIndividual partidoPrac = new PartidoPracticaIndividual(
                "2026-10-22", tenis, reservaTenis, mateo, valeria, "Sencillos", false);
        partidoPrac.registrarEstadisticasIndividuales(new EstadisticaTenisPadel(partidoPrac, mateo, 1, 8, 5, 3));
        partidoPrac.registrarEstadisticasIndividuales(new EstadisticaTenisPadel(partidoPrac, valeria, 2, 12, 2, 6));
        partidoPrac.actualizarPuntajeRankingELO(false);

        System.out.println(" - ELO antes del partido -> Mateo: " + eloMateoAntes + " | Valeria: " + eloValeriaAntes);
        System.out.println(" - ELO despues de ganar Valeria -> Mateo: " + mateo.buscarPerfil("Tenis").getPuntajeRankingELO() +
                           " | Valeria: " + valeria.buscarPerfil("Tenis").getPuntajeRankingELO());

        System.out.println("\n3. Sesion de entrenamiento, asistencia y evaluacion (1 a 5):");
        SesionEntrenamiento sesion = profeMarta.planearSesionEntrenamiento("2026-10-23", "16:00", 90, canchaTenis);
        List<Socio> grupo = new ArrayList<Socio>();
        grupo.add(mateo);
        grupo.add(valeria);
        grupo.add(lucas);
        sesion.convocarSubconjuntoSocios(grupo);
        club.getSesionesEntrenamiento().add(sesion);

        RegistroAsistencia regMateo = profeMarta.registrarAsistencia(sesion, mateo, "Asistió");
        profeMarta.registrarEvaluacionOpcional(regMateo, 5, "Buen servicio");
        RegistroAsistencia regValeria = profeMarta.registrarAsistencia(sesion, valeria, "Llegó tarde");
        profeMarta.registrarEvaluacionOpcional(regValeria, 4, "Buen reves");
        profeMarta.registrarAsistencia(sesion, lucas, "Faltó");

        for (int i = 0; i < sesion.getAsistencias().size(); i++) {
            RegistroAsistencia r = sesion.getAsistencias().get(i);
            String evalTxt = "Sin evaluacion";
            if (r.getEvaluacion() != null) {
                evalTxt = "Nota " + r.getEvaluacion().getNota() + "/5 (" + r.getEvaluacion().getComentario() + ")";
            }
            System.out.println(" - " + r.getSocio().getNombre() + ": " + r.getEstado() + " | " + evalTxt);
        }
    }
}
