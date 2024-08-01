package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCalcularValidarVigencia extends ParamGenerico{
	private String codRamo;
	private String codProducto;
	private String numPoliza;
	private String codPromocion;
	private String modoCalculo;
	private String vigencia;
	private String tipoTransaccion;
	private String fechaDesdeVigencia;
	private String fechaHastaVigencia;
	private String codVigenciaTecnica;
	
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}	
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getCodPromocion() {
		return codPromocion;
	}
	public void setCodPromocion(String codPromocion) {
		this.codPromocion = codPromocion;
	}
	public String getModoCalculo() {
		return modoCalculo;
	}
	public void setModoCalculo(String modoCalculo) {
		this.modoCalculo = modoCalculo;
	}
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
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
	public String getCodVigenciaTecnica() {
		return codVigenciaTecnica;
	}
	public void setCodVigenciaTecnica(String codVigenciaTecnica) {
		this.codVigenciaTecnica = codVigenciaTecnica;
	}			

}
