package com.bse.accesodatos.esoa;

public class PolizaSoaTienda extends PolizaTienda {

    private MarcaVehiculoTienda marcaVehiculo;
    private CategoriaVehiculoTienda categoriaVehiculo;
    private String matriculaVehiculo;
    private String padronVehiculo;
    private String chasisVehiculo;
    private String motorVehiculo;
    private String anioVehiculo;

    public PolizaSoaTienda() {
    }


    public MarcaVehiculoTienda getMarcaVehiculo() {
        return marcaVehiculo;
    }
    public void setMarcaVehiculo(MarcaVehiculoTienda marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }


    public CategoriaVehiculoTienda getCategoriaVehiculo() {
        return categoriaVehiculo;
    }
    public void setCategoriaVehiculo(CategoriaVehiculoTienda categoriaVehiculo) {
        this.categoriaVehiculo = categoriaVehiculo;
    }


    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }
    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }


    public String getPadronVehiculo() {
        return padronVehiculo;
    }
    public void setPadronVehiculo(String padronVehiculo) {
        this.padronVehiculo = padronVehiculo;
    }


    public String getChasisVehiculo() {
        return chasisVehiculo;
    }
    public void setChasisVehiculo(String chasisVehiculo) {
        this.chasisVehiculo = chasisVehiculo;
    }


    public String getMotorVehiculo() {
        return motorVehiculo;
    }
    public void setMotorVehiculo(String motorVehiculo) {
        this.motorVehiculo = motorVehiculo;
    }


    public String getAnioVehiculo() {
        return anioVehiculo;
    }
    public void setAnioVehiculo(String anioVehiculo) {
        this.anioVehiculo = anioVehiculo;
    }


    @Override
    public String toString() {

        String auxMarcaVehiculo;
        String auxCategoriaVehiculo;
        String auxMatriculaVehiculo;
        String auxPadronVehiculo;
        String auxChasisVehiculo;
        String auxMotorVehiculo;
        String auxAnioVehiculo;

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

        String auxExtends = getPolizaTiendaString();

        String logueo = "PolizaSoa { " + auxExtends
                      +  " Marca=["     + auxMarcaVehiculo
                      + "] Categoria=[" + auxCategoriaVehiculo
                      + "] Matricula=[" + auxMatriculaVehiculo
                      + "] Padron=["    + auxPadronVehiculo
                      + "] Chasis=["    + auxChasisVehiculo
                      + "] Motor=["     + auxMotorVehiculo
                      + "] Anio=["      + auxAnioVehiculo
                      + "] }";

        return logueo;
    }

}
