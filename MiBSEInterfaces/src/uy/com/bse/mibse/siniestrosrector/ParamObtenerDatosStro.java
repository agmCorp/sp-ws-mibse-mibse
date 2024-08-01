package uy.com.bse.mibse.siniestrosrector;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDatosStro extends ParamGenerico {


	private static final long serialVersionUID = 1L;
	private int anio;
    private int ramo;
    private int stro;
    private int substro;

    public int getAnio() {
        return anio;
    }

    public void setAnio(int value) {
        this.anio = value;
    }

    public int getRamo() {
        return ramo;
    }

    public void setRamo(int value) {
        this.ramo = value;
    }

    public int getStro() {
        return stro;
    }

    public void setStro(int value) {
        this.stro = value;
    }

    public int getSubstro() {
        return substro;
    }

    public void setSubstro(int value) {
        this.substro = value;
    }

}
