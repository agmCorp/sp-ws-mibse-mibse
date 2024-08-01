package uy.com.bse.maestro.personas.personas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.maestro.personas.comunicaciones.ComunicacionEC;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAltaCliente extends ParamGenerico{
	
	private static final long serialVersionUID = 4417269206330734319L;
	private String tipoDocumento;
	private String numDocumento;
	private String apellido;
	private String nombre;
	private Integer codDireccion;
	private String sexo;
	private String fechaNacimiento;
	private String codEstadoCivil;
	private Integer codProfesion;
	private Integer codPersona;
	private String rut;
	private String razonSocial;
	private String fechaInicio;
	private String codOrganismo;
	private Integer codActividad;
	private Integer tipoPersona;
	
	private ArrayList<ComunicacionEC> comunicaciones = new ArrayList<ComunicacionEC>();
	
	private ArrayList<DireccionEC> domicilios = new ArrayList<DireccionEC>();
	
	private ArrayList<DatosBanco> tarjetas = new ArrayList<DatosBanco>();

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodDireccion() {
		return codDireccion;
	}

	public void setCodDireccion(Integer codDireccion) {
		this.codDireccion = codDireccion;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCodEstadoCivil() {
		return codEstadoCivil;
	}

	public void setCodEstadoCivil(String codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}

	public Integer getCodProfesion() {
		return codProfesion;
	}

	public void setCodProfesion(Integer codProfesion) {
		this.codProfesion = codProfesion;
	}

	public Integer getCodPersona() {
		return codPersona;
	}

	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getCodOrganismo() {
		return codOrganismo;
	}

	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	public Integer getCodActividad() {
		return codActividad;
	}

	public void setCodActividad(Integer codActividad) {
		this.codActividad = codActividad;
	}

	public Integer getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(Integer tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public ArrayList<ComunicacionEC> getComunicaciones() {
		return comunicaciones;
	}

	public void setComunicaciones(ArrayList<ComunicacionEC> comunicaciones) {
		this.comunicaciones = comunicaciones;
	}

	public ArrayList<DireccionEC> getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(ArrayList<DireccionEC> domicilios) {
		this.domicilios = domicilios;
	}

	public ArrayList<DatosBanco> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(ArrayList<DatosBanco> tarjetas) {
		this.tarjetas = tarjetas;
	}
	
	public void setUno(DatosBanco tarjeta){
		tarjetas.add(tarjeta);
		
	}
	
	public void setUno(DireccionEC direccion){
		domicilios.add(direccion);
		
	}
	
	public void setUno(ComunicacionEC comunicacion){
		comunicaciones.add(comunicacion);
		
	}
	
	

	
}
