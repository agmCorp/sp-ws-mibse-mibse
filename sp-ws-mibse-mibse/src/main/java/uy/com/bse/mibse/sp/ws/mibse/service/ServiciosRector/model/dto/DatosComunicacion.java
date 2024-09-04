package uy.com.bse.mibse.sp.ws.mibse.service.ServiciosRector.model.dto;

//TODO usar lombok
// TODO revisar quién usa este DTO

public class DatosComunicacion {
	private Integer codComunicacion;
	private Integer tipoComunicacion;
	private String valorComunicacion;
	private String descripComunicacion;
	private String fechaActualizacion;
	private String nota;
	private String contactoValido;
	
	public String getContactoValido() {
		return contactoValido;
	}
	public void setContactoValido(String contactoValido) {
		this.contactoValido = contactoValido;
	}
	public Integer getCodComunicacion() {
		return codComunicacion;
	}
	public void setCodComunicacion(Integer codComunicacion) {
		this.codComunicacion = codComunicacion;
	}
	public Integer getTipoComunicacion() {
		return tipoComunicacion;
	}
	public void setTipoComunicacion(Integer tipoComunicacion) {
		this.tipoComunicacion = tipoComunicacion;
	}
	public String getValorComunicacion() {
		return valorComunicacion;
	}
	public void setValorComunicacion(String valorComunicacion) {
		this.valorComunicacion = valorComunicacion;
	}
	public String getDescripComunicacion() {
		return descripComunicacion;
	}
	public void setDescripComunicacion(String descripComunicacion) {
		this.descripComunicacion = descripComunicacion;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}	
}
