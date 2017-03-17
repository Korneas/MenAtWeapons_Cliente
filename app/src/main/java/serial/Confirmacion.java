package serial;

import java.io.Serializable;

/**
 * Created by CamiloMontoya on 14/03/17.
 */

public class Confirmacion implements Serializable {
    public String name;
    public String password;
    public boolean confirmado;

    public Confirmacion(String name, String password, boolean confirmado) {
        this.name = name;
        this.password = password;
        this.confirmado = confirmado;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }
}