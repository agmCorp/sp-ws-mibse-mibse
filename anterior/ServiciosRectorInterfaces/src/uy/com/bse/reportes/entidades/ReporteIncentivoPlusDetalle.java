package uy.com.bse.reportes.entidades;

import java.io.Serializable;
import java.util.Date;

public class ReporteIncentivoPlusDetalle implements Serializable {
	private static final long serialVersionUID = -1516526199474765836L;
	private Integer numPoliza;
	private Integer codRamo;
	private Integer certificado;
	private String mesAnio;
	private String matricula;
	private Date desde;
	private Date hasta;
	private String producto;
	private String tipo;
	private String plan;
	private String chasis;
	private String puntaje;
	private Integer moneda;
	private Integer cliente;
	
	public ReporteIncentivoPlusDetalle() {
		super();
	}
	
	
	public ReporteIncentivoPlusDetalle(Integer numPoliza, Integer codRamo, Integer certificado, String mesAnio, String matricula, Date desde, Date hasta, String producto, String tipo,
			String plan, String chasis, String puntaje, Integer moneda, Integer cliente) {
		super();
		this.numPoliza = numPoliza;
		this.codRamo = codRamo;
		this.certificado = certificado;
		this.mesAnio = mesAnio;
		this.matricula = matricula;
		this.desde = desde;
		this.hasta = hasta;
		this.producto = producto;
		this.tipo = tipo;
		this.plan = plan;
		this.chasis = chasis;
		this.puntaje = puntaje;
		this.moneda =moneda;
		this.cliente = cliente;
	}

	

	public ReporteIncentivoPlusDetalle(Integer numPoliza, Integer codRamo,
			Integer certificado, String mesAnio, String matricula,
			String producto, String tipo, String plan, String chasis,
			String puntaje, Integer moneda, Integer cliente) {
		super();
		this.numPoliza = numPoliza;
		this.codRamo = codRamo;
		this.certificado = certificado;
		this.mesAnio = mesAnio;
		this.matricula = matricula;
		this.producto = producto;
		this.tipo = tipo;
		this.plan = plan;
		this.chasis = chasis;
		this.puntaje = puntaje;
		this.moneda = moneda;
		this.cliente = cliente;
		desde=null;
		hasta =null;
	}


	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getCertificado() {
		return certificado;
	}
	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}
	public String getMesAnio() {
		return mesAnio;
	}
	public void setMesAnio(String mesAnio) {
		this.mesAnio = mesAnio;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getChasis() {
		return chasis;
	}
	public void setChasis(String chasis) {
		this.chasis = chasis;
	}
	public String getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}
	
	public Integer getMoneda() {
		return moneda;
	}

	public void setMoneda(Integer moneda) {
		this.moneda = moneda;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
}
