package uy.com.bse.mibse;

public class ClientePagoEDeportivas extends ClientePago {
	private static final long serialVersionUID = -4839137559603046862L;
	
	// Información específica para Embarcaciones Deportivas
	private Integer sucursal;
	private Integer nroEndoso;
	private Long nroSecuenciaPoliza;
	private String mail;
	private String nombre;
	private String matricula;

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Integer getNroEndoso() {
		return nroEndoso;
	}

	public void setNroEndoso(Integer nroEndoso) {
		this.nroEndoso = nroEndoso;
	}

	public Long getNroSecuenciaPoliza() {
		return nroSecuenciaPoliza;
	}

	public void setNroSecuenciaPoliza(Long nroSecuenciaPoliza) {
		this.nroSecuenciaPoliza = nroSecuenciaPoliza;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}
