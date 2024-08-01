package uy.com.bse.mibse.siniestrosrector;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerStrosActivosXCliente extends ParamGenerico {

	
	private static final long serialVersionUID = 1L;

	private String nroCliente;
	private Integer ramo;
	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getPorAsegurado() {
		return porAsegurado;
	}

	public void setPorAsegurado(String porAsegurado) {
		this.porAsegurado = porAsegurado;
	}

	public String getPorContratante() {
		return porContratante;
	}

	public void setPorContratante(String porContratante) {
		this.porContratante = porContratante;
	}

	private String fechaDesde;
    private String porAsegurado;
    private String porContratante;
	
	  
    public String getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(String value) {
        this.nroCliente = value;
    }

    public Integer getRamo() {
        return ramo;
    }

    public void setRamo(Integer value) {
        this.ramo = value;
    }

}
