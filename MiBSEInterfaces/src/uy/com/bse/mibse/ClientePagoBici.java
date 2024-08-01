package uy.com.bse.mibse;

public class ClientePagoBici extends ClientePago {
	private static final long serialVersionUID = -8146193626456299296L;
	
	// Información específica para Bicicletas
	private Integer sucursal;
	private Integer nroEndoso;
	private Long nroSecuenciaPoliza;
	private String mail;
	private String nombre;
	private String marca;

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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
}
