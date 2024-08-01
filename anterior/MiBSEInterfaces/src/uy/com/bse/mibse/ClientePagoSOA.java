package uy.com.bse.mibse;

public class ClientePagoSOA extends ClientePago {
	private static final long serialVersionUID = 7542324321633455098L;

	// Información específica para SOA
	private Integer sucursal;
	private Integer nroEndoso;
	private Long nroSecuenciaPoliza;
	private String mail;
	private String matricula;
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getChasis() {
		return chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}
}
