package uniandes.dpo.cds.modelo;

public abstract class Usuario {
    protected String login;
    protected String password;
    protected String nombre;

    public Usuario(String login, String password, String nombre) {
        this.login = login;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean autenticar(String pass) {
        if (this.password != null && this.password.equals(pass)) {
            return true;
        }
        return false;
    }

    public abstract boolean puedeReservarUsoPersonal();

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }
}
