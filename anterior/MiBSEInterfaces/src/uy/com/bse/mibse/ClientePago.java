package uy.com.bse.mibse;

import java.io.Serializable;

public class ClientePago implements Serializable {
	private static final long serialVersionUID = -5472704587488445056L;

	private String idTransaccion;
	private String codProducto;
	private String descProducto;
	private Integer codRamo;
	private String descRamo;
	private Integer numPoliza;
	private Integer certificado;
	private String documentId;

	private String tipoDocumento;
	private String nroDocumento;
	private String nombreCompleto;
	
	private EnumTipoMedioDePago codTipoMedioDePago;	
	
	public enum EnumTipoMedioDePago {
		DEBITO("DEBITO"),
		RED("RED");

		private final String codTipoMedioDePago;

		private EnumTipoMedioDePago(String codTipoMedioDePago) {
			this.codTipoMedioDePago = codTipoMedioDePago;
		}

		public String getStringValue() {
			return codTipoMedioDePago;
		}

		public static EnumTipoMedioDePago getEnumValue(String codTipoMedioDePago) {
			for (EnumTipoMedioDePago enumValue : EnumTipoMedioDePago.values()) {
				if (enumValue.getStringValue().equals(codTipoMedioDePago)) {
					return enumValue;
				}
			}
			return null;
		}
	}	

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public String getDescProducto() {
		return descProducto;
	}

	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public String getDescRamo() {
		return descRamo;
	}

	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}

	public Integer getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}

	public Integer getCertificado() {
		return certificado;
	}

	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
 	public String getStringCodTipoMedioDePago() {
 		return codTipoMedioDePago.getStringValue();
	}
	
	public void setStringCodTipoMedioDePago(String codTipoMedioDePago) {
		this.codTipoMedioDePago = EnumTipoMedioDePago.getEnumValue(codTipoMedioDePago);
	}

	public EnumTipoMedioDePago getCodTipoMedioPago() {
		return codTipoMedioDePago;
	}
	
	public void setCodTipoMedioDePago(EnumTipoMedioDePago codTipoMedioDePago) {
		this.codTipoMedioDePago = codTipoMedioDePago;
	}
}
