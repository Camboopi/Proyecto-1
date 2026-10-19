package uniandes.dpo.cds.modelo;

public class Tenis extends DisciplinaIndividual {
    private boolean admiteSencillos;
    private boolean admiteDobles;

    public Tenis(int maxInstalacionesSimultaneas) {
        super("Tenis", maxInstalacionesSimultaneas);
        this.admiteSencillos = true;
        this.admiteDobles = true;
    }

    public boolean validarModalidad(String mod, int cant) {
        if ("Sencillos".equalsIgnoreCase(mod) && admiteSencillos) {
            return cant == 2;
        }
        if ("Dobles".equalsIgnoreCase(mod) && admiteDobles) {
            return cant == 4;
        }
        return false;
    }
}
