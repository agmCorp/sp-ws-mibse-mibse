package uy.com.bse.mibse;

import java.util.List;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDetallesMontosSiniestros extends ResultGenerico {
	private static final long serialVersionUID = 3296331594272535551L;
	
	private List<DetalleMontoSiniestro> DetalleMontoSiniestroList;

	public List<DetalleMontoSiniestro> getDetalleMontoSiniestroList() {
		return DetalleMontoSiniestroList;
	}

	public void setDetalleMontoSiniestroList(List<DetalleMontoSiniestro> detalleMontoSiniestroList) {
		DetalleMontoSiniestroList = detalleMontoSiniestroList;
	}
}
