
package uy.com.bse.mibse.siniestrosrector;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultStrosActivosXCliente extends ResultGenerico {

	
	
	
	private static final long serialVersionUID = 1L;
	private Integer anio;
    private String feOcurr;
    private Integer numDenuncia;
    private Integer ramo;
    private String serie;
    private Integer stro;
    private Integer substro;

  
    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer value) {
        this.anio = value;
    }

    public String getFeOcurr() {
        return feOcurr;
    }

    public void setFeOcurr(String value) {
        this.feOcurr = value;
    }

    public Integer getNumDenuncia() {
        return numDenuncia;
    }

    public void setNumDenuncia(Integer value) {
        this.numDenuncia = value;
    }

    public Integer getRamo() {
        return ramo;
    }

    public void setRamo(Integer value) {
        this.ramo = value;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String value) {
        this.serie = value;
    }

    public Integer getStro() {
        return stro;
    }

    public void setStro(Integer value) {
        this.stro = value;
    }

    public Integer getSubstro() {
        return substro;
    }

    public void setSubstro(Integer value) {
        this.substro = value;
    }

}
