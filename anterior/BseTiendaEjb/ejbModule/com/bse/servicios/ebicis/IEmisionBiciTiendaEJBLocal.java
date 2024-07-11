package com.bse.servicios.ebicis;


import java.util.Date;
import java.util.List;

import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.accesodatos.ebici.CotizacionBiciTienda;
import com.bse.accesodatos.ebici.PolizaBiciTienda;
import com.bse.accesodatos.ebici.TipoBiciTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import javax.ejb.Local;

@Local
public interface IEmisionBiciTiendaEJBLocal {

    public CotizacionBiciTienda cotizarBiciAnonimo( DTSesionTienda dtSesion,
                                                    String  planCobertura,
                                                    Double  valorBicicleta,
                                                    Integer vigenciaSeguro ) throws Exception, BSEExceptionTienda;

    public PolizaBiciTienda emitirBici( DTSesionTienda dtSesion,
                                        String planPago,
                                        String tipoDocumento,
                                        String documento,
                                        Date   fechaFactura,
                                        String tipoBicicleta,
                                        Date   fechaNacimientoCliente,
                                        String marca,
                                        String serie,
                                        String modelo,
                                        long   nroCotizacion,
                                        String consumoFinal ) throws Exception, BSEExceptionTienda;

    public String clienteConDeuda( DTSesionTienda dtSesion,
                                   String  tipoDocumento,
                                   String  nroDocumento,
                                   Integer nroCotizacion,
                                   Integer nroCertificado ) throws Exception, BSEExceptionTienda;


    public List<TipoBiciTienda> consultaTiposBicicletas(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda;
}
