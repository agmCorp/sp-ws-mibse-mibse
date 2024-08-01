package uy.com.bse.cotizadores.pymes;



import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamNuevaCotizacionPymes extends ParamGenerico {
	
	private static final long serialVersionUID = 376889507953801770L;
	private String codProductor;

	public String getCodProductor() {
		return codProductor;
	}

	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}
	
}
