package uy.com.bse.prestamosrentistas;

import java.util.List;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarPersonaPVP extends ResultGenerico {
	private static final long serialVersionUID = -2883399705471951607L;

	private List<Renta> rentaList;
	private List<RentaInhabilit> rentaInhabilitList;
	private String nombre;

	public List<Renta> getRentaList() {
		return rentaList;
	}

	public void setRentaList(List<Renta> rentaList) {
		this.rentaList = rentaList;
	}

	public List<RentaInhabilit> getRentaInhabilitList() {
		return rentaInhabilitList;
	}

	public void setRentaInhabilitList(List<RentaInhabilit> rentaInhabilitList) {
		this.rentaInhabilitList = rentaInhabilitList;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
