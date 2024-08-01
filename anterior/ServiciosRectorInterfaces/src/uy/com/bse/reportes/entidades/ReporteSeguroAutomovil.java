package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReporteSeguroAutomovil implements Serializable {

	private static final long serialVersionUID = -3464630236323378581L;
	private Long idPoliza;
	private Long numPoliza;
	private Integer codRamo;
	private Long nroCliente;
	private String productor;
	private String emision;
	private String inicioVigencia;
	private String finVigencia;
	private Integer certificado;
	private String tramite;
	private String motivoAnulacion;
	private String dirCobro;
	private String marca;
	private String modelo;
	private Integer anio;
	private String combustible;
	private String matricula;
	private String motor;
	private String padron;
	private String chasis;
	private String calidad;
	private String cesionarioTelefonos;
	private String plan;
	private String zonaCircula;
	private String deducible;
	private String monDeducible;
	private Double valDeducible;
	private String rC;
	private Long polizaAnterior;
	private Double bonifComercial;
	private Double nivelBonus;
	private Double porcPromocion;
	private String moneda;
	private Integer cuotas;
	private Double premio;
	private Double total;
	private String formaPago;

	public Long getIdPoliza() {
		return idPoliza;
	}

	public void setIdPoliza(Long idPoliza) {
		this.idPoliza = idPoliza;
	}

	public Long getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(Long numPoliza) {
		this.numPoliza = numPoliza;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public Long getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(Long nroCliente) {
		this.nroCliente = nroCliente;
	}

	public String getProductor() {
		return productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public String getEmision() {
		return emision;
	}

	public void setEmision(String emision) {
		this.emision = emision;
	}

	public String getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(String inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public String getFinVigencia() {
		return finVigencia;
	}

	public void setFinVigencia(String finVigencia) {
		this.finVigencia = finVigencia;
	}

	public Integer getCertificado() {
		return certificado;
	}

	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	public String getDirCobro() {
		return dirCobro;
	}

	public void setDirCobro(String dirCobro) {
		this.dirCobro = dirCobro;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getPadron() {
		return padron;
	}

	public void setPadron(String padron) {
		this.padron = padron;
	}

	public String getChasis() {
		return chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public String getCalidad() {
		return calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	public String getCesionarioTelefonos() {
		return cesionarioTelefonos;
	}

	public void setCesionarioTelefonos(String cesionarioTelefonos) {
		this.cesionarioTelefonos = cesionarioTelefonos;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getZonaCircula() {
		return zonaCircula;
	}

	public void setZonaCircula(String zonaCircula) {
		this.zonaCircula = zonaCircula;
	}

	public String getDeducible() {
		return deducible;
	}

	public void setDeducible(String deducible) {
		this.deducible = deducible;
	}

	public String getMonDeducible() {
		return monDeducible;
	}

	public void setMonDeducible(String monDeducible) {
		this.monDeducible = monDeducible;
	}

	public Double getValDeducible() {
		return valDeducible;
	}

	public void setValDeducible(Double valDeducible) {
		this.valDeducible = valDeducible;
	}

	public String getrC() {
		return rC;
	}

	public void setrC(String rC) {
		this.rC = rC;
	}

	public Long getPolizaAnterior() {
		return polizaAnterior;
	}

	public void setPolizaAnterior(Long polizaAnterior) {
		this.polizaAnterior = polizaAnterior;
	}

	public Double getBonifComercial() {
		return bonifComercial;
	}

	public void setBonifComercial(Double bonifComercial) {
		this.bonifComercial = bonifComercial;
	}

	public Double getNivelBonus() {
		return nivelBonus;
	}

	public void setNivelBonus(Double nivelBonus) {
		this.nivelBonus = nivelBonus;
	}

	public Double getPorcPromocion() {
		return porcPromocion;
	}

	public void setPorcPromocion(Double porcPromocion) {
		this.porcPromocion = porcPromocion;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Integer getCuotas() {
		return cuotas;
	}

	public void setCuotas(Integer cuotas) {
		this.cuotas = cuotas;
	}

	public Double getPremio() {
		return premio;
	}

	public void setPremio(Double premio) {
		this.premio = premio;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	

	public ReporteSeguroAutomovil(Long idPoliza, Long numPoliza, Integer codRamo, Long nroCliente, String productor, String emision, String inicioVigencia, String finVigencia,
			Integer certificado, String tramite, String motivoAnulacion, String dirCobro, String marca, String modelo, Integer anio, String combustible, String matricula, String motor, String padron,
			String chasis, String calidad, String cesionarioTelefonos, String plan, String zonaCircula, String deducible, String monDeducible, Double valDeducible, String rC, Long polizaAnterior,
			Double bonifComercial, Double nivelBonus, Double porcPromocion, String moneda, Integer cuotas, Double premio, Double total, String formaPago) {
		super();
		this.idPoliza = idPoliza;
		this.numPoliza = numPoliza;
		this.codRamo = codRamo;
		this.nroCliente = nroCliente;
		this.productor = productor;
		this.emision = emision;
		this.inicioVigencia = inicioVigencia;
		this.finVigencia = finVigencia;
		this.certificado = certificado;
		this.tramite = tramite;
		this.motivoAnulacion = motivoAnulacion;
		this.dirCobro = dirCobro;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.combustible = combustible;
		this.matricula = matricula;
		this.motor = motor;
		this.padron = padron;
		this.chasis = chasis;
		this.calidad = calidad;
		this.cesionarioTelefonos = cesionarioTelefonos;
		this.plan = plan;
		this.zonaCircula = zonaCircula;
		this.deducible = deducible;
		this.monDeducible = monDeducible;
		this.valDeducible = valDeducible;
		this.rC = rC;
		this.polizaAnterior = polizaAnterior;
		this.bonifComercial = bonifComercial;
		this.nivelBonus = nivelBonus;
		this.porcPromocion = porcPromocion;
		this.moneda = moneda;
		this.cuotas = cuotas;
		this.premio = premio;
		this.total = total;
		this.formaPago = formaPago;
	}

	public ReporteSeguroAutomovil() {
		super();
		// TODO Auto-generated constructor stub
	}

}
