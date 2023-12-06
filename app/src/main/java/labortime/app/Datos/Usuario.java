package labortime.app.Datos;

import labortime.app.MainActivity;

public class Usuario extends MainActivity {
   private String rut;
   private String contraseña;

    public Usuario(String rut, String contraseña) {
        this.rut = rut;
        this.contraseña = contraseña;
    }
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
