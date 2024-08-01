package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDatosParametricos extends ParamGenerico {

	private static final long serialVersionUID = -7537101972450125225L;
	ArrayList<String> filtros= new ArrayList<String>();
	
	public ArrayList<String> getFiltros() {
		return filtros;
	}
	public void setFiltros(ArrayList<String> filtros) {
		this.filtros = filtros;
	}
	
	public String filtrosToString() {
		StringBuffer sb = new StringBuffer();
		for (String filtro : filtros) {
			sb.append('\'');
			sb.append(filtro);
			sb.append('\'');
			sb.append(",");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

}
