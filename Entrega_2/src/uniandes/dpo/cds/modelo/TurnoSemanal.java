package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public class TurnoSemanal {
    private String dia;
    private String horarioAtencion;
    private List<Empleado> empleadosAsignados;

    public TurnoSemanal(String dia, String horarioAtencion) {
        this.dia = dia;
        this.horarioAtencion = horarioAtencion;
        this.empleadosAsignados = new ArrayList<Empleado>();
    }

    public void asignarEmpleado(Empleado e) {
        if (!empleadosAsignados.contains(e)) {
            empleadosAsignados.add(e);
        }
    }

    public void removerEmpleado(Empleado e) {
        empleadosAsignados.remove(e);
    }

    // Regla del club: minimo 2 entrenadores y 1 fisioterapeuta por turno
    public boolean cumpleMin2EntrenadoresY1Fisio() {
        int entrenadores = 0;
        int fisios = 0;
        for (int i = 0; i < empleadosAsignados.size(); i++) {
            Empleado e = empleadosAsignados.get(i);
            if (e instanceof Entrenador) {
                entrenadores++;
            } else if (e instanceof Fisioterapeuta) {
                fisios++;
            }
        }
        return entrenadores >= 2 && fisios >= 1;
    }

    public String getDia() {
        return dia;
    }

    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public List<Empleado> getEmpleadosAsignados() {
        return empleadosAsignados;
    }
}
