package uy.com.bse.mibse.sp.ws.mibse.model.dto;

import java.io.Serializable;

public class DatosPolizaADT implements Serializable {

    private static final long serialVersionUID = 897598331621346778L;

    private boolean tieneCertifLibreDeudaADT;
    private String producto;
    private String razonSocial;
    private String certifFechaVigencia;


    public String getProducto() {
        return producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getCertifFechaVigencia() {
        return certifFechaVigencia;
    }
    public void setCertifFechaVigencia(String certifFechaVigencia) {
        this.certifFechaVigencia = certifFechaVigencia;
    }
    public boolean isTieneCertifLibreDeudaADT() {
        return tieneCertifLibreDeudaADT;
    }
    public void setTieneCertifLibreDeudaADT(boolean tieneCertifLibreDeudaADT) {
        this.tieneCertifLibreDeudaADT = tieneCertifLibreDeudaADT;
    }

}
