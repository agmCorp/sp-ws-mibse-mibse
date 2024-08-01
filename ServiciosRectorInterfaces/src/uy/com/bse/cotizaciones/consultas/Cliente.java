package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.maestro.personas.comunicaciones.ComunicacionEC;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class Cliente extends ResultGenerico {
	
	private String codCliente;
	private String apellidoRazon; 
	private String nombre;
	private String tipoDoc;
	private String descTipoDoc;
	private String numDoc;
	private String rut;
	private String direccion;
	private String fechaBaja;
	private String anulacionCorrida;
	private String numPersona;
	private String nacionalidad;
	private String nombreCompleto;
	private DireccionEC direccionCompleta;
	private String descripTipoDoc;
	private ComunicacionEC tel;
	private ComunicacionEC cel;
	private ComunicacionEC mail;
	private String tipoPersona;
	private String descripPersona;
	private Integer diaVencimiento;
	private String esCliente;
	private Integer codProfesion;
	private String profesion;
	private String esPep;
	private String fechaPep;
	private String tieneDeuda;
	private String validaComunicaciones;
	private String noTieneDocumento;
	private ComunicacionEC mailFacturas;

		
	public String getEsPep() {
		return esPep;
	}
	public void setEsPep(String esPep) {
		this.esPep = esPep;
	}
	public String getFechaPep() {
		return fechaPep;
	}
	public void setFechaPep(String fechaPep) {
		this.fechaPep = fechaPep;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}	
	public String getApellidoRazon() {
		return apellidoRazon;
	}
	public void setApellidoRazon(String apellidoRazon) {
		this.apellidoRazon = apellidoRazon;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getAnulacionCorrida() {
		return anulacionCorrida;
	}
	public void setAnulacionCorrida(String anulacionCorrida) {
		this.anulacionCorrida = anulacionCorrida;
	}
	public String getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(String numPersona) {
		this.numPersona = numPersona;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}	
	public DireccionEC getDireccionCompleta() {
		return direccionCompleta;
	}
	public void setDireccionCompleta(DireccionEC direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}
	public String getDescripTipoDoc() {
		return descripTipoDoc;
	}
	public void setDescripTipoDoc(String descripTipoDoc) {
		this.descripTipoDoc = descripTipoDoc;
	}
	public ComunicacionEC getTel() {
		return tel;
	}
	public void setTel(ComunicacionEC tel) {
		this.tel = tel;
	}
	public ComunicacionEC getCel() {
		return cel;
	}
	public void setCel(ComunicacionEC cel) {
		this.cel = cel;
	}
	public ComunicacionEC getMail() {
		return mail;
	}
	public void setMail(ComunicacionEC mail) {
		this.mail = mail;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public String getDescripPersona() {
		return descripPersona;
	}
	public void setDescripPersona(String descripPersona) {
		this.descripPersona = descripPersona;
	}
	public Integer getDiaVencimiento() {
		return diaVencimiento;
	}
	public void setDiaVencimiento(Integer diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}
	public String getDescTipoDoc() {
		return descTipoDoc;
	}
	public void setDescTipoDoc(String descTipoDoc) {
		this.descTipoDoc = descTipoDoc;
	}
	public String getEsCliente() {
		return esCliente;
	}
	public void setEsCliente(String esCliente) {
		this.esCliente = esCliente;
	}
	public Integer getCodProfesion() {
		return codProfesion;
	}
	public void setCodProfesion(Integer codProfesion) {
		this.codProfesion = codProfesion;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getTieneDeuda() {
		return tieneDeuda;
	}
	public void setTieneDeuda(String tieneDeuda) {
		this.tieneDeuda = tieneDeuda;
	}
	public String getValidaComunicaciones() {
		return validaComunicaciones;
	}
	public void setValidaComunicaciones(String validaComunicaciones) {
		this.validaComunicaciones = validaComunicaciones;
	}
	public String getNoTieneDocumento() {
		return noTieneDocumento;
	}
	public void setNoTieneDocumento(String noTieneDocumento) {
		this.noTieneDocumento = noTieneDocumento;
	}
	public ComunicacionEC getMailFacturas() {
		return mailFacturas;
	}
	public void setMailFacturas(ComunicacionEC mailFacturas) {
		this.mailFacturas = mailFacturas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codCliente == null) ? 0 : codCliente.hashCode());
		result = prime * result
				+ ((numPersona == null) ? 0 : numPersona.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codCliente == null) {
			if (other.codCliente != null)
				return false;
		} else if (!codCliente.equals(other.codCliente))
			return false;
		if (numPersona == null) {
			if (other.numPersona != null)
				return false;
		} else if (!numPersona.equals(other.numPersona))
			return false;
		return true;
	}
}
