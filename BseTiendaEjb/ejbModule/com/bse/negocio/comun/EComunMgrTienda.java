package com.bse.negocio.comun;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bse.accesodatos.esoa.MonedaTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanPagoTienda;
import com.bse.accesodatos.esoa.ProductoTienda;
import com.bse.accesodatos.esoa.RamoTienda;
import com.bse.servicios.utilitario.log.Logueo;


public class EComunMgrTienda implements IEComunTienda {

    private static final Logger logger = LogManager.getLogger(EComunMgrTienda.class);

    /**
     * CONSTRUCTOR
     */
    public EComunMgrTienda() {
    }


    /**
     *
     * @return
     */
    public static IEComunTienda getEComunMgr() {
        return new EComunMgrTienda();
    }


    /**
     *
     * @param em
     * @param tipoDocumento
     * @param nroDocumento
     * @param nroCotizacion
     * @param nroCertificado
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    //
    // Desde - INDIVI (SERVICIO)
    // Desde - OPERSONAL (SERVICIO)
    @Override
    public String clienteConDeuda( EntityManager em,
                                   String  tipoDocumento,
                                   String  nroDocumento,
                                   Integer nroCotizacion,
                                   Integer nroCertificado ) throws Exception, BSEExceptionTienda {

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "COMUN - clienteConDeuda (" + Long.toString(tiempoInicioMetodo) + ") - CLIDEUDA";

        // LOGUEO PARAMETROS
        logClienteConDeudaParametros( logEncabezado + " - INICIO",
                                      tipoDocumento, nroDocumento, nroCotizacion, nroCertificado);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        short er = -1;
        if      ((tipoDocumento == null) || tipoDocumento.equals("")) {
            er = CodigosErrorTienda.tipo_documento_invalido;
        } else if ((nroDocumento == null) || nroDocumento.equals("")) {
            er = CodigosErrorTienda.documento_invalido;
        } else if (nroCotizacion == 0) {
            er = CodigosErrorTienda.cotizacion_invalida;
        }
        if ( er > -1 ) { throw new BSEExceptionTienda(er); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocaci�n PL
        logGuiones(logEncabezado);
        logClienteConDeudaPl( logEncabezado, "PACK_EMI_MIBSE.PRO_CONTROLAR_CLIENTE_DEUDA",
                              tipoDocumento, nroDocumento, nroCotizacion, nroCertificado );

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_CONTROLAR_CLIENTE_DEUDA
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_CONTROLAR_CLIENTE_DEUDA");

        q.setParameter("P_TP_DOCUMENTO"  , tipoDocumento);  // PARAMETRO
        q.setParameter("P_NRO_DOCUMENTO" , nroDocumento);   // PARAMETRO
        q.setParameter("P_NU_COTIZACION" , nroCotizacion);  // PARAMETRO
        q.setParameter("P_NU_CERTIFICADO", nroCertificado); // PARAMETRO

        tiempoInicioAux = System.currentTimeMillis();

        @SuppressWarnings("unchecked")
        List<Object[]> l = q.getResultList();
        Object[] r = l.get(0);

        tiempoTotalAux = System.currentTimeMillis() - tiempoInicioAux;
        logger.info(logEncabezado + " - Tiempo Ejecucion PL ["+Long.toString(tiempoTotalAux)+"] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // VERIFICO RESULTADO y en caso de no obtener RESULTADO-> Se DISPARA Exception
        //      -> R[4]  = P_RESULTADO  - type=String.class
        if (r[4] != null) {
            logger.info(logEncabezado+" - R4 = ["+r[4].toString()+"]");
        } else {
            logger.info(logEncabezado+" - R4 = [null]");

            tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
            logger.info(logEncabezado+" - Tiempo Ejecucion METODO ["+Long.toString(tiempoTotalMetodo)+"] milisegundos");

            throw new BSEExceptionTienda(CodigosErrorTienda.no_existe_cliente);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logGuiones(logEncabezado);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // NO hubo error en PL -> PARSEO y se DETERMINA el RESULTADO
        String noTieneDeuda = r[4].toString();

        String result = "";
        if ( (noTieneDeuda.equals("F")) || (noTieneDeuda.equals("N")) ){
            result = "S";
        } else if (!noTieneDeuda.equals("S")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.error_emision_rector);
        } else {
            result = "N";
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logger.info(logEncabezado + " - RESULT - TieneDeuda [" + result + "]");
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return result;
    }


    /**
    *
    * @param em
    * @param codigoMoneda
    * @return
    * @throws Exception
    * @throws BSEExceptionTienda
    */
    //
    // Desde - BICI (EJB)
    // Desde - EDEPORT (EJB)
    // Desde - INDIVI (EJB)
    // Desde - OPERSONAL (EJB)
    // Desde - SOA (EJB)
    // Desde - VIAJEROS (EJB)

