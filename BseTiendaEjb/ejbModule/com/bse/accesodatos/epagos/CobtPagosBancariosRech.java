package com.bse.accesodatos.epagos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "COBT_PAGOS_BANCARIOS_RECH")
public class CobtPagosBancariosRech implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    protected CobtPagosBancariosRechPK cobtPagosBancariosRechPK;

    @Column(name = "CPBR_NU_FACTURA")
	private Long nroFactura;
    
    @Column(name = "CPBR_COD_RESPUESTA_ENTIDAD")
	private String codRespuesta; 
    
    @Column(name = "CPBR_COD_AUTORIZACION_BANCO")
    private String codAutorizacionBanco;

    @Column(name = "CPBR_MONEDA")
	private String moneda;
    
    @Column(name = "CPBR_IMPORTE")
	private Double importe;
    
    @Column(name = "CPBR_IMPORTE_DEVOLUCION")
	private Double importeDevolucion;

    @Column(name = "CPBR_USUARIO")
	private String usuario;

	@Column(name = "CPBR_COD_ERROR_BSE")
    private Integer codError;

	@Column(name = "CPBR_DESC_ERROR_BSE")
    private String descError;
	
	@Column(name = "CPBR_COD_BANCO_BCU")
	private Integer codBancoBcu;

	
    public CobtPagosBancariosRechPK getCobtPagosBancariosRechPK() {
		return cobtPagosBancariosRechPK;
	}

	public void setCobtPagosBancariosRechPK(CobtPagosBancariosRechPK cobtPagosBancariosRechPK) {
		this.cobtPagosBancariosRechPK = cobtPagosBancariosRechPK;
	}

	public Long getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(Long nroFactura) {
		this.nroFactura = nroFactura;
	}

	public String getCodRespuesta() {
		return codRespuesta;
	}

	public void setCodRespuesta(String codRespuesta) {
		this.codRespuesta = codRespuesta;
	}

	public String getCodAutorizacionBanco() {
		return codAutorizacionBanco;
	}

	public void setCodAutorizacionBanco(String codAutorizacionBanco) {
		this.codAutorizacionBanco = codAutorizacionBanco;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Double getImporteDevolucion() {
		return importeDevolucion;
	}

	public void setImporteDevolucion(Double importeDevolucion) {
		this.importeDevolucion = importeDevolucion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getCodError() {
		return codError;
	}

	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	public String getDescError() {
		return descError;
	}

	public void setDescError(String descError) {
		this.descError = descError;
	}

	public Integer getCodBancoBcu() {
		return codBancoBcu;
	}

	public void setCodBancoBcu(Integer codBancoBcu) {
		this.codBancoBcu=codBancoBcu;
	}
	
	
	
}
