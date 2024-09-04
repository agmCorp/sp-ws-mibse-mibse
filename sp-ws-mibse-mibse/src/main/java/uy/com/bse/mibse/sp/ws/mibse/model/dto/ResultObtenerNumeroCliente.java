package uy.com.bse.mibse.sp.ws.mibse.model.dto;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;

public class ResultObtenerNumeroCliente extends ResultGenerico {
    private static final long serialVersionUID = -566111179456801184L;

    private String numCliente;

    public String getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(String numCliente) {
        this.numCliente = numCliente;
    }

}