    @Override
    public MonedaTienda consultaMoneda( EntityManager em,
                                        String codigoMoneda ) throws Exception, BSEExceptionTienda {
        String logEncabezado = "COMUN - consultaMoneda";
        String logParametros = " - PARAMETROS -> Moneda [" + codigoMoneda + "]";

        try {
            MonedaTienda monedaObj = em.find(MonedaTienda.class, codigoMoneda);

            logger.info(logEncabezado + logParametros + " - RESULT -> [" + monedaObj.toString() + "]");
            return monedaObj;

        } catch (Exception e) {
            logger.info(logEncabezado + logParametros + " - RESULT -> [NO EXISTE MONEDA]");
            throw new BSEExceptionTienda(CodigosErrorTienda.plan_pago_invalido, e);
        }
    }


    /**
    *
    * @param em
    * @param ramo
    * @param producto
    * @return
    * @throws Exception
    * @throws BSEExceptionTienda
    */
    //
    // Desde - SOA (SERVICIO)
    // Desde - VIAJEROS (SERVICIO)
    //
    // Desde - VIAJEROS (EJB)
    @Override
    public List<PlanCoberturaTienda> consultaPlanesCobertura( EntityManager em,
                                                              int    ramo,
                                                              String producto ) throws Exception, BSEExceptionTienda {
        String logEncabezado = "COMUN - consultaPlanesCobertura";
        String logParametros = " - PARAMETROS -> Ramo [" + Integer.toString(ramo) + "] Producto [" + producto + "]";

        ArrayList<PlanCoberturaTienda> lista = new ArrayList<PlanCoberturaTienda>();

        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramo));
        ProductoTienda productoObj = em.find(ProductoTienda.class, producto);

        Query query = em.createNamedQuery("PlanCoberturaTienda.findByRamoyProducto");
        query.setParameter("ramo", ramoObj);
        query.setParameter("producto", productoObj);

        List<PlanCoberturaTienda> result = UtilTienda.castList(PlanCoberturaTienda.class, query.getResultList());

        if ((result != null) && (result.size() > 0)) {
            for (int i = 0; i < result.size(); i++) {
                PlanCoberturaTienda pc = result.get(i);
                lista.add(pc);
            }
        } else { logger.info(logEncabezado + " - BASE de DATOS no retorna PLANES de COBERTURAS"); }
        logger.info(logEncabezado + logParametros
                                  + " - RESULT -> ["    + Integer.toString(lista.size()) + "] elementos.");
        return lista;
    }


    /**
    *
    * @param em
    * @param plan
    * @return
    * @throws Exception
    * @throws BSEExceptionTienda
    */
    //
    // Desde - SOA (SERVICIO)
    // Desde - VIAJEROS (SERVICIO)
    //
    // Desde - SOA (EJB)
    // Desde - VIAJEROS (EJB)
    @Override
    public PlanCoberturaTienda consultaPlanCobertura( EntityManager em,
                                                      String plan ) throws Exception, BSEExceptionTienda {
        String logEncabezado = "COMUN - consultaPlanCobertura";
        String logParametros = " - PARAMETROS -> PlanCobertura [" + plan + "]";

        try {
            PlanCoberturaTienda planObj = em.find(PlanCoberturaTienda.class, plan);

            logger.info(logEncabezado + logParametros + " - RESULT -> [" +planObj.toString()+ "]");
            return planObj;

        } catch (Exception e) {
            logger.info(logEncabezado + logParametros
                                      + " - RESULT -> [NO EXISTE PLAN de COBERTURA para los PARAMETROS INDICADOS]");
            e.printStackTrace();
            throw new BSEExceptionTienda(CodigosErrorTienda.plan_cobertura_invalido);
        }
    }


    /**
     *
     * @param em
     * @param ramo
     * @param plan
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    //
    // Desde - BICI (EJB)
    // Desde - EDEPORT (EJB)
    @Override
    public PlanCoberturaTienda consultaPlanCoberturaRamo( EntityManager em,
                                                          int    ramo,
                                                          String plan ) throws Exception, BSEExceptionTienda{
        String logEncabezado = "COMUN - consultaPlanCoberturaRamo";
        String logParametros = " - PARAMETROS -> Ramo [" + Integer.toString(ramo) + "] PlanCobertura [" + plan + "]";

        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramo));

        Query query = em.createNamedQuery("PlanCoberturaTienda.findByRamoPlan");
        query.setParameter("ramo", ramoObj);
        query.setParameter("plan", plan);

        List<PlanCoberturaTienda> result = UtilTienda.castList(PlanCoberturaTienda.class, query.getResultList());

        if ((result != null) && (result.size() > 0)) {
            PlanCoberturaTienda pc = result.get(0);

            logger.info(logEncabezado + logParametros + " - RESULT -> [" + pc.toString() + "]");
            return pc;
        }
        logger.info(logEncabezado + logParametros
                                  + " - RESULT -> [NO EXISTE PLAN de COBERTURA para los PARAMETROS INDICADOS]");
        return null;
    }


    /**
    *
    * @param em
    * @param ramo
    * @param producto
    * @param plan
    * @return
    * @throws Exception
    * @throws BSEExceptionTienda
    */
    //
    // Desde - BICI (EJB)
    // Desde - INDIVI (EJB)
    // Desde - OPERSONAL (EJB)
    @Override
    public PlanCoberturaTienda consultaPlanCoberturaRamoProducto( EntityManager em,
                                                                  int    ramo,
                                                                  String producto,
                                                                  String plan ) throws Exception, BSEExceptionTienda {
        String logEncabezado = "COMUN - consultaPlanCoberturaRamoProducto";
        String logParametros = " - PARAMETROS -> Ramo [" + Integer.toString(ramo)
                             + "] Producto [" + producto + "] PlanCobertura [" + plan + "]";

        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramo));
        ProductoTienda productoObj = em.find(ProductoTienda.class, producto);

        Query query = em.createNamedQuery("PlanCoberturaTienda.findByRamoProductoPlan");
        query.setParameter("ramo", ramoObj);
        query.setParameter("producto", productoObj);
        query.setParameter("plan", plan);

        List<PlanCoberturaTienda> result = UtilTienda.castList(PlanCoberturaTienda.class, query.getResultList());

        if ((result != null) && (result.size() > 0)) {
            PlanCoberturaTienda pc = result.get(0);

            logger.info(logEncabezado + logParametros + " - RESULT -> [" + pc.toString() + "]");
            return pc;
        }

        logger.info(logEncabezado + logParametros
                                  + " - RESULT -> [NO EXISTE PLAN de COBERTURA para los PARAMETROS INDICADOS]");
        return null;
    }


    /**
     *
     * @param em
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    //
    // Desde - SOA (SERVICIOS)
    // Desde - VIAJEROS (SERVICIOS)
    // OJO OJO -- USA desde CONFIGURACION - getPlanesPagoSoa(em)
    @Override
    public List<PlanPagoTienda> consultaPlanesPago(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "COMUN - consultaPlanesPago";

        ArrayList<PlanPagoTienda> lista = new ArrayList<PlanPagoTienda>();

        String planesAutorizados = CodigosTienda.getCodigos().getPlanesPagoSoa(em);
        String[] planesAutorizadosVec = planesAutorizados.split(",");

        Query query = em.createNamedQuery("PlanPagoTienda.findAll");

        List<PlanPagoTienda> result = UtilTienda.castList(PlanPagoTienda.class, query.getResultList());

        if ((result != null) && (result.size() > 0)) {
            for (int i = 0; i < result.size(); i++) {
                PlanPagoTienda pc = result.get(i);

                boolean autorizado = false;
                for (int x = 0; x < planesAutorizadosVec.length; x++){
                    if (pc.getCodigo() == Integer.parseInt(planesAutorizadosVec[x])){
                        autorizado = true;
                        x = planesAutorizadosVec.length;
                    }
                }

                if (autorizado){
                    lista.add(pc);
                }
            }
        }

        logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
        return lista;
    }


    /**
     *
     * @param em
     * @param plan
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    // Desde - SOA (SERVICIO)
    // Desde - VIAJEROS (SERVICIOS)
    //
    // Desde - BICI (EJB)
    // Desde - EDEPORT (EJB)
    // Desde - INDIVI (EJB)
    // Desde - OPERSONAL (EJB)
    // Desde - SOA (EJB)
    // Desde - VIAJEROS (EJB)
    @Override
    public PlanPagoTienda consultaPlanPago(EntityManager em, int plan) throws Exception, BSEExceptionTienda {
        String logEncabezado = "COMUN - consultaPlanPago";
        String logParametros = " - PARAMETROS -> PlanPago [" + Integer.toString(plan) + "]";

        try {
            PlanPagoTienda planObj = em.find(PlanPagoTienda.class, Integer.valueOf(plan));

            logger.info(logEncabezado + logParametros + " - RESULT -> [" +planObj.toString()+ "]");
            return planObj;
        } catch (Exception e) {
            logger.info(logEncabezado + logParametros
                                      + " - RESULT -> [NO EXISTE PLAN de PAGO para los PARAMETROS INDICADOS]");
            throw new BSEExceptionTienda(CodigosErrorTienda.plan_pago_invalido, e);
        }
    }


    /**
     *
     * @param em
     * @param sucursal
     * @param nroCotizacion
     * @param ramo
     * @param producto
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    //
    // Desde - BICI (EJB)
    // Desde - EDEPORT (EJB)
    // Desde - INDIVI (EJB)
    // Desde - OPERSONAL (EJB)
    @Override
    public boolean validarExistenciaCotizacion( EntityManager em,
                                                int sucursal, long   nroCotizacion,
                                                int ramo    , String producto ) throws Exception, BSEExceptionTienda {
        String logEncabezado = "COMUN - validarExistenciaCotizacion";
        String logParametros = " - PARAMETROS -> Sucursal ["      + Integer.toString(sucursal)
                                            + "] NroCotizacion [" + Long.toString(nroCotizacion)
                                            + "] Ramo ["          + Integer.toString(ramo)
                                            + "] Producto ["      + producto + "]";

        boolean result = false;
        String sqlCot = "SELECT * "
                      + "FROM CART_COTIZA_BANCO "
                      + "WHERE CAZB_CAPJ_CD_SUCURSAL = ? "
                      +   "AND CAZB_NU_COTIZACION = ? "
                      +   "AND CAZB_NU_CONSECUTIVO = 0 "
                      +   "AND CAZB_CARP_CD_RAMO = ? "
                      +   "AND CAZB_CAPU_CD_PRODUCTO = ?";

        Query queryCot = em.createNativeQuery(sqlCot);
        queryCot.setParameter(1, sucursal);
        queryCot.setParameter(2, nroCotizacion);
        queryCot.setParameter(3, ramo);
        queryCot.setParameter(4, producto);

        @SuppressWarnings("unchecked")
        List<Object[]> resultCot = queryCot.getResultList();
        if ((resultCot != null) && (resultCot.size() > 0)) { result = true; }

        logger.info(logEncabezado + logParametros
                     + " - RESULT -> [" + ((result)?"OK":"COTIZACION NO VALIDA") + "]");
        return result;
    }


    /**
     *
     * @param logEncabezado
     * @param tipoDocumento
     * @param nroDocumento
     * @param nroCotizacion
     * @param nroCertificado
     */
    private void logClienteConDeudaParametros( String  logEncabezado,
                                               String  tipoDocumento,
                                               String  nroDocumento,
                                               Integer nroCotizacion,
                                               Integer nroCertificado ) {

        String logParametros = "TipoDocumento ["  + tipoDocumento + "] NroDocumento [" + nroDocumento
                           + "] NroCotizacion ["  + ((nroCotizacion!=null)?Integer.toString(nroCotizacion):"null")
                           + "] NroCertificado [" + ((nroCertificado!=null)?Integer.toString(nroCertificado):"null")
                           + "]";
        logger.info(logEncabezado + " - Parametros -> " + logParametros);
    }

    /**
     *
     * @param logEncabezado
     * @param tipoDocumento
     * @param nroDocumento
     * @param nroCotizacion
     * @param nroCertificado
     */
    private void logClienteConDeudaPl( String  logEncabezado, String titulo,
                                       String  tipoDocumento,
                                       String  nroDocumento,
                                       Integer nroCotizacion,
                                       Integer nroCertificado ) {
        Logueo logueo = new Logueo();
        int pad = 17;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO"  , tipoDocumento, pad);
        logueo.setParametro("P_NRO_DOCUMENTO" , nroDocumento , pad);
        logueo.setParametro("P_NU_COTIZACION" , ((nroCotizacion!=null)?Integer.toString(nroCotizacion):"null")  , pad);
        logueo.setParametro("P_NU_CERTIFICADO", ((nroCertificado!=null)?Integer.toString(nroCertificado):"null"), pad);
        logger.info(logEncabezado + " - " + logueo.getSoloParametros());

    }


    /**
     *
     * @param logEncabezado
     */
    private void logGuiones(String logEncabezado) {
        String tanda = "----------";
        String result = "";
        for (int i=0; i < 11; i++) {
            result += tanda;
        }
        logger.info(logEncabezado + " - " + result);
    }

}
