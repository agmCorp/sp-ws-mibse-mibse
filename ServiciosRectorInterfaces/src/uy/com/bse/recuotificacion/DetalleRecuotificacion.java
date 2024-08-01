package uy.com.bse.recuotificacion;

import java.io.Serializable;
import java.util.ArrayList;

public class DetalleRecuotificacion implements Serializable{
	private ArrayList <CuotaRecuotificacion> cuotas = new ArrayList <CuotaRecuotificacion>();
	private Double totalCuotaPura;
	private Double totalRecargos;
	private Double totalIVA;
	private Double totalOtros;
	private Double totalCuotas;
	
	public ArrayList<CuotaRecuotificacion> getCuotas() {
		return cuotas;
	}
	public void setCuotas(ArrayList<CuotaRecuotificacion> cuotas) {
		this.cuotas = cuotas;
	}
	public Double getTotalCuotaPura() {
		return totalCuotaPura;
	}
	public void setTotalCuotaPura(Double totalCuotaPura) {
		this.totalCuotaPura = totalCuotaPura;
	}
	public Double getTotalRecargos() {
		return totalRecargos;
	}
	public void setTotalRecargos(Double totalRecargos) {
		this.totalRecargos = totalRecargos;
	}
	public Double getTotalIVA() {
		return totalIVA;
	}
	public void setTotalIVA(Double totalIVA) {
		this.totalIVA = totalIVA;
	}
	public Double getTotalOtros() {
		return totalOtros;
	}
	public void setTotalOtros(Double totalOtros) {
		this.totalOtros = totalOtros;
	}
	public Double getTotalCuotas() {
		return totalCuotas;
	}
	public void setTotalCuotas(Double totalCuotas) {
		this.totalCuotas = totalCuotas;
	}
	
	public void setUnaCuotaRecuotificacion(CuotaRecuotificacion item){
		this.cuotas.add(item);
	}
	
}
