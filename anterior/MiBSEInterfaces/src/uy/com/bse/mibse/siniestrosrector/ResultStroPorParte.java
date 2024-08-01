package uy.com.bse.mibse.siniestrosrector;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultStroPorParte extends ResultGenerico {

  
	private static final long serialVersionUID = 1665387569731552874L;
	private Integer anioStr;
    private Integer ramoStr;
    private Integer stroStr;
    private Integer substroStr;

    public Integer getAnioStr() {
        return anioStr;
    }

    public void setAnioStr(Integer value) {
        this.anioStr = value;
    }

    public Integer getRamoStr() {
        return ramoStr;
    }

    public void setRamoStr(Integer value) {
        this.ramoStr = value;
    }

    public Integer getStroStr() {
        return stroStr;
    }

    public void setStroStr(Integer value) {
        this.stroStr = value;
    }

    public Integer getSubstroStr() {
        return substroStr;
    }

    public void setSubstroStr(Integer value) {
        this.substroStr = value;
    }

}
