package uy.com.bse.cotizadores.pymes;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCalcularPymes extends ParamGenerico{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -874165741430047671L;
	
	private Integer numCotizacion;        
    private Double capitalHurto ;                         
    private String codUbicacion;
    private String actividad;

	private String tipoCalculo;  
    private String formaPago;
    private String fechaDesde;
    private String fechaHasta;
    
    
	
    
    
    
    
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Double getCapitalHurto() {
		return capitalHurto;
	}
	public void setCapitalHurto(Double capitalHurto) {
		this.capitalHurto = capitalHurto;
	}
	public String getCodUbicacion() {
		return codUbicacion;
	}
	public void setCodUbicacion(String codUbicacion) {
		this.codUbicacion = codUbicacion;
	}
	public String getTipoCalculo() {
		return tipoCalculo;
	}
	public void setTipoCalculo(String tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
    
    public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
    
    
	
    
    
    
    

}
