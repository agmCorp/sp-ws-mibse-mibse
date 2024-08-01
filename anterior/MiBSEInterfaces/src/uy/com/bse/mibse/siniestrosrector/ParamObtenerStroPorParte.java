package uy.com.bse.mibse.siniestrosrector;

import javax.xml.bind.JAXBElement;

import uy.com.bse.utilitario.dato.ParamGenerico;



public class ParamObtenerStroPorParte extends ParamGenerico {

    
	private static final long serialVersionUID = 1L;
	private JAXBElement<Integer> nroAda;
    private String serieAda;

    public JAXBElement<Integer> getNroAda() {
        return nroAda;
    }

    public void setNroAda(JAXBElement<Integer> value) {
        this.nroAda = value;
    }

    public String getSerieAda() {
        return serieAda;
    }

    public void setSerieAda(String value) {
        this.serieAda = value;
    }

}
