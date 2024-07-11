package com.bse.negocio.eindivi;

import java.util.List;

import javax.persistence.EntityManager;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.accesodatos.eindivi.CotizacionIndiviTienda;
import com.bse.accesodatos.eindivi.DatosVariosIndivi;
import com.bse.accesodatos.eindivi.ItemDeptoLocalidadArea;
import com.bse.accesodatos.eindivi.PolizaIndiviTienda;
import com.bse.negocio.comun.BSEExceptionTienda;

public interface IEIndiviTienda {

    public CotizacionIndiviTienda cotizarIndiviAnonimo ( EntityManager em,
                                                         String marcaVehiculo,
                                                         String anioVehiculo,
                                                         String tipoVehiculo,
                                                         String combustible,
                                                         String versionVehiculo,
                                                         String areaCirculacion ) throws Exception, BSEExceptionTienda;

    public PolizaIndiviTienda     emitirIndivi         ( EntityManager em,
                                                         String tipoDocumento,
                                                         String documento,
                                                         long   nroCotizacion,
                                                         String matriculaVehiculo,
                                                         String padronVehiculo,
                                                         String motorVehiculo,
                                                         String chasisVehiculo,
                                                         String planCobertura,
                                                         String planPago,
                                                         String modalidad,
                                                         String consumoFinal )    throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera> consultaAreasCirculacion   ( EntityManager em )    throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera> consultaTiposVehiculos     ( EntityManager em )    throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera> consultaTiposCombustible   ( EntityManager em )    throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera> consultaMarcasVehiculos    ( EntityManager em )    throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera> consultaFamiliasVehiculos  ( EntityManager em,
                                                            String marcaVehiculo,
                                                            String anioVehiculo,
                                                            String tipoVehiculo,
                                                            String combustible )  throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera> consultaVersionesVehiculos ( EntityManager em,
                                                            String marcaVehiculo,
                                                            String anioVehiculo,
                                                            String tipoVehiculo,
                                                            String combustible,
                                                            String familiaVehiculo)throws Exception, BSEExceptionTienda;

    public DatosVariosIndivi   consultaDatosVarios        ( EntityManager em )    throws Exception, BSEExceptionTienda;

    public List<ItemDeptoLocalidadArea> consultaDepartamentosArea
                                                          ( EntityManager em )    throws Exception, BSEExceptionTienda;

    public List<ItemDeptoLocalidadArea> consultaLocalidadesDeptoArea
                                                          ( EntityManager em,
                                                            String idDepto   )    throws Exception, BSEExceptionTienda;

}
