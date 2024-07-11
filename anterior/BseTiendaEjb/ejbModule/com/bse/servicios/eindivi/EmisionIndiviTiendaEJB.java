package com.bse.servicios.eindivi;


import java.util.List;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.accesodatos.eindivi.CotizacionIndiviTienda;
import com.bse.accesodatos.eindivi.DatosVariosIndivi;
import com.bse.accesodatos.eindivi.ItemDeptoLocalidadArea;
import com.bse.accesodatos.eindivi.PolizaIndiviTienda;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.eindivi.IEIndiviTienda;
import com.bse.servicios.seguridad.SeguridadTiendaws;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "EmisionIndiviTiendaEJB")
@Interceptors({SeguridadTiendaws.class})
public class EmisionIndiviTiendaEJB implements IEmisionIndiviTiendaEJBLocal {

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public CotizacionIndiviTienda cotizarIndiviAnonimo( DTSesionTienda dtSesion,
                                                        String marcaVehiculo,
                                                        String anioVehiculo,
                                                        String tipoVehiculo,
                                                        String combustible,
                                                        String versionVehiculo,
                                                        String areaCirculacion ) throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.cotizarIndiviAnonimo( em,
                                                    marcaVehiculo,
                                                    anioVehiculo,
                                                    tipoVehiculo,
                                                    combustible,
                                                    versionVehiculo,
                                                    areaCirculacion );
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public PolizaIndiviTienda emitirIndivi( DTSesionTienda dtSesion,
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
                                            String consumoFinal ) throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.emitirIndivi( em,
                                            tipoDocumento,
                                            documento,
                                            nroCotizacion,
                                            matriculaVehiculo,
                                            padronVehiculo,
                                            motorVehiculo,
                                            chasisVehiculo,
                                            planCobertura,
                                            planPago,
                                            modalidad,
                                            consumoFinal );
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemCodiguera> consultaAreasCirculacion(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.consultaAreasCirculacion(em);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemCodiguera> consultaTiposVehiculos(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.consultaTiposVehiculos(em);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemCodiguera> consultaTiposCombustible(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.consultaTiposCombustible(em);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemCodiguera> consultaMarcasVehiculos(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.consultaMarcasVehiculos(em);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemCodiguera> consultaFamiliasVehiculos( DTSesionTienda dtSesion,
                                                          String marcaVehiculo,
                                                          String anioVehiculo,
                                                          String tipoVehiculo,
                                                          String combustible ) throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.consultaFamiliasVehiculos( em,
                                                         marcaVehiculo, anioVehiculo, tipoVehiculo, combustible );
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemCodiguera> consultaVersionesVehiculos( DTSesionTienda dtSesion,
                                                           String marcaVehiculo,
                                                           String anioVehiculo,
                                                           String tipoVehiculo,
                                                           String combustible,
                                                           String familiaVehiculo ) throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.consultaVersionesVehiculos( em,
                                                          marcaVehiculo, anioVehiculo, tipoVehiculo, combustible,
                                                          familiaVehiculo );
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public DatosVariosIndivi consultaDatosVarios(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.consultaDatosVarios(em);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemDeptoLocalidadArea> consultaDepartamentosArea(DTSesionTienda dtSesion)
                                                                                  throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.consultaDepartamentosArea(em);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemDeptoLocalidadArea> consultaLocalidadesDeptoArea(DTSesionTienda dtSesion, String idDepto)
                                                                                  throws Exception, BSEExceptionTienda {
        IEIndiviTienda eIndiviManager = FabricaNegocioTienda.getFabricaNegocio().getEIndiviMgr();
        return eIndiviManager.consultaLocalidadesDeptoArea(em, idDepto);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
