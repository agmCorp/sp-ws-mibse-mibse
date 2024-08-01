package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCalcularCobertura extends ResultGenerico{
	
	private DatosValidos datosValidos;
	private String habilitoInsertar;
	private String tieneFranquicias;
	private String codPlanPago;
	private Integer diaVto;
	private Integer codNivelComision;
	private String descNivelComision;
	
	public DatosValidos getDatosValidos() {
		return datosValidos;
	}
	public void setDatosValidos(DatosValidos datosValidos) {
		this.datosValidos = datosValidos;
	}
	public String getHabilitoInsertar() {
		return habilitoInsertar;
	}
	public void setHabilitoInsertar(String habilitoInsertar) {
		this.habilitoInsertar = habilitoInsertar;
	}
	public String getTieneFranquicias() {
		return tieneFranquicias;
	}
	public void setTieneFranquicias(String tieneFranquicias) {
		this.tieneFranquicias = tieneFranquicias;
	}
	public String getCodPlanPago() {
		return codPlanPago;
	}
	public void setCodPlanPago(String codPlanPago) {
		this.codPlanPago = codPlanPago;
	}
	public Integer getDiaVto() {
		return diaVto;
	}
	public void setDiaVto(Integer diaVto) {
		this.diaVto = diaVto;
	}
	public Integer getCodNivelComision() {
		return codNivelComision;
	}
	public void setCodNivelComision(Integer codNivelComision) {
		this.codNivelComision = codNivelComision;
	}
	public String getDescNivelComision() {
		return descNivelComision;
	}
	public void setDescNivelComision(String descNivelComision) {
		this.descNivelComision = descNivelComision;
	}
		
	
}
