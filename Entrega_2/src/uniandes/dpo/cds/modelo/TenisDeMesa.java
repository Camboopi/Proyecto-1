package uniandes.dpo.cds.modelo;

public class TenisDeMesa extends DisciplinaIndividual {
    private boolean admiteSencillos;
    private boolean admiteDobles;

    public TenisDeMesa(int maxInstalacionesSimultaneas) {
        super("Tenis de Mesa", maxInstalacionesSimultaneas);
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
