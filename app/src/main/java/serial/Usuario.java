package serial;

import java.io.Serializable;

/**
 * Created by CamiloMontoya on 1/03/17.
 */

public class Usuario implements Serializable {
    public String user, password;

    public Usuario(String user, String password) {

        this.user = user;
        this.password = password;
    }

    public String getUsuario() {
        return user;
    }

    public void setUsuario(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}