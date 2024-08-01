package uy.com.bse.mibse;

import java.io.Serializable;
import java.util.List;

public class Finiquito implements Serializable {
	private static final long serialVersionUID = 5457729478663801346L;

	private Integer nroFiniquito;
	private Integer nroCompromisoDePago;
	private String estadoFiniquito;
	private String fechaHoraFirmado;
	private String fechaHoraFirmadoFormatoSoloFecha;
	private String fechaHoraFirmadoFormatoSoloHora;
	private List<Siniestro> siniestroList;

	public Integer getNroFiniquito() {
		return nroFiniquito;
	}

	public void setNroFiniquito(Integer nroFiniquito) {
		this.nroFiniquito = nroFiniquito;
	}

	public Integer getNroCompromisoDePago() {
		return nroCompromisoDePago;
	}

	public void setNroCompromisoDePago(Integer nroCompromisoDePago) {
		this.nroCompromisoDePago = nroCompromisoDePago;
	}

	public String getEstadoFiniquito() {
		return estadoFiniquito;
	}

	public void setEstadoFiniquito(String estadoFiniquito) {
		this.estadoFiniquito = estadoFiniquito;
	}

	public String getFechaHoraFirmado() {
		return fechaHoraFirmado;
	}

	public void setFechaHoraFirmado(String fechaHoraFirmado) {
		this.fechaHoraFirmado = fechaHoraFirmado;
	}

	public String getFechaHoraFirmadoFormatoSoloFecha() {
		return fechaHoraFirmadoFormatoSoloFecha;
	}

	public void setFechaHoraFirmadoFormatoSoloFecha(String fechaHoraFirmadoFormatoSoloFecha) {
		this.fechaHoraFirmadoFormatoSoloFecha = fechaHoraFirmadoFormatoSoloFecha;
	}

	public String getFechaHoraFirmadoFormatoSoloHora() {
		return fechaHoraFirmadoFormatoSoloHora;
	}

	public void setFechaHoraFirmadoFormatoSoloHora(String fechaHoraFirmadoFormatoSoloHora) {
		this.fechaHoraFirmadoFormatoSoloHora = fechaHoraFirmadoFormatoSoloHora;
	}

	public List<Siniestro> getSiniestroList() {
		return siniestroList;
	}

	public void setSiniestroList(List<Siniestro> siniestroList) {
		this.siniestroList = siniestroList;
	}
}
