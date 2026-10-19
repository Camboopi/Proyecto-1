package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public class Snack extends ProductoCafeteria {
    private List<String> alergenos;

    public Snack(String nombre, double precio, List<String> alergenos) {
        super(nombre, precio);
        this.alergenos = new ArrayList<String>();
        if (alergenos != null) {
            for (int i = 0; i < alergenos.size(); i++) {
                this.alergenos.add(alergenos.get(i));
            }
        }
    }

    public String informarAlergenosAlSocio() {
        if (alergenos.isEmpty()) {
            return "Sin alergenos reportados";
        }
        String texto = "Contiene alergenos: ";
        for (int i = 0; i < alergenos.size(); i++) {
            texto = texto + alergenos.get(i);
            if (i < alergenos.size() - 1) {
                texto = texto + ", ";
            }
        }
        return texto;
    }

    public List<String> getAlergenos() {
        return alergenos;
    }

    @Override
    public boolean puedeDespacharseEn(Instalacion inst) {
        return true;
    }
}
