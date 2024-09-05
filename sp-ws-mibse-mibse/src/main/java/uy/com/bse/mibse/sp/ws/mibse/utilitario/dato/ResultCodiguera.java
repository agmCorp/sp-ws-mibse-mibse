package uy.com.bse.mibse.sp.ws.mibse.utilitario.dato;

import java.util.ArrayList;
import java.util.List;

public class ResultCodiguera extends ResultGenerico {

    private static final long serialVersionUID = 3653223981826198375L;

    private List<Codiguera> items = new ArrayList<Codiguera>();

    private Codiguera valorDefecto;

    public List<Codiguera> getItems() {
        return items;
    }

    public void setItems(List<Codiguera> items) {
        this.items = items;
    }

    public void setUno(Codiguera codiguera) {
        this.items.add(codiguera);
    }

    public Codiguera getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(Codiguera valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

}
