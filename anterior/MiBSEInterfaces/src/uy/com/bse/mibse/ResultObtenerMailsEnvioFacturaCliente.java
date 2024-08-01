package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerMailsEnvioFacturaCliente extends ResultGenerico {
	private static final long serialVersionUID = -566111179456801184L;
	
	private String mails;

	public String getMail() {
		return mails;
	}

	public void setMail(String mails) {
		this.mails = mails;
	}

	

}
