package uy.com.bse.genericos.ws;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.polizas.entidades.Cuota;
import uy.com.bse.polizas.operaciones.ParamFacturacionElectronica;
import uy.com.bse.polizas.ws.WsServiciosPolizas;
import uy.com.bse.servicios.rector.emision.ParamEmision;
import uy.com.bse.servicios.rector.emision.ParamValidarCotizacion;
import uy.com.bse.servicios.rector.emision.ResultEmision;
import uy.com.bse.servicios.rector.emision.ResultValidarCotizacion;
import uy.com.bse.servicios.rector.interfaces.ParamCodigoPostal;
import uy.com.bse.servicios.rector.interfaces.ParamContactoWeb;
import uy.com.bse.servicios.rector.interfaces.ParamListaActividades;
import uy.com.bse.servicios.rector.interfaces.ParamListaDepartamentos;
import uy.com.bse.servicios.rector.interfaces.ParamListaOrganismos;
import uy.com.bse.servicios.rector.interfaces.ParamListaPaises;
import uy.com.bse.servicios.rector.interfaces.ParamListaProductoresAsignados;
import uy.com.bse.servicios.rector.interfaces.ParamListaProductoresAsignadosVigentes;
import uy.com.bse.servicios.rector.interfaces.ParamListaSucursales;
import uy.com.bse.servicios.rector.interfaces.ParamListaTipoDoc;
import uy.com.bse.servicios.rector.interfaces.ParamLocalidades;
import uy.com.bse.servicios.rector.interfaces.ParamTipoUsuario;
import uy.com.bse.servicios.rector.interfaces.ResultContactoWeb;
import uy.com.bse.servicios.rector.interfaces.ResultListaActividades;
import uy.com.bse.servicios.rector.interfaces.ResultListaCodPostal;
import uy.com.bse.servicios.rector.interfaces.ResultListaDepartamentos;
import uy.com.bse.servicios.rector.interfaces.ResultListaLocalidades;
import uy.com.bse.servicios.rector.interfaces.ResultListaOrganismos;
import uy.com.bse.servicios.rector.interfaces.ResultListaPaises;
import uy.com.bse.servicios.rector.interfaces.ResultListaSucursales;
import uy.com.bse.servicios.rector.interfaces.ResultListaTipoDoc;
import uy.com.bse.servicios.rector.interfaces.ResultTipoUsuario;
import uy.com.bse.serviciosEJB.CotOperacionesLocal;
import uy.com.bse.serviciosEJB.ServiciosRectorGenericosLocal;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;
import uy.com.bse.utilitario.util.ValuesUtils;

@WebService(endpointInterface = "uy.com.bse.genericos.ws.IWsServiciosRector", serviceName = "WsServiciosRector")
public class WsServiciosRector implements IWsServiciosRector {

	private static Logger LOG = LogManager.getLogger(WsServiciosRector.class);

