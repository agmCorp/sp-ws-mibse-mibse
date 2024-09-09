package uy.com.bse.mibse.sp.ws.mibse.model.dto;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;

public class ParamExisteCliente extends ParamGenerico {

    private static final long serialVersionUID = 8264209137521255944L;

    private String tipoDocumento;
    private String numDocumento;


    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }


}
