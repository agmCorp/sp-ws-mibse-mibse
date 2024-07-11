package com.bse.negocio;

import com.bse.negocio.parametros.IParametrosTienda;
import com.bse.negocio.parametros.ParametrosMgrTienda;
import com.bse.negocio.comun.EComunMgrTienda;
import com.bse.negocio.comun.IEComunTienda;
import com.bse.negocio.ebicis.EBiciMgrTienda;
import com.bse.negocio.ebicis.IEBiciTienda;
import com.bse.negocio.edepor.EDeporMgrTienda;
import com.bse.negocio.edepor.IEDeporTienda;
import com.bse.negocio.eindivi.EIndiviMgrTienda;
import com.bse.negocio.eindivi.IEIndiviTienda;
import com.bse.negocio.eoperson.EOPersonalMgrTienda;
import com.bse.negocio.eoperson.IEOPersonalTienda;
import com.bse.negocio.esoa.ESoaMgrTienda;
import com.bse.negocio.esoa.IESoaTienda;
import com.bse.negocio.eviajeros.EViajerosMgrTienda;
import com.bse.negocio.eviajeros.IEViajerosTienda;
import com.bse.negocio.seguridad.ISeguridadTienda;
import com.bse.negocio.seguridad.SeguridadMgrTienda;;


public class FabricaNegocioTienda {

    private static FabricaNegocioTienda instancia = null;

    private FabricaNegocioTienda() {
    }

    public static FabricaNegocioTienda getFabricaNegocio() {
        synchronized (FabricaNegocioTienda.class) {
            if (instancia == null)
                instancia = new FabricaNegocioTienda();
        }
        return instancia;
    }

    public ISeguridadTienda getSeguridadMgr() {
        return SeguridadMgrTienda.getSeguridadMgr();
    }

    public IESoaTienda getESoaMgr() {
        return ESoaMgrTienda.getESoaMgr();
    }

    public IEBiciTienda getEBiciMgr() {
        return EBiciMgrTienda.getEBiciMgr();
    }

    public IEViajerosTienda getEViajerosMgr() {
        return EViajerosMgrTienda.getEViajerosMgr();
    }

  public IParametrosTienda getParametrosMgr() {
        return ParametrosMgrTienda.getParametrosMgr();
  }

    public IEIndiviTienda getEIndiviMgr() {
        return EIndiviMgrTienda.getEIndiviMgr();
    }

  public IEDeporTienda getEDeporMgr() {
        return EDeporMgrTienda.getEDeporMgr();
  }

    public IEOPersonalTienda getEOPersonalMgr() {
        return EOPersonalMgrTienda.getEOPersonalMgr();
    }

    public IEComunTienda getEComunMgr() {
        return EComunMgrTienda.getEComunMgr();
    }
}
