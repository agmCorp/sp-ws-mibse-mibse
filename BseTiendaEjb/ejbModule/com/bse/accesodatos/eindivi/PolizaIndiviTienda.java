package com.bse.accesodatos.eindivi;

import com.bse.accesodatos.esoa.CategoriaVehiculoTienda;
import com.bse.accesodatos.esoa.MarcaVehiculoTienda;
import com.bse.accesodatos.esoa.PolizaTienda;

public class PolizaIndiviTienda extends PolizaTienda {

    private MarcaVehiculoTienda marcaVehiculo;
    private CategoriaVehiculoTienda categoriaVehiculo;
    private String matriculaVehiculo;
    private String padronVehiculo;
    private String chasisVehiculo;
    private String motorVehiculo;
    private String anioVehiculo;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public PolizaIndiviTienda() {
        super();
        this.marcaVehiculo     = null;
        this.categoriaVehiculo = null;
        this.matriculaVehiculo = null;
        this.padronVehiculo    = null;
        this.chasisVehiculo    = null;
        this.motorVehiculo     = null;
        this.anioVehiculo      = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the marcaVehiculo
     */
    public MarcaVehiculoTienda getMarcaVehiculo() {
        return marcaVehiculo;
    }
    /**
     * @param marcaVehiculo the marcaVehiculo to set
     */
    public void setMarcaVehiculo(MarcaVehiculoTienda marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the categoriaVehiculo
     */
    public CategoriaVehiculoTienda getCategoriaVehiculo() {
        return categoriaVehiculo;
    }
    /**
     * @param categoriaVehiculo the categoriaVehiculo to set
     */
    public void setCategoriaVehiculo(CategoriaVehiculoTienda categoriaVehiculo) {
        this.categoriaVehiculo = categoriaVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the matriculaVehiculo
     */
    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }
    /**
     * @param matriculaVehiculo the matriculaVehiculo to set
     */
    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the padronVehiculo
     */
    public String getPadronVehiculo() {
        return padronVehiculo;
    }
    /**
     * @param padronVehiculo the padronVehiculo to set
     */
    public void setPadronVehiculo(String padronVehiculo) {
        this.padronVehiculo = padronVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the chasisVehiculo
     */
    public String getChasisVehiculo() {
        return chasisVehiculo;
    }
    /**
     * @param chasisVehiculo the chasisVehiculo to set
     */
    public void setChasisVehiculo(String chasisVehiculo) {
        this.chasisVehiculo = chasisVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the motorVehiculo
     */
    public String getMotorVehiculo() {
        return motorVehiculo;
    }
    /**
     * @param motorVehiculo the motorVehiculo to set
     */
    public void setMotorVehiculo(String motorVehiculo) {
        this.motorVehiculo = motorVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the anioVehiculo
     */
    public String getAnioVehiculo() {
        return anioVehiculo;
    }
    /**
     * @param anioVehiculo the anioVehiculo to set
     */
    public void setAnioVehiculo(String anioVehiculo) {
        this.anioVehiculo = anioVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {

        String auxMarcaVehiculo;
        String auxCategoriaVehiculo;
        String auxMatriculaVehiculo;
        String auxPadronVehiculo;
        String auxChasisVehiculo;
        String auxMotorVehiculo;
        String auxAnioVehiculo;
        String auxExtends = getPolizaTiendaString();

        String logueo = null;

        if (this.getMarcaVehiculo() == null) { auxMarcaVehiculo = "null";
        } else { auxMarcaVehiculo = this.getMarcaVehiculo().toString(); }

        if (this.getCategoriaVehiculo() == null) { auxCategoriaVehiculo = "null";
        } else { auxCategoriaVehiculo = this.getCategoriaVehiculo().toString(); }

        if (this.getMatriculaVehiculo() == null) { auxMatriculaVehiculo = "null";
        } else { auxMatriculaVehiculo = this.getMatriculaVehiculo(); }

        if (this.getPadronVehiculo() == null) { auxPadronVehiculo = "null";
        } else { auxPadronVehiculo = this.getPadronVehiculo(); }

        if (this.getChasisVehiculo() == null) { auxChasisVehiculo = "null";
        } else { auxChasisVehiculo = this.getChasisVehiculo(); }

        if (this.getMotorVehiculo() == null) { auxMotorVehiculo = "null";
        } else { auxMotorVehiculo = this.getMotorVehiculo(); }

        if (this.getAnioVehiculo() == null) { auxAnioVehiculo = "null";
        } else { auxAnioVehiculo = this.getAnioVehiculo(); }

        logueo = "PolizaIndivi { "    + auxExtends
               +  " Marca=["          + auxMarcaVehiculo
               + "] Categoria=["      + auxCategoriaVehiculo
               + "] Matricula=["      + auxMatriculaVehiculo
               + "] Padron=["         + auxPadronVehiculo
               + "] Chasis=["         + auxChasisVehiculo
               + "] Motor=["          + auxMotorVehiculo
               + "] Anio=["           + auxAnioVehiculo
               + "] }";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
