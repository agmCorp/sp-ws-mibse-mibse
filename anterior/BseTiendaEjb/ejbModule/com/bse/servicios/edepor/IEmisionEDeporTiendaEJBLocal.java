package com.bse.servicios.edepor;


import java.util.Date;
import java.util.List;

import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.accesodatos.edepor.CoberturaRcTienda;
import com.bse.accesodatos.edepor.CotizacionEDeporTienda;
import com.bse.accesodatos.edepor.TipoBuqueTienda;
import com.bse.accesodatos.edepor.PolizaEDeporTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import javax.ejb.Local;

@Local
public interface IEmisionEDeporTiendaEJBLocal {

    public List<TipoBuqueTienda> consultaTiposBuques(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda;

    public List<CoberturaRcTienda> consultaCoberturasRc(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda;

    public CotizacionEDeporTienda cotizarEDeporAnonimo( DTSesionTienda dtSesion,
                                                        String planCobertura,
                                                        String tipoBuque,
                                                        Date   fechaDesde,
                                                        Date   fechaHasta,
                                                        double capital,
                                                        int    planPago ) throws Exception, BSEExceptionTienda;

    public PolizaEDeporTienda emitirEDepor( DTSesionTienda dtSesion,
                                            String tipoDocumento,
                                            String documento,
                                            String matriculaBuque,
                                            String nombreBuque,
                                            long   nroCotizacion,
                                            String consumoFinal ) throws Exception, BSEExceptionTienda;

}
