
package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;


public class ParamLogActividadMibseWsExt  extends ParamGenerico
{

  
	/**
	 * 
	 */
	private static final long serialVersionUID = -7828205453233272566L;
	private String detalle;
	private String evento;

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String value) {
        this.detalle = value;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String value) {
        this.evento = value;
    }

}
