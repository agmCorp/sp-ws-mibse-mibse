package uy.com.bse.mibse.siniestrosrector;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultMapaStro extends ResultGenerico {

   
	private static final long serialVersionUID = -6717311277638013120L;
	private Integer codSubNota;
    private String descripcionSubNota;
    private String fechaNota;
    private String observacionNota;
    private String tipoNota;
    //Campos de la BPM
    private String idFlujoBPM;
    private Integer instanciaNota;
    private String subtipoReclamo;
    private String sistemaOrigen;

      
   
    public Integer getCodSubNota() {
        return codSubNota;
    }

    public void setCodSubNota(Integer value) {
        this.codSubNota = value;
    }

    public String getDescripcionSubNota() {
        return descripcionSubNota;
    }

    public void setDescripcionSubNota(String value) {
        this.descripcionSubNota = value;
    }

    public String getFechaNota() {
        return fechaNota;
    }

    public void setFechaNota(String value) {
        this.fechaNota = value;
    }

    public String getObservacionNota() {
        return observacionNota;
    }

    public void setObservacionNota(String value) {
        this.observacionNota = value;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String value) {
        this.tipoNota = value;
    }

	public String getIdFlujoBPM() {
		return idFlujoBPM;
	}

	public void setIdFlujoBPM(String idFlujoBPM) {
		this.idFlujoBPM = idFlujoBPM;
	}

	public Integer getInstanciaNota() {
		return instanciaNota;
	}

	public void setInstanciaNota(Integer instanciaNota) {
		this.instanciaNota = instanciaNota;
	}

	public String getSubtipoReclamo() {
		return subtipoReclamo;
	}

	public void setSubtipoReclamo(String subtipoReclamo) {
		this.subtipoReclamo = subtipoReclamo;
	}

	public String getSistemaOrigen() {
		return sistemaOrigen;
	}

	public void setSistemaOrigen(String sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

}
