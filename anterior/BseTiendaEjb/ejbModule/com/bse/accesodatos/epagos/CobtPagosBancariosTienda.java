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
@Table(name = "COBT_PAGOS_BANCARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CobtPagosBancariosTienda.findAll", query = "SELECT c FROM CobtPagosBancariosTienda c"),
    @NamedQuery(name = "CobtPagosBancariosTienda.findByIdTransaccion", query = "SELECT c FROM CobtPagosBancariosTienda c where c.cobtPagosBancariosPK.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "CobtPagosBancariosTienda.findByFacturaPagada", query = "SELECT c FROM CobtPagosBancariosTienda c where c.nroFactura = :nroFactura and c.codRespuesta = '00'")
})

@NamedNativeQueries({

	@NamedNativeQuery(name = "facturasPendientes", query = 
		"SELECT " + 
			"R.CARE_CARP_CD_RAMO RAMO, " + 
			"' ' PRODUCTO, " + 
			"R.CARE_CAPO_NU_POLIZA POLIZA, " + 
			"R.CARE_CACE_NU_CERTIFICADO CERTIFICADO, " + 
			"F.CAFC_NU_FACTURA NRO_FACTURA, " + 
			"'' CB1, " + 
			"'' CB2, " + 
			"F.CAFC_CAMO_CD_MONEDA MONEDA, " + 
			"decode(F.CAFC_CFE_TIPO, 101, 'S', 'N') CONSUMIDOR_FINAL, " + 
			"R.CARE_NU_CONSECUTIVO_CUOTA CUOTA, " + 
			"F.CAFC_FE_PRIMER_VTO PRIMER_VTO, " + 
			"F.CAFC_FE_SEGUNDO_VTO SEGUNDO_VTO, " + 
			"'' COD_ADHESION, " +
			"F.CAFC_CFE_SERIE SERIE_FCE, " + 
			"F.CAFC_CFE_NUMERO NUMERO_FCE, " +
			"F.CAFC_CFE_DOCUMENT_ID, " +
			"R.CARE_CASU_CD_SUCURSAL, " +
			"F.CAFC_FE_FACTURA " +
		"FROM " + 
			"CART_FACTURAS F, " + 
			"CART_FACTURAS_RECIBOS FR, " + 
			"CART_RECIBOS R " + 
		"WHERE R.CARE_CACN_NU_CEDULA_RIF = TO_NUMBER(?) " + 
			"AND F.CAFC_NU_FACTURA = FR.CAFB_CAFC_NU_FACTURA " + 
			"AND FR.CAFB_CASU_CD_SUCURSAL = R.CARE_CASU_CD_SUCURSAL " + 
			"AND FR.CAFB_CARE_NU_RECIBO = R.CARE_NU_RECIBO " + 
			"AND F.CAFC_ST_ESTADO = 1 " +
		"ORDER BY F.CAFC_FE_SEGUNDO_VTO, F.CAFC_FE_PRIMER_VTO "
	),

	@NamedNativeQuery(name = "facturasPendientesNew", query = 
		"SELECT " + 
			"R.CARE_CARP_CD_RAMO RAMO, " + 
			"' ' PRODUCTO, " + 
			"R.CARE_CAPO_NU_POLIZA POLIZA, " + 
			"R.CARE_CACE_NU_CERTIFICADO CERTIFICADO, " + 
			"F.CAFC_NU_FACTURA NRO_FACTURA, " + 
			"'' CB1, " + 
			"'' CB2, " + 
			"F.CAFC_CAMO_CD_MONEDA MONEDA, " + 
			"decode(F.CAFC_CFE_TIPO, 101, 'S', 'N') CONSUMIDOR_FINAL, " + 
			"R.CARE_NU_CONSECUTIVO_CUOTA CUOTA, " + 
			"F.CAFC_FE_PRIMER_VTO PRIMER_VTO, " + 
			"F.CAFC_FE_SEGUNDO_VTO SEGUNDO_VTO, " + 
			"'' COD_ADHESION, " +
			"F.CAFC_CFE_SERIE SERIE_FCE, " + 
			"F.CAFC_CFE_NUMERO NUMERO_FCE, " +
			"F.CAFC_CFE_DOCUMENT_ID, " +
			"R.CARE_CASU_CD_SUCURSAL, " +
			"F.CAFC_FE_FACTURA " +
		"FROM " + 
			"CART_FACTURAS F, " + 
			"CART_FACTURAS_RECIBOS FR, " + 
			"CART_RECIBOS R " + 
		"WHERE R.CARE_CACN_NU_CEDULA_RIF = TO_NUMBER(?) " + 
			"AND F.CAFC_NU_FACTURA = FR.CAFB_CAFC_NU_FACTURA " + 
			"AND FR.CAFB_CASU_CD_SUCURSAL = R.CARE_CASU_CD_SUCURSAL " + 
			"AND FR.CAFB_CARE_NU_RECIBO = R.CARE_NU_RECIBO " + 
			"AND F.CAFC_ST_ESTADO = 1 " +
		"ORDER BY F.CAFC_FE_PRIMER_VTO, F.CAFC_FE_FACTURA, F.CAFC_NU_FACTURA "
	),
// se cambia el orden segun mail del 9/10 de Andrea Blanco
//		.- fecha de primer vencimiento
//		.- fecha de emisión de factura
//		.- número de factura

	@NamedNativeQuery(name = "facturaByNumero", query = 
		"SELECT " + 
			"R.CARE_CARP_CD_RAMO RAMO, " + 
			"' ' PRODUCTO, " + 
			"R.CARE_CAPO_NU_POLIZA POLIZA, " + 
			"R.CARE_CACE_NU_CERTIFICADO CERTIFICADO, " + 
			"F.CAFC_NU_FACTURA NRO_FACTURA, " + 
			"'' CB1, " + 
			"'' CB2, " + 
			"F.CAFC_CAMO_CD_MONEDA MONEDA, " + 
			"decode(F.CAFC_CFE_TIPO, 101, 'S', 'N') CONSUMIDOR_FINAL, " + 
			"R.CARE_NU_CONSECUTIVO_CUOTA CUOTA, " + 
			"F.CAFC_FE_PRIMER_VTO PRIMER_VTO, " + 
			"F.CAFC_FE_SEGUNDO_VTO SEGUNDO_VTO, " + 
			"'' COD_ADHESION, " +
			"F.CAFC_CFE_SERIE SERIE_FCE, " + 
			"F.CAFC_CFE_NUMERO NUMERO_FCE, " +
			"F.CAFC_CFE_DOCUMENT_ID, " +
			"R.CARE_CASU_CD_SUCURSAL, " +
			"F.CAFC_FE_FACTURA, " +
			"R.CARE_CACN_NU_CEDULA_RIF " +
		"FROM " + 
			"CART_FACTURAS F, " + 
			"CART_FACTURAS_RECIBOS FR, " + 
			"CART_RECIBOS R " + 
		"WHERE " + 
			"F.CAFC_NU_FACTURA = FR.CAFB_CAFC_NU_FACTURA " + 
			"AND FR.CAFB_CASU_CD_SUCURSAL = R.CARE_CASU_CD_SUCURSAL " + 
			"AND FR.CAFB_CARE_NU_RECIBO = R.CARE_NU_RECIBO " + 
			"AND F.CAFC_ST_ESTADO = 1 " +    
			"AND F.CAFC_NU_FACTURA = ? "
	)

})
    

