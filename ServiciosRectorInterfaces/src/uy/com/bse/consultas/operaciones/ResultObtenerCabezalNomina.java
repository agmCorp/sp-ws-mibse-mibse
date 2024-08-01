package uy.com.bse.consultas.operaciones;

import uy.com.bse.consultas.entidades.CabezalNomina;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerCabezalNomina extends ResultGenerico{

	private static final long serialVersionUID = 1053006948145293687L;
	
    private CabezalNomina cabezalNomina;

	public CabezalNomina getCabezalNomina() {
		return cabezalNomina;
	}

	public void setCabezalNomina(CabezalNomina cabezalNomina) {
		this.cabezalNomina = cabezalNomina;
	}
	
	
	
}
