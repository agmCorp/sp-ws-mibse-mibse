package uy.com.bse.consultas.operaciones;

import java.util.ArrayList;

import uy.com.bse.consultas.entidades.DatoPolizaFlotante;
import uy.com.bse.consultas.entidades.Movimiento;
import uy.com.bse.consultas.entidades.Resumen;
import uy.com.bse.consultas.entidades.Saldo;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerMovimientosComision extends ResultGenerico{

	private static final long serialVersionUID = 1053006948145293687L;
	
	
	private ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
	private ArrayList<Resumen> resumen = new ArrayList<Resumen>();
	private Saldo saldo;
	private Double montoInicial;
	private Double montoAcumulado;
	private Double montoTotal;
	
	
	
	public ArrayList<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(ArrayList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public ArrayList<Resumen> getResumen() {
		return resumen;
	}

	public void setResumen(ArrayList<Resumen> resumen) {
		this.resumen = resumen;
	}

	public Saldo getSaldo() {
		return saldo;
	}

	public void setSaldo(Saldo saldo) {
		this.saldo = saldo;
	}

	public Double getMontoInicial() {
		return montoInicial;
	}

	public void setMontoInicial(Double montoInicial) {
		this.montoInicial = montoInicial;
	}

	public Double getMontoAcumulado() {
		return montoAcumulado;
	}

	public void setMontoAcumulado(Double montoAcumulado) {
		this.montoAcumulado = montoAcumulado;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	
	
	public void setUnoMovimiento(Movimiento mov){
		movimientos.add(mov);
	}
	
	public void setUnoResumen(Resumen res){
		resumen.add(res);
	}

	
}
