package com.bse.negocio.eviajeros;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;

import com.bse.accesodatos.eviajeros.CoberturaMuerteTienda;
import com.bse.accesodatos.eviajeros.CoberturaPrexistentesTienda;
import com.bse.accesodatos.eviajeros.CotizacionViajerosTienda;
import com.bse.accesodatos.eviajeros.PolizaViajerosTienda;
import com.bse.accesodatos.eviajeros.ViajeroTienda;
import com.bse.negocio.comun.BSEExceptionTienda;

public interface IEViajerosTienda {

    public CotizacionViajerosTienda cotizarViajerosAnonimo( EntityManager em,
                                                            String planCobertura,
                                                            int    planPago,
                                                            Date   fechaDesde,
                                                            Date   fechaHasta,
                                                            ArrayList<ViajeroTienda> listaPersonas,
                                                            String extension,
                                                            Date   fechaSalidaPais )                throws Exception, BSEExceptionTienda;

    public PolizaViajerosTienda     emitirViajeros        ( EntityManager em,
                                                            String tipoDocumentoContratante,
                                                            String documentoContratante,
                                                            ArrayList<ViajeroTienda> listaPersonas,
                                                            long   nroCotizacion,
                                                            String consumoFinal,
                                                            String extension,
                                                            Date   fechaSalidaPais )                throws Exception, BSEExceptionTienda;

    public ArrayList<CoberturaPrexistentesTienda> consultaCoberturasPrexistentes(EntityManager em)  throws Exception, BSEExceptionTienda;

    public ArrayList<CoberturaMuerteTienda>       consultaCoberturasMuerte(EntityManager em)        throws Exception, BSEExceptionTienda;

}
