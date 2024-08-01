package uy.com.bse.serviciosEJB;

import javax.ejb.Local;

import uy.com.bse.reportes.ParamListaReportesResumenEmision;
import uy.com.bse.reportes.ParamObtenerClientesCorredores;
import uy.com.bse.reportes.ParamObtenerConsultasExportables;
import uy.com.bse.reportes.ParamObtenerFacturasDelProductorProximasAVencer;
import uy.com.bse.reportes.ParamObtenerFacturasDelProductorProximasAVencerEn12Dias;
import uy.com.bse.reportes.ParamObtenerIncentivoPlusDetalle;
import uy.com.bse.reportes.ParamObtenerIncentivoPlusTotales;
import uy.com.bse.reportes.ParamObtenerListadoControlADTBPSParaProductor;
import uy.com.bse.reportes.ParamObtenerLogsUsuariosProductor;
import uy.com.bse.reportes.ParamObtenerNombreFicheros;
import uy.com.bse.reportes.ParamObtenerNuevaTarifaIncendioProductor;
import uy.com.bse.reportes.ParamObtenerPolizasAdheridasDebitoAutomatico;
import uy.com.bse.reportes.ParamObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores;
import uy.com.bse.reportes.ParamObtenerPolizasSinMatriculasXProductores;
import uy.com.bse.reportes.ParamObtenerResumenEmision;
import uy.com.bse.reportes.ParamObtenerRutaDescargaUsuario;
import uy.com.bse.reportes.ParamObtenerSeguroAutomovil;
import uy.com.bse.reportes.ParamObtenerTotalesResumenEmision;
import uy.com.bse.reportes.ResultObtenerClientesCorredores;
import uy.com.bse.reportes.ResultObtenerConsultasExportables;
import uy.com.bse.reportes.ResultObtenerFacturasDelProductorProximasAVencer;
import uy.com.bse.reportes.ResultObtenerFacturasDelProductorProximasAVencerEn12Dias;
import uy.com.bse.reportes.ResultObtenerIncentivoPlusDetalle;
import uy.com.bse.reportes.ResultObtenerIncentivoPlusTotales;
import uy.com.bse.reportes.ResultObtenerListadoControlADTBPSParaProductor;
import uy.com.bse.reportes.ResultObtenerLogsUsuariosProductor;
import uy.com.bse.reportes.ResultObtenerNombreFicheros;
import uy.com.bse.reportes.ResultObtenerNuevaTarifaIncendioProductor;
import uy.com.bse.reportes.ResultObtenerPolizasAdheridasDebitoAutomatico;
import uy.com.bse.reportes.ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores;
import uy.com.bse.reportes.ResultObtenerPolizasSinMatriculasXProductores;
import uy.com.bse.reportes.ResultObtenerResumenEmision;
import uy.com.bse.reportes.ResultObtenerRutaDescargaUsuario;
import uy.com.bse.reportes.ResultObtenerSeguroAutomovil;
import uy.com.bse.reportes.ResultObtenerTotalesResumenEmision;
import uy.com.bse.utilitario.dato.ResultCodiguera;
@Local
public interface ConsultaReportesLocal {

	 ResultObtenerFacturasDelProductorProximasAVencerEn12Dias obtenerFacturasDelProductorProximasAVencerEn12Dias(final ParamObtenerFacturasDelProductorProximasAVencerEn12Dias param);

	 ResultObtenerFacturasDelProductorProximasAVencer obtenerFacturasDelProductorProximasAVencer(final ParamObtenerFacturasDelProductorProximasAVencer param);

	 ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores(
			final ParamObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores param);

	 ResultObtenerListadoControlADTBPSParaProductor obtenerListadoControlADTBPSParaProductor(final ParamObtenerListadoControlADTBPSParaProductor param);

	 ResultObtenerIncentivoPlusTotales obtenerIncentivoPlusTotales(final ParamObtenerIncentivoPlusTotales param);

	 ResultObtenerPolizasSinMatriculasXProductores obtenerPolizasSinMatriculasXProductores(final ParamObtenerPolizasSinMatriculasXProductores param);

	 ResultObtenerClientesCorredores obtenerClientesCorredores(final ParamObtenerClientesCorredores param);

	 ResultObtenerIncentivoPlusDetalle obtenerIncentivoPlusDetalle(final ParamObtenerIncentivoPlusDetalle param);

	 ResultObtenerPolizasAdheridasDebitoAutomatico obtenerPolizasAdheridasDebitoAutomatico(final ParamObtenerPolizasAdheridasDebitoAutomatico param);

	 ResultObtenerLogsUsuariosProductor obtenerLogsUsuariosProductor(final ParamObtenerLogsUsuariosProductor param);
	 
	 ResultObtenerNombreFicheros obtenerNombreFicheros(final ParamObtenerNombreFicheros param);
	 
	 ResultObtenerRutaDescargaUsuario obtenerRutaDescargaUsuario(final ParamObtenerRutaDescargaUsuario param);
	 
	 ResultObtenerResumenEmision obtenerResumenEmision(final ParamObtenerResumenEmision param);
	 
	 ResultObtenerTotalesResumenEmision obtenerTotalesResumenEmision(final ParamObtenerTotalesResumenEmision param);
	 
	 ResultCodiguera obtenerListaReportesResumenEmision(final ParamListaReportesResumenEmision param);
	 
	 ResultObtenerSeguroAutomovil obtenerSeguroAutomovil(final ParamObtenerSeguroAutomovil param);

	 ResultObtenerNuevaTarifaIncendioProductor obtenerNuevaTarifaIncendioProductor(final ParamObtenerNuevaTarifaIncendioProductor param);

	 ResultObtenerConsultasExportables obtenerConsultasExportables(ParamObtenerConsultasExportables param);
	 
	

}