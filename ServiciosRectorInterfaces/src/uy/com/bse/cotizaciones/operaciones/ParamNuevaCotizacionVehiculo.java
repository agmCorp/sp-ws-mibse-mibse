package uy.com.bse.cotizaciones.operaciones;



import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamNuevaCotizacionVehiculo extends ParamGenerico {
	
	private static final long serialVersionUID = 376889507953801770L;
	private String codProductor;

	public String getCodProductor() {
		return codProductor;
	}

	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}
	
}
