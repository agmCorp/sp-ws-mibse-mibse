package uy.com.bse.mibse.sp.ws.mibse.model.dto;

import java.util.ArrayList;
import java.util.List;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;


public class ResultObtenerPolizasCliente extends ResultGenerico {
    private static final long serialVersionUID = -566111179456801184L;

    //TODO: aaguirre revisar si List o ArrayList
    ArrayList<DatosFacturacion> datosFacturacion = new ArrayList<DatosFacturacion>();

    public ArrayList<DatosFacturacion> getDatosFacturacion() {
        return datosFacturacion;
    }

    public void setDatosFacturacion(ArrayList<DatosFacturacion> datosFacturacion) {
        this.datosFacturacion = datosFacturacion;
    }

    public void setUno(DatosFacturacion datos){
        this.datosFacturacion.add(datos);
    }

}
