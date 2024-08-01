package uy.com.bse.mibse;

public class ClientePagoIndivi extends ClientePago {
	private static final long serialVersionUID = 7678928348363973522L;
	
	// Informaci�n espec�fica para INDIVI
	private Integer sucursal;
	private Integer nroEndoso;
	private Long nroSecuenciaPoliza;
	private String mail;
	private String motor;
	private String chasis;

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

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getChasis() {
		return chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}
}
