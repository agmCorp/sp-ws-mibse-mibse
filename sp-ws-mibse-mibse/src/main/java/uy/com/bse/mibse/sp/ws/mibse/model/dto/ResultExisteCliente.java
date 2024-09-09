package uy.com.bse.mibse.sp.ws.mibse.model.dto;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;

public class ResultExisteCliente extends ResultGenerico {
    private static final long serialVersionUID = -566111179456801184L;
    private String user;
    private boolean existe;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
