package uy.com.bse.recuotificacion;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.cotizaciones.lovs.MedioPago;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarDetectarEndoso extends ResultGenerico {

	private static final long serialVersionUID = 3057026544005967126L;
	private ArrayList<BienCert> bienes = new ArrayList<BienCert>();
	private DatosBanco datosBancarios = new DatosBanco();
	private MedioPago medioPago = new MedioPago();
	private String fechaEmision;
	private OrigenPagoEndoso origenPago = new OrigenPagoEndoso();
	private ArrayList<MedioPago> mediosPago = new ArrayList<MedioPago>();
	private ArrayList<MedioPagoOrigenes> origenesXMedioPago= new  ArrayList<MedioPagoOrigenes>();

	public ArrayList<BienCert> getBienes() {
		return bienes;
	}

	public void setBienes(ArrayList<BienCert> bienes) {
		this.bienes = bienes;
	}

	public void setUnBien(BienCert bienCert) {
		this.bienes.add(bienCert);
	}

	public DatosBanco getDatosBancarios() {
		return datosBancarios;
	}

	public void setDatosBancarios(DatosBanco datosBancarios) {
		this.datosBancarios = datosBancarios;
	}

	public ArrayList<MedioPago> getMediosPago() {
		return mediosPago;
	}

	public void setMediosPago(ArrayList<MedioPago> mediosPago) {
		this.mediosPago = mediosPago;
	}

	public void setUnMedioPago(MedioPago medio, ArrayList<OrigenPagoEndoso> origenPago){
		this.mediosPago.add(medio);
		MedioPagoOrigenes mpo= new MedioPagoOrigenes();
		mpo.setMedioPago(medio);
		mpo.setOrigenesPago(origenPago);
		origenesXMedioPago.add(mpo);
	}


	public MedioPago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public OrigenPagoEndoso getOrigenPago() {
		return origenPago;
	}

	public void setOrigenPago(OrigenPagoEndoso origenPago) {
		this.origenPago = origenPago;
	}
	
	public ArrayList<OrigenPagoEndoso> obtenerOrigenesPago(Integer posicion){
		ArrayList<OrigenPagoEndoso> resultado =origenesXMedioPago.get(posicion).getOrigenesPago();
		return resultado;	
	}

	public ArrayList<MedioPagoOrigenes> getOrigenesXMedioPago() {
		return origenesXMedioPago;
	}

	public void setOrigenesXMedioPago(ArrayList<MedioPagoOrigenes> origenesXMedioPago) {
		this.origenesXMedioPago = origenesXMedioPago;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
}