	public ResultListaTipoDoc listaTipoDoc(ParamListaTipoDoc param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("listaTipoDoc");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultListaTipoDoc datos = new ResultListaTipoDoc();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarTipoDoc(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().listaTipoDoc(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultListaOrganismos listaOrganismos(ParamListaOrganismos param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("listaOrganismos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultListaOrganismos datos = new ResultListaOrganismos();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarOrganismos(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().listaOrganismos(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultListaActividades listaActividades(ParamListaActividades param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("listaActividades");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultListaActividades datos = new ResultListaActividades();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarActividades(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().listaActividades(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultListaPaises listaPaises(ParamListaPaises param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("listaPaises");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultListaPaises datos = new ResultListaPaises();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarPaises(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().listaPaises(param);
				datos.setHayError(Boolean.FALSE);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultListaDepartamentos listaDepartamentos(ParamListaDepartamentos param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("listaDepartamentos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultListaDepartamentos datos = new ResultListaDepartamentos();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarDepartamentos(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().listaDepartamentos(param);
				datos.setHayError(Boolean.FALSE);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultListaLocalidades listaLocalidades(ParamLocalidades param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("listaLocalidades");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Departamento", param.getDepartamento());
		logueo.setParametro("Pais", param.getPais());

		ResultListaLocalidades datos = new ResultListaLocalidades();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarLocalidades(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().listaLocalidades(param);
				datos.setHayError(Boolean.FALSE);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultListaSucursales listaSucursales(ParamListaSucursales param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("listaSucursales");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultListaSucursales datos = new ResultListaSucursales();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarSucursales(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().listaSucursales(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultListaCodPostal listaCodPostal(ParamCodigoPostal param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("listaCodPostal");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Departamento", param.getCodDepartamento());
		logueo.setParametro("Pais", param.getCodPais());
		logueo.setParametro("Localidad", param.getLocalidad());
		logueo.setParametro("Numero postal", param.getNumPostal());

		ResultListaCodPostal datos = new ResultListaCodPostal();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarCodigoPostal(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().listaCodPostal(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultContactoWeb altaContactoWeb(ParamContactoWeb param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("altaContactoWeb");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido", param.getApellido());
		logueo.setParametro("Email", param.getEmail());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Documento", param.getNumDocumento());
		logueo.setParametro("Telefono", param.getTelefono());

		ResultContactoWeb datos = new ResultContactoWeb();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarAltaContactoWeb(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().altaContactoWeb(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultCodiguera listaProductoresAsignados(ParamListaProductoresAsignados param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("listaProductoresAsignados");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro("Cod producto", param.getCodProducto());

		ResultCodiguera datos = new ResultCodiguera();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarListaProductoresAsignados(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().listaProductoresAsignados(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultCodiguera listaProductoresAsignadosVigentes(ParamListaProductoresAsignadosVigentes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("listaProductoresAsignadosVigentes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro("Cod Productoe", param.getCodProducto());

		ResultCodiguera datos = new ResultCodiguera();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarListaProductoresAsignadosVigentes(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().listaProductoresAsignadosVigentes(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultTipoUsuario tipoUsuario(ParamTipoUsuario param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("tipoUsuario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultTipoUsuario datos = new ResultTipoUsuario();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarTipoUsuario(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManager().tipoUsuario(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}
	public ResultValidarCotizacion validarCotizacion(ParamValidarCotizacion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("validarCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
	
		ResultValidarCotizacion datos = new ResultValidarCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarValidarCotizacion(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManagerCot().validarCotizacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultEmision emitirPoliza(ParamEmision param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosRector.class);
		logueo.setMetodo("emitirPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Plan de pago", param.getPlanPago());
		
		ResultEmision datos = new ResultEmision();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesServiciosRector validar = new ValidacionesServiciosRector();
			ServiciosError error = validar.validarEmitirPoliza(param);

			if (error == null) {
				datos = WsServiciosRector.getEJBManagerCot().emitirPoliza(param);
				if(datos.getEmision()!= null && datos.getEmision().getFacturaElectronica()!= null){
					LOG.debug("EMITIR POLIZA 1 : HAY FACTURAS ");
					StringBuffer sb = new StringBuffer("Hubo error en la generación de factura electrónica para factura(s) ");
					
				
					ArrayList<Cuota> cuotas = datos.getEmision().getCuotas();
					//recorrer todas las cuotas
					 for(int i = 0; i < cuotas.size(); i++){
					
						 String nroFactura = ValuesUtils.toString(cuotas.get(i).getFactura());
					
						ParamFacturacionElectronica paramFacturacion = new ParamFacturacionElectronica();
						paramFacturacion.setNumeroFactura(nroFactura);
						paramFacturacion.setUsuario(param.getUsuario());
						paramFacturacion.setClave(param.getClave());
						
						logueo.setParametro("Nro de factura "+i, paramFacturacion.getNumeroFactura());
						
						ResultGenerico rfacturacion = WsServiciosPolizas.getEJBManager().facturacionElectronica(paramFacturacion);
						LOG.debug("EMITIR POLIZA 2 : facturacion electronica :"+i+ " finalizada");
						if (rfacturacion.getHayError()) {
							datos.setHayError(Boolean.TRUE);
							sb.append(nroFactura);
							sb.append(", ");
						}
					 }
					 LOG.debug("EMITIR POLIZA 3 : facturacion electronica para todas las cuotas terminada");
					 if (datos.getHayError()) {
						String errores = sb.toString();
						datos.setError(new ServiciosError(30, errores.substring(0, errores.length() - 2)));
					 }
					
				}
					
			} 
		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	



	private String catchException(Logueo logueo, Exception e) {
		String claveError = Values.CLWEBSERVEXCP;
		logueo.setException(Values.EXCEPTION);
		logueo.setError(e.getMessage());
		LOG.error(logueo.getMensaje(),e);
		enviarMail( logueo);
		return claveError;
	}

	private void finallyBlock(String claveError, ResultGenerico datos) {
		if (claveError != null) {
			datos.setHayError(Boolean.TRUE);
			datos.setError(ErrorResolver.getError(claveError));
		}
	}
	
	public static ServiciosRectorGenericosLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (ServiciosRectorGenericosLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/ServiciosRectorGenericos!uy.com.bse.serviciosEJB.ServiciosRectorGenericosLocal");
	}
	

	public static CotOperacionesLocal getEJBManagerCot() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (CotOperacionesLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/CotOperaciones!uy.com.bse.serviciosEJB.CotOperacionesLocal");
		
	}

	
	private void enviarMail(Logueo logueo) {
		try {
			EARPropertiesManager manager= new EARPropertiesManager("configServiciosRector.properties");
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("serviciosRector.mail.soporte"),manager.obtenerValor("serviciosRector.ccmail.soporte"),logueo.getMensaje(), "SERVICIO SERVICIOSRECTOR Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.error("Error en m\u00e9todo enviarMail", e);
		}
	}

}