public class CobtPagosBancariosTienda implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    protected CobtPagosBancariosPKTienda cobtPagosBancariosPK;

    @Column(name = "CPB_NU_FACTURA")
	private Long nroFactura;
    
    @Column(name = "CPB_COD_RESPUESTA_ENTIDAD")
	private String codRespuesta; 
    
    @Column(name = "CPB_COD_AUTORIZACION_BANCO")
    private String codAutorizacionBanco;

    @Column(name = "CPB_MONEDA")
	private String moneda;
    
    @Column(name = "CPB_IMPORTE")
	private Double importe;
    
    @Column(name = "CPB_IMPORTE_DEVOLUCION")
	private Double importeDevolucion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CPB_FECHA")
	private Date fecha;
	
    @Column(name = "CPB_USUARIO")
	private String usuario;

	@Column(name = "CPB_CARP_CD_RAMO") 
    private Integer ramo;
    
	@Column(name = "CPB_CAPO_NU_POLIZA") 
    private Integer poliza;

	@Column(name = "CPB_CACE_NU_CERTIFICADO") 
    private Integer certificado;

	@Column(name = "CPB_TP_DOCUMENTO")
    private String tipoDocumento;
    
	@Column(name = "CPB_DOCUMENTO")
    private String documento;

	@Column(name = "CPB_COD_ERROR")
    private Integer codError;

	@Column(name = "CPB_DESC_ERROR")
    private String descError;
	
	@Column(name = "CPB_FECHA_CONCILIACION")
	@Temporal(TemporalType.TIMESTAMP)
    private Date fechaConciliacion;

	@Column(name = "CPB_COD_BANCO_BCU")
	private Integer codBancoBcu;

	
    public CobtPagosBancariosPKTienda getCobtPagosBancariosPK() {
		return cobtPagosBancariosPK;
	}

	public void setCobtPagosBancariosPK(CobtPagosBancariosPKTienda cobtPagosBancariosPK) {
		this.cobtPagosBancariosPK = cobtPagosBancariosPK;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public Date getFechaConciliacion() {
		return fechaConciliacion;
	}

	public void setFechaConciliacion(Date fechaConciliacion) {
		this.fechaConciliacion = fechaConciliacion;
	}

	public Integer getCodBancoBcu() {
		return codBancoBcu;
	}

	public void setCodBancoBcu(Integer codBancoBcu) {
		this.codBancoBcu=codBancoBcu;
	}
	
	
	
}
