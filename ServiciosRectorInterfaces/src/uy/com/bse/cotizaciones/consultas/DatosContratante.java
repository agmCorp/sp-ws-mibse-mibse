package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class DatosContratante implements Serializable{
	private String nacionalidadCod;
	private Integer clienteCod;
	private String clienteDesc;
	private Integer radioCod;
	private String radioDesc;
	private String direccionDesc;
	private Integer direccionNum;
	private String direccionUnidad;
	private String direccionPiso;
	private Integer localidadCod;
	private String localidadDesc;
	private Integer departamentoCod;
	private String departamentoDesc; 
	private Integer paisCod;
	private String paisDesc;
	private Integer stCapital;	
	private Integer numPersona;
	private String descripDpto;
	private String telefono;
	private String aclaracionDic;
	private Integer numPostal;
	private Integer codCalle;
	private String anulacionCorrida;
	private Integer diaVencimiento;
	private String tieneDeuda;
	private String validaComunicaciones;
	private String noTieneDocumento;
	
	public String getNoTieneDocumento() {
		return noTieneDocumento;
	}
	public void setNoTieneDocumento(String noTieneDocumento) {
		this.noTieneDocumento = noTieneDocumento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Integer getStCapital() {
		return stCapital;
	}
	public void setStCapital(Integer stCapital) {
		this.stCapital = stCapital;
	}
	public Integer getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}
	public String getDescripDpto() {
		return descripDpto;
	}
	public void setDescripDpto(String descripDpto) {
		this.descripDpto = descripDpto;
	}
	
	public String getAclaracionDic() {
		return aclaracionDic;
	}
	public void setAclaracionDic(String aclaracionDic) {
		this.aclaracionDic = aclaracionDic;
	}
	public Integer getNumPostal() {
		return numPostal;
	}
	public void setNumPostal(Integer numPostal) {
		this.numPostal = numPostal;
	}
	public Integer getCodCalle() {
		return codCalle;
	}
	public void setCodCalle(Integer codCalle) {
		this.codCalle = codCalle;
	}
	public String getNacionalidadCod() {
		return nacionalidadCod;
	}
	public void setNacionalidadCod(String nacionalidadCod) {
		this.nacionalidadCod = nacionalidadCod;
	}
	public Integer getClienteCod() {
		return clienteCod;
	}
	public void setClienteCod(Integer clienteCod) {
		this.clienteCod = clienteCod;
	}
	public String getClienteDesc() {
		return clienteDesc;
	}
	public void setClienteDesc(String clienteDesc) {
		this.clienteDesc = clienteDesc;
	}
	public Integer getRadioCod() {
		return radioCod;
	}
	public void setRadioCod(Integer radioCod) {
		this.radioCod = radioCod;
	}
	public String getRadioDesc() {
		return radioDesc;
	}
	public void setRadioDesc(String radioDesc) {
		this.radioDesc = radioDesc;
	}
	public String getDireccionDesc() {
		return direccionDesc;
	}
	public void setDireccionDesc(String direccionDesc) {
		this.direccionDesc = direccionDesc;
	}
	public Integer getDireccionNum() {
		return direccionNum;
	}
	public void setDireccionNum(Integer direccionNum) {
		this.direccionNum = direccionNum;
	}
	public String getDireccionUnidad() {
		return direccionUnidad;
	}
	public void setDireccionUnidad(String direccionUnidad) {
		this.direccionUnidad = direccionUnidad;
	}
	public String getDireccionPiso() {
		return direccionPiso;
	}
	public void setDireccionPiso(String direccionPiso) {
		this.direccionPiso = direccionPiso;
	}
	public Integer getLocalidadCod() {
		return localidadCod;
	}
	public void setLocalidadCod(Integer localidadCod) {
		this.localidadCod = localidadCod;
	}
	public String getLocalidadDesc() {
		return localidadDesc;
	}
	public void setLocalidadDesc(String localidadDesc) {
		this.localidadDesc = localidadDesc;
	}
	public Integer getDepartamentoCod() {
		return departamentoCod;
	}
	public void setDepartamentoCod(Integer departamentoCod) {
		this.departamentoCod = departamentoCod;
	}
	public String getDepartamentoDesc() {
		return departamentoDesc;
	}
	public void setDepartamentoDesc(String departamentoDesc) {
		this.departamentoDesc = departamentoDesc;
	}
	public Integer getPaisCod() {
		return paisCod;
	}
	public void setPaisCod(Integer paisCod) {
		this.paisCod = paisCod;
	}
	public String getPaisDesc() {
		return paisDesc;
	}
	public void setPaisDesc(String paisDesc) {
		this.paisDesc = paisDesc;
	}
	public String getAnulacionCorrida() {
		return anulacionCorrida;
	}
	public void setAnulacionCorrida(String anulacionCorrida) {
		this.anulacionCorrida = anulacionCorrida;
	}
	public Integer getDiaVencimiento() {
		return diaVencimiento;
	}
	public void setDiaVencimiento(Integer diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}
	public String getTieneDeuda() {
		return tieneDeuda;
	}
	public void setTieneDeuda(String tieneDeuda) {
		this.tieneDeuda = tieneDeuda;
	}

	public void setValidaComunicaciones(String validaComunicaciones) {
		this.validaComunicaciones = validaComunicaciones;
	}
	public String getValidaComunicaciones() {
		return validaComunicaciones;
	}	
	
}
