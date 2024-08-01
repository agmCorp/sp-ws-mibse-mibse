package uy.com.bse.polizas.entidades;
import java.util.ArrayList;

import uy.com.bse.polizas.consultas.InfoSiniestro;

public class Siniestro extends DatosBasicosSiniestro{
	private String descripRamo;
	private Integer numNotificacion;
	private String fechaNotificacion;
	private String descripNotificador;
	private String descripPremioCero;
	private String fechaDeclaracion;
	private String fechaOcurrencia;
	private String horaOcurrencia;
	private String fechaRegistro;
	private String serieDenuncia;
	private Integer numDenuncia;
	private String facultativo;
	private String siniestroGraciable;
	private String codNacionalidad;
	private Integer codCliente;
	private String descripCliente;
	private String codCausaSiniestro;
	private String descripCausa;
	private String descripPlan;
	private String codTipoSiniestro;
	private String descripTipoSiniestro;
	private String lugarOcurrencia;
	private Integer codCodigoPostal;
	private String descripLocalidad;
	private String descripProvincia;
	private String descripSiniestro;
	private Integer codSucursal;
	private String descripSucursal;
	private Integer codSucursalDenuncia;
	private String descripSucursalDenuncia;
	private String usuario;
	private String juzgado;
	private String comisaria;
	private Integer numActa;
	private String financiera;
	private String tecnica;
	private ArrayList<InfoSiniestro> infoSiniestro = new ArrayList<InfoSiniestro>();
	
	public String getDescripRamo() {
		return descripRamo;
	}
	public void setDescripRamo(String descripRamo) {
		this.descripRamo = descripRamo;
	}
	public Integer getNumNotificacion() {
		return numNotificacion;
	}
	public void setNumNotificacion(Integer numNotificacion) {
		this.numNotificacion = numNotificacion;
	}
	public String getFechaNotificacion() {
		return fechaNotificacion;
	}
	public void setFechaNotificacion(String fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	public String getDescripNotificador() {
		return descripNotificador;
	}
	public void setDescripNotificador(String descripNotificador) {
		this.descripNotificador = descripNotificador;
	}
	public String getDescripPremioCero() {
		return descripPremioCero;
	}
	public void setDescripPremioCero(String descripPremioCero) {
		this.descripPremioCero = descripPremioCero;
	}
	public String getFechaDeclaracion() {
		return fechaDeclaracion;
	}
	public void setFechaDeclaracion(String fechaDeclaracion) {
		this.fechaDeclaracion = fechaDeclaracion;
	}
	public String getFechaOcurrencia() {
		return fechaOcurrencia;
	}
	public void setFechaOcurrencia(String fechaOcurrencia) {
		this.fechaOcurrencia = fechaOcurrencia;
	}
	public String getHoraOcurrencia() {
		return horaOcurrencia;
	}
	public void setHoraOcurrencia(String horaOcurrencia) {
		this.horaOcurrencia = horaOcurrencia;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getSerieDenuncia() {
		return serieDenuncia;
	}
	public void setSerieDenuncia(String serieDenuncia) {
		this.serieDenuncia = serieDenuncia;
	}
	public Integer getNumDenuncia() {
		return numDenuncia;
	}
	public void setNumDenuncia(Integer numDenuncia) {
		this.numDenuncia = numDenuncia;
	}
	public String getFacultativo() {
		return facultativo;
	}
	public void setFacultativo(String facultativo) {
		this.facultativo = facultativo;
	}
	public String getSiniestroGraciable() {
		return siniestroGraciable;
	}
	public void setSiniestroGraciable(String siniestroGraciable) {
		this.siniestroGraciable = siniestroGraciable;
	}
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	public Integer getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}
	public String getDescripCliente() {
		return descripCliente;
	}
	public void setDescripCliente(String descripCliente) {
		this.descripCliente = descripCliente;
	}
	public String getCodCausaSiniestro() {
		return codCausaSiniestro;
	}
	public void setCodCausaSiniestro(String codCausaSiniestro) {
		this.codCausaSiniestro = codCausaSiniestro;
	}
	public String getDescripCausa() {
		return descripCausa;
	}
	public void setDescripCausa(String descripCausa) {
		this.descripCausa = descripCausa;
	}
	public String getDescripPlan() {
		return descripPlan;
	}
	public void setDescripPlan(String descripPlan) {
		this.descripPlan = descripPlan;
	}
	public String getCodTipoSiniestro() {
		return codTipoSiniestro;
	}
	public void setCodTipoSiniestro(String codTipoSiniestro) {
		this.codTipoSiniestro = codTipoSiniestro;
	}
	public String getDescripTipoSiniestro() {
		return descripTipoSiniestro;
	}
	public void setDescripTipoSiniestro(String descripTipoSiniestro) {
		this.descripTipoSiniestro = descripTipoSiniestro;
	}
	public String getLugarOcurrencia() {
		return lugarOcurrencia;
	}
	public void setLugarOcurrencia(String lugarOcurrencia) {
		this.lugarOcurrencia = lugarOcurrencia;
	}
	public Integer getCodCodigoPostal() {
		return codCodigoPostal;
	}
	public void setCodCodigoPostal(Integer codCodigoPostal) {
		this.codCodigoPostal = codCodigoPostal;
	}
	public String getDescripLocalidad() {
		return descripLocalidad;
	}
	public void setDescripLocalidad(String descripLocalidad) {
		this.descripLocalidad = descripLocalidad;
	}
	public String getDescripProvincia() {
		return descripProvincia;
	}
	public void setDescripProvincia(String descripProvincia) {
		this.descripProvincia = descripProvincia;
	}
	public String getDescripSiniestro() {
		return descripSiniestro;
	}
	public void setDescripSiniestro(String descripSiniestro) {
		this.descripSiniestro = descripSiniestro;
	}
	public Integer getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(Integer codSucursal) {
		this.codSucursal = codSucursal;
	}
	public String getDescripSucursal() {
		return descripSucursal;
	}
	public void setDescripSucursal(String descripSucursal) {
		this.descripSucursal = descripSucursal;
	}
	public Integer getCodSucursalDenuncia() {
		return codSucursalDenuncia;
	}
	public void setCodSucursalDenuncia(Integer codSucursalDenuncia) {
		this.codSucursalDenuncia = codSucursalDenuncia;
	}
	public String getDescripSucursalDenuncia() {
		return descripSucursalDenuncia;
	}
	public void setDescripSucursalDenuncia(String descripSucursalDenuncia) {
		this.descripSucursalDenuncia = descripSucursalDenuncia;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getJuzgado() {
		return juzgado;
	}
	public void setJuzgado(String juzgado) {
		this.juzgado = juzgado;
	}
	public String getComisaria() {
		return comisaria;
	}
	public void setComisaria(String comisaria) {
		this.comisaria = comisaria;
	}
	public Integer getNumActa() {
		return numActa;
	}
	public void setNumActa(Integer numActa) {
		this.numActa = numActa;
	}
	public String getFinanciera() {
		return financiera;
	}
	public void setFinanciera(String financiera) {
		this.financiera = financiera;
	}
	public String getTecnica() {
		return tecnica;
	}
	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}	
	public ArrayList<InfoSiniestro> getInfoSiniestro() {
		return infoSiniestro;
	}
	public void setInfoSiniestro(ArrayList<InfoSiniestro> infoSiniestro) {
		this.infoSiniestro = infoSiniestro;
	}
	public void setUnInfoSiniestro(InfoSiniestro item){
		this.infoSiniestro.add(item);
	}
	
}
