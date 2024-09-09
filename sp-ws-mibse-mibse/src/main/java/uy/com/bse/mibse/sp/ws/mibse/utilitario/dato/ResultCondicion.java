package uy.com.bse.mibse.sp.ws.mibse.utilitario.dato;

public class ResultCondicion extends ResultGenerico {

    private static final long serialVersionUID = 3653223981826198375L;

    private Boolean resultado;

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    public void setResultadoString(String resultado) {
        this.resultado = "S".equalsIgnoreCase(resultado) ? Boolean.TRUE : Boolean.FALSE;
    }
}
