package serial;

import java.io.Serializable;

/**
 * Created by CamiloMontoya on 14/03/17.
 */

public class Confirmacion implements Serializable {
    public String msg;

    public Confirmacion(String msg) {

        this.msg = msg;
    }

    public String getContenido() {
        return msg;
    }

    public void setMensaje(String msg) {
        this.msg = msg;
    }
}
