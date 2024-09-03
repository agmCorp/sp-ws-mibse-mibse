package uy.com.bse.mibse.sp.ws.mibse.model.dto;

import java.io.Serializable;
import java.util.List;

public class DatosMatricula implements Serializable {

    private static final long serialVersionUID = 897598331621346778L;

    private String matricula;
    private int coberturasRestantes;
    private int nroCertificado;
    private String marca;
    private String modelo;
    private boolean tieneCertificadoMercosur;
    private List<DatoAuxilioOtorgado> listaAuxilios;

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public int getNroCertificado() {
        return nroCertificado;
    }
    public void setNroCertificado(int nroCertificado) {
        this.nroCertificado = nroCertificado;
    }
    public int getCoberturasRestantes() {
        return coberturasRestantes;
    }
    public void setCoberturasRestantes(int coberturasRestantes) {
        this.coberturasRestantes = coberturasRestantes;
    }
    public List<DatoAuxilioOtorgado> getListaAuxilios() {
        return listaAuxilios;
    }
    public void setListaAuxilios(List<DatoAuxilioOtorgado> listaAuxilios) {
        this.listaAuxilios = listaAuxilios;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public boolean isTieneCertificadoMercosur() {
        return tieneCertificadoMercosur;
    }
    public void setTieneCertificadoMercosur(boolean tieneCertificadoMercosur) {
        this.tieneCertificadoMercosur = tieneCertificadoMercosur;
    }


}
