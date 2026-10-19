package uniandes.dpo.cds.modelo;

public class Padel extends DisciplinaIndividual {
    private boolean siempreDobles;

    public Padel(int maxInstalacionesSimultaneas) {
        super("Pádel", maxInstalacionesSimultaneas);
        this.siempreDobles = true;
    }

    public boolean validarCuatroJugadores(int cant) {
        if (siempreDobles && cant == 4) {
            return true;
        }
        return false;
    }
}
