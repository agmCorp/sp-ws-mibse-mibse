package com.bse.negocio.edepor;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.bse.accesodatos.edepor.CoberturaRcTienda;
import com.bse.accesodatos.edepor.CotizacionEDeporTienda;
import com.bse.accesodatos.edepor.PolizaEDeporTienda;
import com.bse.accesodatos.edepor.TipoBuqueTienda;
import com.bse.negocio.comun.BSEExceptionTienda;

public interface IEDeporTienda {

    public CotizacionEDeporTienda   cotizarEDeporAnonimo( EntityManager em,
                                                          String planCobertura,
                                                          String tipoBuque,
                                                          Date   fechaDesde,
                                                          Date   fechaHasta,
                                                          double capital,
                                                          int    planPago ) throws Exception, BSEExceptionTienda;

    public PolizaEDeporTienda       emitirEDepor( EntityManager em,
                                                  String tipoDocumento,
                                                  String documento,
                                                  String matriculaBuque,
                                                  String nombreBuque,
                                                  long   nroCotizacion,
                                                  String consumoFinal )     throws Exception, BSEExceptionTienda;

    public List<TipoBuqueTienda>    consultaTiposBuques(EntityManager em)   throws Exception, BSEExceptionTienda;

    public TipoBuqueTienda          consultaTipoBuque( EntityManager em,
                                                       String tipo )        throws Exception, BSEExceptionTienda;

    public List<CoberturaRcTienda>  consultaCoberturasRc(EntityManager em)  throws Exception, BSEExceptionTienda;

}
