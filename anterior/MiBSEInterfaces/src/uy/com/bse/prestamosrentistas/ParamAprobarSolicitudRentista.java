package uy.com.bse.prestamosrentistas;

import java.util.Date;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAprobarSolicitudRentista extends ParamGenerico {
	private static final long serialVersionUID = 6329651533103180863L;
	
	private String paisDoc;
	private String tipoDoc;
	private String documento;
	private int  nuSolicitud;
	private String fechaFirma; //dd/mm/yyyy 
	private String horaMinutoFirma; //hh24:mm


	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public int getNuSolicitud() {
		return nuSolicitud;
	}

	public void setNuSolicitud(int nuSolicitud) {
		this.nuSolicitud = nuSolicitud;
	}

	public String getFechaFirma() {
		return fechaFirma;
	}

	public void setFechaFirma(String fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

	public String getHoraMinutoFirma() {
		return horaMinutoFirma;
	}

	public void setHoraMinutoFirma(String horaMinutoFirma) {
		this.horaMinutoFirma = horaMinutoFirma;
	}

	
}
