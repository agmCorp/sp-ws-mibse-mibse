package com.bse.servicios.ws.bici;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

import com.bse.accesodatos.ebici.CotizacionBiciTienda;
import com.bse.accesodatos.ebici.PolizaBiciTienda;
import com.bse.accesodatos.ebici.TipoBiciTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.servicios.ebicis.IEmisionBiciTiendaEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesionTienda;

@WebService(serviceName = "EmisionBici")
public class EmisionBiciTienda {

    private static final Logger logger = Logger.getLogger(EmisionBiciTienda.class);

    @Resource
    private WebServiceContext wsContext;

    /**
     *
     * @return
     * @throws NamingException
     */
    private IEmisionBiciTiendaEJBLocal getEJBManager() throws NamingException {
        InitialContext ctx = new InitialContext();
        IEmisionBiciTiendaEJBLocal bean = (IEmisionBiciTiendaEJBLocal) 
                                                            ctx.lookup("BseTiendaEar/EmisionBiciTiendaEJB/local");
        return bean;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    private DTSesionTienda getDTSesion(String usuario, String contrasena) {
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
        return new DTSesionTienda(usuario, contrasena, req.getRemoteAddr());
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @param planCobertura
     * @param valorBicicleta
     * @param vigenciaSeguro
     * @return
     */
    @WebMethod
    public CotizacionBiciTiendaResp cotizarBiciAnonimo( @WebParam(name = "usuario")        String  usuario,
                                                        @WebParam(name = "contrasena")     String  contrasena,
                                                        @WebParam(name = "planCobertura")  String  planCobertura,
                                                        @WebParam(name = "valorBicicleta") Double  valorBicicleta,
                                                        @WebParam(name = "vigenciaSeguro") Integer vigenciaSeguro ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - BICI - cotizarBiciAnonimo - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        CotizacionBiciTiendaResp result = new CotizacionBiciTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            CotizacionBiciTienda cotizacionBici = getEJBManager().cotizarBiciAnonimo( getDTSesion(usuario, contrasena),
                                                                                      planCobertura,
                                                                                      valorBicicleta,
                                                                                      vigenciaSeguro );
            result.setCotizacionBici(cotizacionBici);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - TIEMPO TOTAL EJECUCION [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return result;
    }


    //- Fecha de factura (hasta 2 años y hasta hoy inclusive) *
    //- Tipo de bicicleta (clásica o eléctrica, lista desplegable)
    //- Fecha de nacimiento **
    //- Marca (texto plano no valida)
    //- Serie (texto plano no valida)
    //- Modelo (texto plano no valida)

    /**
     *
     * @param usuario
     * @param contrasena
     * @param planPago
     * @param tipoDocumento
     * @param documento
     * @param fechaNacimientoCliente
     * @param nroCotizacion
     * @param tipoBicicleta
     * @param marca
     * @param serie
     * @param modelo
     * @param fechaFactura
     * @param consumoFinal
     * @return
     */
    @WebMethod
    public EmisionBiciTiendaResp emitirBici( @WebParam(name = "usuario")                String usuario,
                                             @WebParam(name = "contrasena")             String contrasena,
                                             @WebParam(name = "planPago")               String planPago,
                                             @WebParam(name = "tipoDocumento")          String tipoDocumento,
                                             @WebParam(name = "documento")              String documento,
                                             @XmlElement(nillable=true)
                                             @WebParam(name = "fechaNacimientoCliente") Date   fechaNacimientoCliente,
                                             @WebParam(name = "nroCotizacion")          long   nroCotizacion,
                                             @WebParam(name = "tipoBicicleta")          String tipoBicicleta,
                                             @WebParam(name = "marca")                  String marca,
                                             @WebParam(name = "serie")                  String serie,
                                             @WebParam(name = "modelo")                 String modelo,
                                             @WebParam(name = "fechaFactura")           Date   fechaFactura,
                                             @WebParam(name = "consumoFinal")           String consumoFinal ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - BICI - emitirBici - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        EmisionBiciTiendaResp result = new EmisionBiciTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            PolizaBiciTienda polizaBici = getEJBManager().emitirBici( getDTSesion(usuario, contrasena),
                                                                      planPago,
                                                                      tipoDocumento,
                                                                      documento,
                                                                      fechaFactura,
                                                                      tipoBicicleta,
                                                                      fechaNacimientoCliente,
                                                                      marca,
                                                                      serie,
                                                                      modelo,
                                                                      nroCotizacion,
                                                                      consumoFinal );
            result.setPolizaBici(polizaBici);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - TIEMPO TOTAL EJECUCION [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @param tipoDocumento
     * @param documento
     * @param nroCotizacion
     * @param nroCertificado
     * @return
     */
    @WebMethod
    public ClienteDeudaTiendaResp controlarClienteConDeuda( @WebParam(name = "usuario")        String usuario,
                                                            @WebParam(name = "contrasena")     String contrasena,
                                                            @WebParam(name = "tipoDocumento")  String tipoDocumento,
                                                            @WebParam(name = "documento")      String documento,
                                                            @WebParam(name = "nroCotizacion")  int    nroCotizacion,
                                                            @WebParam(name = "nroCertificado") int    nroCertificado ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - BICI - controlarClienteConDeuda - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        ClienteDeudaTiendaResp result = new ClienteDeudaTiendaResp();

        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            String tieneDeuda = getEJBManager().clienteConDeuda( getDTSesion(usuario, contrasena),
                                                                 tipoDocumento,
                                                                 documento,
                                                                 nroCotizacion,
                                                                 nroCertificado );
            result.setClienteConDeuda(tieneDeuda);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - TIEMPO TOTAL EJECUCION [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    @WebMethod
    public TiposBicicletasTiendaResp consultaTiposBicicletas( @WebParam(name = "usuario")    String usuario,
                                                              @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - BICI - consultaTiposBicicletas - TIENDA";

        TiposBicicletasTiendaResp result = new TiposBicicletasTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<TipoBiciTienda> tiposBicis = getEJBManager().consultaTiposBicicletas(getDTSesion(usuario, contrasena));
            result.setTiposBicicletas(tiposBicis);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param codigo
     * @param descripcion
     * @return
     */
    private String logError(String codigo, String descripcion) {
        return " - ERROR: CODIGO=[" + codigo + "] DESCRIPCION=[" + descripcion + "]";
    }

}
