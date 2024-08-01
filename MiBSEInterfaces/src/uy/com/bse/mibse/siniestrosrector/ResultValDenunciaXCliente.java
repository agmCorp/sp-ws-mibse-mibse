package uy.com.bse.mibse.siniestrosrector;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValDenunciaXCliente extends ResultGenerico{

	private static final long serialVersionUID = 6033134603484763981L;
	
	
	private String resultado; // Valores S o N


	public String getResultado() {
		return resultado;
	}


	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
		

}