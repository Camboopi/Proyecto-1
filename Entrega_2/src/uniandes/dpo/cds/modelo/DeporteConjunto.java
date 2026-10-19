package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class DeporteConjunto extends Deporte {
    protected int minJugadoresHabilitados;
    protected boolean permiteEmpates;
    protected List<String> categoriasEdad;

    public DeporteConjunto(String nombre, int minJugadoresHabilitados, boolean permiteEmpates) {
        super(nombre);
        this.minJugadoresHabilitados = minJugadoresHabilitados;
        this.permiteEmpates = permiteEmpates;
        this.categoriasEdad = new ArrayList<String>();
        this.categoriasEdad.add("Sub-13");
        this.categoriasEdad.add("Sub-15");
        this.categoriasEdad.add("Sub-17");
        this.categoriasEdad.add("Mayores");
    }

    public int getMinJugadoresHabilitados() {
        return minJugadoresHabilitados;
    }

    public boolean isPermiteEmpates() {
        return permiteEmpates;
    }

    public List<String> getCategoriasEdad() {
        return categoriasEdad;
    }

    @Override
    public boolean esDeporteDeConjunto() {
        return true;
    }

    public boolean alcanzaMinimoJugadores(int habilitados) {
        return habilitados >= minJugadoresHabilitados;
    }

    public abstract int otorgarPuntosLiga(boolean victoria, boolean empate);
}
