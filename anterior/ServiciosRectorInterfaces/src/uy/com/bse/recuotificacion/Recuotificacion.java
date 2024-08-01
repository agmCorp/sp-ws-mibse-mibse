package uy.com.bse.recuotificacion;

import java.io.Serializable;

public class Recuotificacion implements Serializable{
	private Double importeFacturado;
	private Integer diaVencimiento;
	private Double saldoFacturar;
	private String fechaEmision;
	private Integer numCuotasFacturadas;
	private String fechaDesdeVigencia;
	private String fechaHastaVigencia;
	private String fechaDesdeVigenciaTecnica;
	private String fechaHastaVigenciaTecnica;
	private Integer numEndoso;
	private Integer numCotizacion;
	private Double montoPrima;
	private Double montoPremio;
	private String habilitoDiaVto;
	
	public Double getImporteFacturado() {
		return importeFacturado;
	}
	public void setImporteFacturado(Double importeFacturado) {
		this.importeFacturado = importeFacturado;
	}
	public Integer getDiaVencimiento() {
		return diaVencimiento;
	}
	public void setDiaVencimiento(Integer diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}
	public Double getSaldoFacturar() {
		return saldoFacturar;
	}
	public void setSaldoFacturar(Double saldoFacturar) {
		this.saldoFacturar = saldoFacturar;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Integer getNumCuotasFacturadas() {
		return numCuotasFacturadas;
	}
	public void setNumCuotasFacturadas(Integer numCuotasFacturadas) {
		this.numCuotasFacturadas = numCuotasFacturadas;
	}
	public String getFechaDesdeVigencia() {
		return fechaDesdeVigencia;
	}
	public void setFechaDesdeVigencia(String fechaDesdeVigencia) {
		this.fechaDesdeVigencia = fechaDesdeVigencia;
	}
	public String getFechaHastaVigencia() {
		return fechaHastaVigencia;
	}
	public void setFechaHastaVigencia(String fechaHastaVigencia) {
		this.fechaHastaVigencia = fechaHastaVigencia;
	}
	public String getFechaDesdeVigenciaTecnica() {
		return fechaDesdeVigenciaTecnica;
	}
	public void setFechaDesdeVigenciaTecnica(String fechaDesdeVigenciaTecnica) {
		this.fechaDesdeVigenciaTecnica = fechaDesdeVigenciaTecnica;
	}
	public String getFechaHastaVigenciaTecnica() {
		return fechaHastaVigenciaTecnica;
	}
	public void setFechaHastaVigenciaTecnica(String fechaHastaVigenciaTecnica) {
		this.fechaHastaVigenciaTecnica = fechaHastaVigenciaTecnica;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Double getMontoPrima() {
		return montoPrima;
	}
	public void setMontoPrima(Double montoPrima) {
		this.montoPrima = montoPrima;
	}
	public Double getMontoPremio() {
		return montoPremio;
	}
	public void setMontoPremio(Double montoPremio) {
		this.montoPremio = montoPremio;
	}
	public String getHabilitoDiaVto() {
		return habilitoDiaVto;
	}
	public void setHabilitoDiaVto(String habilitoDiaVto) {
		this.habilitoDiaVto = habilitoDiaVto;
	}

}
