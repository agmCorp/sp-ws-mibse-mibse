package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultFacturacionElectronica extends ResultGenerico {
	private static final long serialVersionUID = -8719438952343148858L;
	private Long documentId;
	
	
	public ResultFacturacionElectronica() {
		super();
	}
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
}
