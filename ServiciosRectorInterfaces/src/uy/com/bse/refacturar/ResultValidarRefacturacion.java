package uy.com.bse.refacturar;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.Bien;
import uy.com.bse.polizas.entidades.DetalleRefacturacionPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarRefacturacion extends ResultGenerico{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4139085814876011783L;

	private String habilitoEmitir;
	private String habilitoModificar;
	private String habilitoRefacturar;
	private String habilitoFacturacion;
	private DetalleRefacturacionPoliza detallePoliza;
	private ArrayList<Bien> bienes = new ArrayList<Bien>();
	

	public ArrayList<Bien> getBienes() {
		return bienes;
	}
	public void setBienes(ArrayList<Bien> bienes) {
		this.bienes = bienes;
	}
	
	
	public DetalleRefacturacionPoliza getDetallePoliza() {
		return detallePoliza;
	}
	public void setDetallePoliza(DetalleRefacturacionPoliza detallePoliza) {
		this.detallePoliza = detallePoliza;
	}
	public String getHabilitoEmitir() {
		return habilitoEmitir;
	}
	public void setHabilitoEmitir(String habilitoEmitir) {
		this.habilitoEmitir = habilitoEmitir;
	}
	public String getHabilitoModificar() {
		return habilitoModificar;
	}
	public void setHabilitoModificar(String habilitoModificar) {
		this.habilitoModificar = habilitoModificar;
	}
	public String getHabilitoRefacturar() {
		return habilitoRefacturar;
	}
	public void setHabilitoRefacturar(String habilitoRefacturar) {
		this.habilitoRefacturar = habilitoRefacturar;
	}
	public String getHabilitoFacturacion() {
		return habilitoFacturacion;
	}
	public void setHabilitoFacturacion(String habilitoFacturacion) {
		this.habilitoFacturacion = habilitoFacturacion;
	}
	
	public void setUnDato(Bien bien) {
		bienes.add(bien);
	}
}
