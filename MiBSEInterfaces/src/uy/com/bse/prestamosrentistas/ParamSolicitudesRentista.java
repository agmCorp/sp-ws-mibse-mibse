package uy.com.bse.prestamosrentistas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamSolicitudesRentista extends ParamGenerico {
	

	private static final long serialVersionUID = 8629051523136683316L;
	private String paisDoc;
	private String tipoDoc;
	private String documento;
	private String estadoSolicitud;
	private String  fechaDesde; //yyyy-mm-dd
	private String fechaHasta;  //yyyy-mm-dd


	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getPaisDoc() {
		return paisDoc;
	}

	public void setPaisDoc(String paisDoc) {
		this.paisDoc = paisDoc;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
}
