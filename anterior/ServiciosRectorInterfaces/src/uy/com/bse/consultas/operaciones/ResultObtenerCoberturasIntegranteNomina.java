package uy.com.bse.consultas.operaciones;

import java.util.ArrayList;

import uy.com.bse.consultas.entidades.CoberturaIntegranteNomina;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerCoberturasIntegranteNomina extends ResultGenerico{

	private static final long serialVersionUID = 1053006948145293687L;
	
	private Double totalPrima;
	private Double totalPremio;
	private Double totalPremioFactura;
	
	
	private ArrayList<CoberturaIntegranteNomina> datos = new ArrayList<CoberturaIntegranteNomina>();
	
	public void setUno(CoberturaIntegranteNomina dato){
		datos.add(dato);
	}

	public ArrayList<CoberturaIntegranteNomina> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<CoberturaIntegranteNomina> datos) {
		this.datos = datos;
	}

	public Double getTotalPrima() {
		return totalPrima;
	}

	public void setTotalPrima(Double totalPrima) {
		this.totalPrima = totalPrima;
	}

	public Double getTotalPremio() {
		return totalPremio;
	}

	public void setTotalPremio(Double totalPremio) {
		this.totalPremio = totalPremio;
	}

	public Double getTotalPremioFactura() {
		return totalPremioFactura;
	}

	public void setTotalPremioFactura(Double totalPremioFactura) {
		this.totalPremioFactura = totalPremioFactura;
	}
	
}
