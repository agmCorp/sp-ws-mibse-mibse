package uy.com.bse.dnic;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPersonaPorDoc extends ResultGenerico{

	private static final long serialVersionUID = 7481544709652786107L;
	private ArrayList<Mensaje> errores = new ArrayList<Mensaje>();
	private ArrayList<Mensaje> warnings = new ArrayList<Mensaje>();
	
	private ObjPersona objPersona;

	public ObjPersona getObjPersona() {
		return objPersona;
	}

	public void setObjPersona(ObjPersona objPersona) {
		this.objPersona = objPersona;
	}

	public ArrayList<Mensaje> getErrores() {
		return errores;
	}

	public void setErrores(ArrayList<Mensaje> errores) {
		this.errores = errores;
	}

	public ArrayList<Mensaje> getWarnings() {
		return warnings;
	}

	public void setWarnings(ArrayList<Mensaje> warnings) {
		this.warnings = warnings;
	}

}
