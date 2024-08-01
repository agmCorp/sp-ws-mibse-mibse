package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCalcularValidarVigencia extends ResultGenerico {
	private String fechaDesdeVigencia;
	private String fechaHastaVigencia;
	private String fechaDesdeVigenciaTecnica;
	private String fechaHastaVigenciaTecnica;
	private String vigencia;
	private String modoCalculo;
		
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
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public String getModoCalculo() {
		return modoCalculo;
	}
	public void setModoCalculo(String modoCalculo) {
		this.modoCalculo = modoCalculo;
	}
	
}
