package com.bse.servicios.eviajeros;


import java.util.ArrayList;
import java.util.Date;

import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.accesodatos.eviajeros.CoberturaMuerteTienda;
import com.bse.accesodatos.eviajeros.CoberturaPrexistentesTienda;
import com.bse.accesodatos.eviajeros.CotizacionViajerosTienda;
import com.bse.accesodatos.eviajeros.PolizaViajerosTienda;
import com.bse.accesodatos.eviajeros.ViajeroTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import javax.ejb.Local;

@Local
public interface IEmisionViajerosTiendaEJBLocal {

    public CotizacionViajerosTienda cotizarViajerosAnonimo( DTSesionTienda dtSesion,
                                                            String planCobertura,
                                                            int    planPago,
                                                            Date   fechaDesde,
                                                            Date   fechaHasta,
                                                            ArrayList<ViajeroTienda> listaPersonas,
                                                            String extension,
                                                            Date   fechaSalidaPais ) throws Exception, BSEExceptionTienda;

    public PolizaViajerosTienda emitirViajeros( DTSesionTienda dtSesion,
                                                String tipoDocumentoContratante,
                                                String documentoContratante,
                                                ArrayList<ViajeroTienda> listaPersonas,
                                                long   nroCotizacion,
                                                String consumoFinal,
                                                String extension,
                                                Date   fechaSalidaPais ) throws Exception, BSEExceptionTienda;

    public ArrayList<CoberturaPrexistentesTienda> consultaCoberturasPrexistentes(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda;

    public ArrayList<CoberturaMuerteTienda> consultaCoberturasMuerte(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda;
}
