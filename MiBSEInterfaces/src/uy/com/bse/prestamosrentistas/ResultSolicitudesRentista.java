package uy.com.bse.prestamosrentistas;

import java.util.List;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultSolicitudesRentista extends ResultGenerico {
	private static final long serialVersionUID = -1628935595488523604L;
	
	private List<SolicitudRentista> solicitudRentistaList;

	public List<SolicitudRentista> getSolicitudRentistaList() {
		return solicitudRentistaList;
	}

	public void setSolicitudRentistaList(List<SolicitudRentista> solicitudRentistaList) {
		this.solicitudRentistaList = solicitudRentistaList;
	}
}
