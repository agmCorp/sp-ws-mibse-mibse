package uy.com.bse.mibse;

import java.util.Date;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamFiniquitosCliente extends ParamGenerico {
	private static final long serialVersionUID = 6329651533103180863L;
	
	private String codTipoDoc;
	private String documento;
	private String estadoFiniquito;
	private Date fechaDesde;
	private Date fechaHasta;

	public String getCodTipoDoc() {
		return codTipoDoc;
	}

	public void setCodTipoDoc(String codTipoDoc) {
		this.codTipoDoc = codTipoDoc;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEstadoFiniquito() {
		return estadoFiniquito;
	}

	public void setEstadoFiniquito(String estadoFiniquito) {
		this.estadoFiniquito = estadoFiniquito;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
