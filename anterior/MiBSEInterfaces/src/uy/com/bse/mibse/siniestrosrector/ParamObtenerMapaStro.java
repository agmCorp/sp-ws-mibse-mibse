
package uy.com.bse.mibse.siniestrosrector;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerMapaStro extends ParamGenerico {


	private static final long serialVersionUID = 1L;
	private String fechaDesde;
	private Integer numDenuncia;
	private String serie;

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String value) {
        this.fechaDesde = value;
    }

    public Integer getNumDenuncia() {
        return numDenuncia;
    }

    public void setNumDenuncia(Integer value) {
        this.numDenuncia = value;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String value) {
        this.serie = value;
    }

}
