package uy.com.bse.cotizadores.pymes;

import java.io.Serializable;
import java.util.ArrayList;

public class CoberturaPymes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6403785822567634648L;
	
	private String descCobertura;
	
	private ArrayList<ModuloCobertura> modulosCob = new ArrayList<ModuloCobertura>();
	
	
	public String getDescCobertura() {
		return descCobertura;
	}
	public void setDescCobertura(String descCobertura) {
		this.descCobertura = descCobertura;
	}
	public ArrayList<ModuloCobertura> getModulosCob() {
		return modulosCob;
	}
	
	public void setUnoModuloCobertura(ModuloCobertura moduloCobertura) {
		modulosCob.add(moduloCobertura);
	}
	public void setModulosCob(ArrayList<ModuloCobertura> modulosCob) {
		this.modulosCob = modulosCob;
	}
	
	

}
