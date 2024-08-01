package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Anexo implements Serializable{
	private ArrayList<Clausula> clausulas= new ArrayList<Clausula>();
	private Integer codigo;
	private String descripcion;
	
	public ArrayList<Clausula> getClausulas() {
		return clausulas;
	}
	public void setClausulas(ArrayList<Clausula> clausulas) {
		this.clausulas = clausulas;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setUnaClausula(Clausula item) {
		this.clausulas.add(item);
	}
}
