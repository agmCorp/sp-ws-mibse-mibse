package com.bse.negocio;

import com.bse.negocio.parametros.IParametros;
import com.bse.negocio.parametros.ParametrosMgr;
import com.bse.negocio.ebicis.EBiciMgr;
import com.bse.negocio.ebicis.IEBici;
import com.bse.negocio.edepor.EDeporMgr;
import com.bse.negocio.edepor.IEDepor;
import com.bse.negocio.eindivi.EIndiviMgr;
import com.bse.negocio.eindivi.IEIndivi;
import com.bse.negocio.esoa.ESoaMgr;
import com.bse.negocio.esoa.IESoa;
import com.bse.negocio.eviajeros.EViajerosMgr;
import com.bse.negocio.eviajeros.IEViajeros;
import com.bse.negocio.seguridad.ISeguridad;
import com.bse.negocio.seguridad.SeguridadMgr;;


public class FabricaNegocio {

    private static FabricaNegocio instancia = null;

    private FabricaNegocio() {
    }

    public static FabricaNegocio getFabricaNegocio() {
        synchronized (FabricaNegocio.class) {
            if (instancia == null)
                instancia = new FabricaNegocio();
        }
        return instancia;
    }

    public ISeguridad getSeguridadMgr() {
        return SeguridadMgr.getSeguridadMgr();
    }

    public IESoa getESoaMgr() {
        return ESoaMgr.getESoaMgr();
    }

    public IEBici getEBiciMgr() {
        return EBiciMgr.getEBiciMgr();
    }
    
    public IEViajeros getEViajerosMgr() {
        return EViajerosMgr.getEViajerosMgr();
    }
    
	public IParametros getParametrosMgr() {
        return ParametrosMgr.getParametrosMgr();
	}    
    
    public IEIndivi getEIndiviMgr() {
        return EIndiviMgr.getEIndiviMgr();
    }

	public IEDepor getEDeporMgr() {
        return EDeporMgr.getEDeporMgr();
	}
    
}
