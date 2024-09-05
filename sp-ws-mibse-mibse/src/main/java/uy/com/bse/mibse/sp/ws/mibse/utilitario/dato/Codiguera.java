package uy.com.bse.mibse.sp.ws.mibse.utilitario.dato;

import java.io.Serializable;

public class Codiguera implements Serializable {

    private static final long serialVersionUID = 4979428772620794152L;
    private String codigo;
    private String descripcion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
