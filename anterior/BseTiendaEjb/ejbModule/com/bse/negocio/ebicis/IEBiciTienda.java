package com.bse.negocio.ebicis;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.bse.accesodatos.ebici.CotizacionBiciTienda;
import com.bse.accesodatos.ebici.PolizaBiciTienda;
import com.bse.accesodatos.ebici.TipoBiciTienda;
import com.bse.negocio.comun.BSEExceptionTienda;

public interface IEBiciTienda {

    public CotizacionBiciTienda cotizarBiciAnonimo( EntityManager em,
                                                    String  planCobertura,
                                                    Double  valorBicicleta,
                                                    Integer vigenciaSeguro )    throws Exception, BSEExceptionTienda;

    public PolizaBiciTienda     emitirBici( EntityManager em,
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
                                            String consumoFinal )               throws Exception, BSEExceptionTienda;

    public String               clienteConDeuda( EntityManager em,
                                                 String  tipoDocumento,
                                                 String  nroDocumento,
                                                 Integer nroCotizacion,
                                                 Integer nroCertificado )       throws Exception, BSEExceptionTienda;

    public List<TipoBiciTienda> consultaTiposBicicletas(EntityManager em)       throws Exception, BSEExceptionTienda;

}
