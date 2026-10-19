package uniandes.dpo.cds.modelo;

import java.util.List;

public class ReporteAdministrativo {
    private String rubro;
    private String periodo;

    public ReporteAdministrativo(String rubro, String periodo) {
        this.rubro = rubro;
        this.periodo = periodo;
    }

    public String generarReporteVentasPorRubroYPeriodo(List<Venta> ventas) {
        double totalRubro = 0;
        int cant = 0;
        for (int i = 0; i < ventas.size(); i++) {
            Venta v = ventas.get(i);
            if ("Todos".equalsIgnoreCase(rubro) || v.getRubroContable().equalsIgnoreCase(rubro)) {
                totalRubro = totalRubro + v.getValorTotal();
                cant++;
            }
        }
        return "Reporte Ventas [" + rubro + " - " + periodo + "]: " + cant + " ventas | Total: $" + Math.round(totalRubro);
    }

    public String generarReporteDeportivoAsistenciaYPartidos(List<SesionEntrenamiento> sesiones, List<Partido> partidos) {
        int totalAsistencias = 0;
        for (int i = 0; i < sesiones.size(); i++) {
            totalAsistencias = totalAsistencias + sesiones.get(i).getAsistencias().size();
        }
        return "Reporte Deportivo [" + periodo + "]: " + sesiones.size() + " sesiones (" + totalAsistencias + " asistencias) | " + partidos.size() + " partidos";
    }

    public String generarReporteEvolucionRankings(List<Usuario> usuarios) {
        String texto = "Evolucion de Rankings Individuales (ELO): ";
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u instanceof Socio) {
                Socio s = (Socio) u;
                for (int j = 0; j < s.getPerfilesAcademia().size(); j++) {
                    PerfilAcademiaIndividual p = s.getPerfilesAcademia().get(j);
                    texto = texto + s.getNombre() + " [" + p.getDisciplina().getNombre() + " - " + p.getNivel() + ": " + p.getPuntajeRankingELO() + "] ";
                }
            }
        }
        return texto;
    }
}
