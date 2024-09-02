package uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad;
import org.springframework.stereotype.Component;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;

@Component
public final class ResultValidar extends ResultGenerico {

	private static final long serialVersionUID = 1L;
	private String user;

	public ResultValidar() {
		super();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
