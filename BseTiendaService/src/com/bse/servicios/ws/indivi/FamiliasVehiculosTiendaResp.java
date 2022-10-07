package com.bse.servicios.ws.indivi;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class FamiliasVehiculosTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemCodiguera> familiasDeVehiculos;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public FamiliasVehiculosTiendaResp() {
        super();
        this.familiasDeVehiculos = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param familiasDeVehiculos
     * @param codigoError
     * @param descripcionError
     */
    public FamiliasVehiculosTiendaResp(List<ItemCodiguera> familiasDeVehiculos, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.familiasDeVehiculos = familiasDeVehiculos;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public FamiliasVehiculosTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.familiasDeVehiculos = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @XmlElementWrapper(name = "familiasVehiculosList")
    @XmlElement(name="familiaVehiculo")
    /**
     * @return the familiasDeVehiculos
     */
    public List<ItemCodiguera> getFamiliasDeVehiculos() {
        return familiasDeVehiculos;
    }
    /**
     * @param familiasDeVehiculos the familiasDeVehiculos to set
     */
    public void setFamiliasDeVehiculos(List<ItemCodiguera> familiasDeVehiculos) {
        this.familiasDeVehiculos = familiasDeVehiculos;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
    *
    */
    @Override
    public String toString() {
        String logueo = null;
        String auxFamiliasDeVehiculos = null;
        String auxCodError  = null;
        String auxDescError = null;;
        String auxError     = "null";

        if        (this.getFamiliasDeVehiculos() == null)   { auxFamiliasDeVehiculos = "[null]";
        } else if (this.getFamiliasDeVehiculos().isEmpty()) { auxFamiliasDeVehiculos = "[VACIA]";
        } else { auxFamiliasDeVehiculos = this.getFamiliasDeVehiculos().toString(); }

        if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
        if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
        if ( (auxCodError != null) || (auxDescError != null) ) {
            auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
        }

        logueo = "FamiliasDeVehiculos=" + auxFamiliasDeVehiculos + " - Error=[" + auxError + "]";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
