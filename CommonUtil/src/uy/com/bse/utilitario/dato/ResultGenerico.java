package uy.com.bse.utilitario.dato;

import java.io.Serializable;

public class ResultGenerico implements Serializable{

	private static final long serialVersionUID = 2833548930813377945L;
	private Boolean hayError;
	private ServiciosError error;
	
	public ResultGenerico(){
		this.hayError = Boolean.FALSE;		
	}

	public Boolean getHayError() {
		return hayError;
	}

	public void setHayError(Boolean hayError) {
		this.hayError = hayError;
	}

	public ServiciosError getError() {
		return error;
	}

	public void setError(ServiciosError error) {
		this.error = error;
	}
}
