package uy.com.bse.prestamosrentistas;

import java.io.Serializable;

public class SolicitudRentista implements Serializable {
	private static final long serialVersionUID = 5457729478663801346L;

	private Integer nuSolicitud;
	private String estadoSolicitud;
	private String fechaSolicitud;
	
	public Integer getNuSolicitud() {
		return nuSolicitud;
	}
	public void setNuSolicitud(Integer nuSolicitud) {
		this.nuSolicitud = nuSolicitud;
	}
	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	
}
