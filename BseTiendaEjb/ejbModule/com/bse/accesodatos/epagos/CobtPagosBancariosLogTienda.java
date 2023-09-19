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
@Table(name = "COBT_PAGOS_BANCARIOS_LOG")
@XmlRootElement
    

public class CobtPagosBancariosLogTienda implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    protected CobtPagosBancariosLogPKTienda cobtPagosBancariosLogPK;

	@Column(name = "CPBL_CARP_CD_RAMO") 
    private Integer ramo;
    
	@Column(name = "CPBL_CAPO_NU_POLIZA") 
    private Integer poliza;

	@Column(name = "CPBL_CACE_NU_CERTIFICADO") 
    private Integer certificado;

	@Column(name = "CPBL_TP_DOCUMENTO")
    private String tipoDocumento;
    
	@Column(name = "CPBL_DOCUMENTO")
    private String documento;
    
    @Column(name = "CPBL_NU_FACTURA")
	private Long nroFactura;
    
    @Column(name = "CPBL_COD_RESPUESTA_ENTIDAD")
	private String codRespuesta; 
    
    @Column(name = "CPBL_COD_AUTORIZACION_BANCO")
    private String codAutorizacionBanco;

    @Column(name = "CPBL_MONEDA")
	private String moneda;
    
    @Column(name = "CPBL_IMPORTE")
	private Double importe;
    
    @Column(name = "CPBL_IMPORTE_DEVOLUCION")
	private Double importeDevolucion;

    @Column(name = "CPBL_OPERACION")
	private String operacion;

    @Column(name = "CPBL_OBS") 
	private String obs;

    @Column(name = "CPBL_USUARIO")
	private String usuario;

	@Column(name = "CPBL_COD_BANCO_BCU")
	private Integer codBancoBcu;

	public CobtPagosBancariosLogPKTienda getCobtPagosBancariosLogPK() {
		return cobtPagosBancariosLogPK;
	}

	public void setCobtPagosBancariosLogPK(CobtPagosBancariosLogPKTienda cobtPagosBancariosLogPK) {
		this.cobtPagosBancariosLogPK = cobtPagosBancariosLogPK;
	}

	public Integer getRamo() {
		return ramo;
	}

	public void setRamo(Integer ramo) {
		this.ramo = ramo;
	}

	public Integer getPoliza() {
		return poliza;
	}

	public void setPoliza(Integer poliza) {
		this.poliza = poliza;
	}

	public Integer getCertificado() {
		return certificado;
	}

	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
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

	public Integer getCodBancoBcu() {
		return codBancoBcu;
	}

	public void setCodBancoBcu(Integer codBancoBcu) {
		this.codBancoBcu=codBancoBcu;
	}

	
}
