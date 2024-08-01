
package uy.com.bse.mibse.siniestrosrector;

import java.util.ArrayList;
import java.util.List;

import uy.com.bse.utilitario.dato.ResultGenerico;


public class ResultObtenerStroPorParte extends ResultGenerico {

  
	private static final long serialVersionUID = -5674787046471390815L;
	protected List<ResultStroPorParte> stroPorParte;

    public List<ResultStroPorParte> getStroPorParte() {
        if (stroPorParte == null) {
            stroPorParte = new ArrayList<ResultStroPorParte>();
        }
        return this.stroPorParte;
    }

}
