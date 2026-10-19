package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public class ClubDeportivoSeneca {
    private String nombre;
    private int minEntrenadoresPorTurno;
    private int minFisioterapeutasPorTurno;

    private List<Usuario> usuarios;
    private List<Deporte> deportes;
    private List<Equipo> equipos;
    private List<Instalacion> instalaciones;
    private List<ReservaInstalacion> reservas;
    private List<SesionEntrenamiento> sesionesEntrenamiento;
    private List<Partido> partidos;
    private List<Competencia> competencias;
    private List<ArticuloTienda> articulosTienda;
    private List<UbicacionInventario> ubicacionesInventario;
    private List<ProductoCafeteria> productosCafeteria;
    private List<Venta> ventas;
    private List<TurnoSemanal> turnos;
    private List<SugerenciaProducto> sugerencias;

    public ClubDeportivoSeneca() {
        this.nombre = "Club Deportivo Séneca";
        this.minEntrenadoresPorTurno = 2;
        this.minFisioterapeutasPorTurno = 1;
        this.usuarios = new ArrayList<Usuario>();
        this.deportes = new ArrayList<Deporte>();
        this.equipos = new ArrayList<Equipo>();
        this.instalaciones = new ArrayList<Instalacion>();
        this.reservas = new ArrayList<ReservaInstalacion>();
        this.sesionesEntrenamiento = new ArrayList<SesionEntrenamiento>();
        this.partidos = new ArrayList<Partido>();
        this.competencias = new ArrayList<Competencia>();
        this.articulosTienda = new ArrayList<ArticuloTienda>();
        this.ubicacionesInventario = new ArrayList<UbicacionInventario>();
        this.productosCafeteria = new ArrayList<ProductoCafeteria>();
        this.ventas = new ArrayList<Venta>();
        this.turnos = new ArrayList<TurnoSemanal>();
        this.sugerencias = new ArrayList<SugerenciaProducto>();
    }

    public Usuario autenticarUsuario(String login, String pass) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getLogin().equalsIgnoreCase(login) && u.autenticar(pass)) {
                return u;
            }
        }
        return null;
    }

    public boolean verificarDisponibilidadTurnos() {
        for (int i = 0; i < turnos.size(); i++) {
            if (!turnos.get(i).cumpleMin2EntrenadoresY1Fisio()) {
                return false;
            }
        }
        return true;
    }

    public void agregarUsuario(Usuario u) {
        this.usuarios.add(u);
    }

    public Usuario buscarUsuario(String login) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getLogin().equalsIgnoreCase(login)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    public void agregarDeporte(Deporte d) {
        this.deportes.add(d);
    }

    public Deporte buscarDeporte(String nombreDep) {
        for (int i = 0; i < deportes.size(); i++) {
            if (deportes.get(i).getNombre().equalsIgnoreCase(nombreDep)) {
                return deportes.get(i);
            }
        }
        return null;
    }

    public Equipo crearEquipo(String nombreEq, DeporteConjunto deporte, String categoria, int cupo) {
        Equipo eq = new Equipo(nombreEq, deporte, categoria, cupo);
        this.equipos.add(eq);
        return eq;
    }

    public boolean darDeBajaEquipo(String nombreEquipo) {
        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getNombre().equalsIgnoreCase(nombreEquipo)) {
                equipos.remove(i);
                return true;
            }
        }
        return false;
    }

    public Equipo buscarEquipo(String nombreEq) {
        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getNombre().equalsIgnoreCase(nombreEq)) {
                return equipos.get(i);
            }
        }
        return null;
    }

    public void agregarInstalacion(Instalacion inst) {
        this.instalaciones.add(inst);
    }

    public Instalacion buscarInstalacion(String nombreInst) {
        for (int i = 0; i < instalaciones.size(); i++) {
            if (instalaciones.get(i).getNombre().equalsIgnoreCase(nombreInst)) {
                return instalaciones.get(i);
            }
        }
        return null;
    }

    public boolean registrarReserva(Usuario solicitante, Instalacion inst, String bloqueHorario, int cantJugadores, String modalidad) {
        if (solicitante == null || !solicitante.puedeReservarUsoPersonal() || !(solicitante instanceof Socio)) {
            return false;
        }
        Socio socio = (Socio) solicitante;
        if (!inst.admiteModalidadYJugadores(modalidad, cantJugadores)) {
            return false;
        }
        ReservaInstalacion nueva = new ReservaInstalacion(socio, inst, bloqueHorario, cantJugadores, modalidad);
        if (!nueva.validarSinBebidaCaliente(socio)) {
            return false;
        }
        Deporte d = buscarDeporte(inst.getNombreDisciplina());
        int maxSimultaneas = 2;
        if (d instanceof DisciplinaIndividual) {
            maxSimultaneas = ((DisciplinaIndividual) d).getMaxInstalacionesSimultaneas();
        }
        if (!nueva.hayCupoSimultaneoEnDisciplina(reservas, maxSimultaneas)) {
            return false;
        }
        reservas.add(nueva);
        return true;
    }

    public void agregarArticuloTienda(ArticuloTienda a) {
        this.articulosTienda.add(a);
    }

    public ArticuloTienda buscarArticuloTienda(String nombreArt) {
        for (int i = 0; i < articulosTienda.size(); i++) {
            if (articulosTienda.get(i).getNombre().equalsIgnoreCase(nombreArt)) {
                return articulosTienda.get(i);
            }
        }
        return null;
    }

    public void agregarUbicacionInventario(UbicacionInventario u) {
        this.ubicacionesInventario.add(u);
    }

    public void agregarProductoCafeteria(ProductoCafeteria p) {
        this.productosCafeteria.add(p);
    }

    public ProductoCafeteria buscarProductoCafeteria(String nombreProd) {
        for (int i = 0; i < productosCafeteria.size(); i++) {
            if (productosCafeteria.get(i).getNombre().equalsIgnoreCase(nombreProd)) {
                return productosCafeteria.get(i);
            }
        }
        return null;
    }

    public void registrarVenta(Venta v) {
        v.acreditarPuntosSocio();
        this.ventas.add(v);
    }

    public TurnoSemanal obtenerOCrearTurno(String dia, String horario) {
        for (int i = 0; i < turnos.size(); i++) {
            TurnoSemanal t = turnos.get(i);
            if (t.getDia().equalsIgnoreCase(dia) && t.getHorarioAtencion().equalsIgnoreCase(horario)) {
                return t;
            }
        }
        TurnoSemanal nuevo = new TurnoSemanal(dia, horario);
        turnos.add(nuevo);
        return nuevo;
    }

    public String getNombre() { return nombre; }
    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Deporte> getDeportes() { return deportes; }
    public List<Equipo> getEquipos() { return equipos; }
    public List<Instalacion> getInstalaciones() { return instalaciones; }
    public List<ReservaInstalacion> getReservas() { return reservas; }
    public List<SesionEntrenamiento> getSesionesEntrenamiento() { return sesionesEntrenamiento; }
    public List<Partido> getPartidos() { return partidos; }
    public List<Competencia> getCompetencias() { return competencias; }
    public List<ArticuloTienda> getArticulosTienda() { return articulosTienda; }
    public List<UbicacionInventario> getUbicacionesInventario() { return ubicacionesInventario; }
    public List<ProductoCafeteria> getProductosCafeteria() { return productosCafeteria; }
    public List<Venta> getVentas() { return ventas; }
    public List<TurnoSemanal> getTurnos() { return turnos; }
    public List<SugerenciaProducto> getSugerencias() { return sugerencias; }
}
