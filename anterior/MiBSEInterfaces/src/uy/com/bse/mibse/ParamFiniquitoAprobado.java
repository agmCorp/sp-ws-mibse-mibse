package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamFiniquitoAprobado extends ParamGenerico {
	private static final long serialVersionUID = -7473215369807712829L;

	private String nroFiniquito;
	private String codTipoDoc;
	private String documento;
	private String fechaFirma;
	private String horaMinutoFirma;

	public String getNroFiniquito() {
		return nroFiniquito;
	}

	public void setNroFiniquito(String nroFiniquito) {
		this.nroFiniquito = nroFiniquito;
	}

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
