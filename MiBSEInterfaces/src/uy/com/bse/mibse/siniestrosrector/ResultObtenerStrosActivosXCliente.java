package uy.com.bse.mibse.siniestrosrector;

import java.util.List;

import uy.com.bse.utilitario.dato.ResultGenerico;



public class ResultObtenerStrosActivosXCliente extends ResultGenerico {

    
	private static final long serialVersionUID = 7027944572302576043L;
	protected List<ResultStrosActivosXCliente> strosActivosXCliente;
	
	public List<ResultStrosActivosXCliente> getStrosActivosXCliente() {
		return strosActivosXCliente;
	}
	
	public void setStrosActivosXCliente(List<ResultStrosActivosXCliente> strosActivosXCliente) {
		this.strosActivosXCliente = strosActivosXCliente;
	}

}
