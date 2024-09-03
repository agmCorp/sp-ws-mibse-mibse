package uy.com.bse.mibse.sp.ws.mibse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mibse")
public class MiBseProperties {

    private String obtenerDatosCliente;
    private String procObtenerDatosCliente;
    private String obtenerPolizas;
    private String procObtenerPolizas;
    private String funObtenerNumeroCliente;
    private String funValidarCodigoAdhesion;
    private String procActualizarFacturacionPoliza;
    private String obtenerDatosComunicacionesPersona;
    private String procObtenerDatosComunicacionesPersona;
    private String procActualizarDatosCliente;
    private String procActualizarDatosComunicaionesPersona;
    private String funObtenerMail;
    private String funObtenerDatosValidados;
    private String funVerificarUsuarioDuplicado;
    private String procEnviarMailWS;
    private String procLogActividadMibseWsExt;
    private String procValidarSOA;
    private String procValidarCartaVerde;
    private String procGentOrdenes;
    private String procBorrarMailPersona;
    private String procModificarMailPersona;
    private String procAltaMailPersona;
    private String funObtenerMailsEnvioFacturaPersona;
    private String procValidarCertificadoLibreDeudaADT;
    private String procObtenerMapaMsgSiniestroMiBse;
    private String procProCarta;
    private String funCorrespondeCartaPoliza;
    private String procObtenerPolizasFacturasPagasMiBse;
    private String procAdherirFacturaDigital;

    // Getters y Setters
    public String getObtenerDatosCliente() {
        return obtenerDatosCliente;
    }

    public void setObtenerDatosCliente(String obtenerDatosCliente) {
        this.obtenerDatosCliente = obtenerDatosCliente;
    }

    public String getProcObtenerDatosCliente() {
        return procObtenerDatosCliente;
    }

    public void setProcObtenerDatosCliente(String procObtenerDatosCliente) {
        this.procObtenerDatosCliente = procObtenerDatosCliente;
    }

    public String getObtenerPolizas() {
        return obtenerPolizas;
    }

    public void setObtenerPolizas(String obtenerPolizas) {
        this.obtenerPolizas = obtenerPolizas;
    }

    public String getFunObtenerNumeroCliente() {
        return funObtenerNumeroCliente;
    }

    public void setFunObtenerNumeroCliente(String funObtenerNumeroCliente) {
        this.funObtenerNumeroCliente = funObtenerNumeroCliente;
    }

    public String getFunValidarCodigoAdhesion() {
        return funValidarCodigoAdhesion;
    }

    public void setFunValidarCodigoAdhesion(String funValidarCodigoAdhesion) {
        this.funValidarCodigoAdhesion = funValidarCodigoAdhesion;
    }

    public String getProcActualizarFacturacionPoliza() {
        return procActualizarFacturacionPoliza;
    }

    public void setProcActualizarFacturacionPoliza(String procActualizarFacturacionPoliza) {
        this.procActualizarFacturacionPoliza = procActualizarFacturacionPoliza;
    }

    public String getProcObtenerDatosComunicacionesPersona() {
        return procObtenerDatosComunicacionesPersona;
    }

    public void setProcObtenerDatosComunicacionesPersona(String procObtenerDatosComunicacionesPersona) {
        this.procObtenerDatosComunicacionesPersona = procObtenerDatosComunicacionesPersona;
    }

    public String getObtenerDatosComunicacionesPersona() {
        return obtenerDatosComunicacionesPersona;
    }

    public void setObtenerDatosComunicacionesPersona(String procObtenerDatosComunicacionesPersona) {
        this.obtenerDatosComunicacionesPersona = procObtenerDatosComunicacionesPersona;
    }


    public String getProcActualizarDatosCliente() {
        return procActualizarDatosCliente;
    }

    public void setProcActualizarDatosCliente(String procActualizarDatosCliente) {
        this.procActualizarDatosCliente = procActualizarDatosCliente;
    }

    public String getProcActualizarDatosComunicaionesPersona() {
        return procActualizarDatosComunicaionesPersona;
    }

    public void setProcActualizarDatosComunicaionesPersona(String procActualizarDatosComunicaionesPersona) {
        this.procActualizarDatosComunicaionesPersona = procActualizarDatosComunicaionesPersona;
    }

    public String getFunObtenerMail() {
        return funObtenerMail;
    }

    public void setFunObtenerMail(String funObtenerMail) {
        this.funObtenerMail = funObtenerMail;
    }

    public String getFunObtenerDatosValidados() {
        return funObtenerDatosValidados;
    }

    public void setFunObtenerDatosValidados(String funObtenerDatosValidados) {
        this.funObtenerDatosValidados = funObtenerDatosValidados;
    }

    public String getFunVerificarUsuarioDuplicado() {
        return funVerificarUsuarioDuplicado;
    }

    public void setFunVerificarUsuarioDuplicado(String funVerificarUsuarioDuplicado) {
        this.funVerificarUsuarioDuplicado = funVerificarUsuarioDuplicado;
    }

    public String getProcEnviarMailWS() {
        return procEnviarMailWS;
    }

    public void setProcEnviarMailWS(String procEnviarMailWS) {
        this.procEnviarMailWS = procEnviarMailWS;
    }

    public String getProcLogActividadMibseWsExt() {
        return procLogActividadMibseWsExt;
    }

    public void setProcLogActividadMibseWsExt(String procLogActividadMibseWsExt) {
        this.procLogActividadMibseWsExt = procLogActividadMibseWsExt;
    }

    public String getProcValidarSOA() {
        return procValidarSOA;
    }

    public void setProcValidarSOA(String procValidarSOA) {
        this.procValidarSOA = procValidarSOA;
    }

    public String getProcValidarCartaVerde() {
        return procValidarCartaVerde;
    }

    public void setProcValidarCartaVerde(String procValidarCartaVerde) {
        this.procValidarCartaVerde = procValidarCartaVerde;
    }

    public String getProcGentOrdenes() {
        return procGentOrdenes;
    }

    public void setProcGentOrdenes(String procGentOrdenes) {
        this.procGentOrdenes = procGentOrdenes;
    }

    public String getProcBorrarMailPersona() {
        return procBorrarMailPersona;
    }

    public void setProcBorrarMailPersona(String procBorrarMailPersona) {
        this.procBorrarMailPersona = procBorrarMailPersona;
    }

    public String getProcModificarMailPersona() {
        return procModificarMailPersona;
    }

    public void setProcModificarMailPersona(String procModificarMailPersona) {
        this.procModificarMailPersona = procModificarMailPersona;
    }

    public String getProcAltaMailPersona() {
        return procAltaMailPersona;
    }

    public void setProcAltaMailPersona(String procAltaMailPersona) {
        this.procAltaMailPersona = procAltaMailPersona;
    }

    public String getFunObtenerMailsEnvioFacturaPersona() {
        return funObtenerMailsEnvioFacturaPersona;
    }

    public void setFunObtenerMailsEnvioFacturaPersona(String funObtenerMailsEnvioFacturaPersona) {
        this.funObtenerMailsEnvioFacturaPersona = funObtenerMailsEnvioFacturaPersona;
    }

    public String getProcValidarCertificadoLibreDeudaADT() {
        return procValidarCertificadoLibreDeudaADT;
    }

    public void setProcValidarCertificadoLibreDeudaADT(String procValidarCertificadoLibreDeudaADT) {
        this.procValidarCertificadoLibreDeudaADT = procValidarCertificadoLibreDeudaADT;
    }

    public String getProcObtenerMapaMsgSiniestroMiBse() {
        return procObtenerMapaMsgSiniestroMiBse;
    }

    public void setProcObtenerMapaMsgSiniestroMiBse(String procObtenerMapaMsgSiniestroMiBse) {
        this.procObtenerMapaMsgSiniestroMiBse = procObtenerMapaMsgSiniestroMiBse;
    }

    public String getProcProCarta() {
        return procProCarta;
    }

    public void setProcProCarta(String procProCarta) {
        this.procProCarta = procProCarta;
    }

    public String getFunCorrespondeCartaPoliza() {
        return funCorrespondeCartaPoliza;
    }

    public void setFunCorrespondeCartaPoliza(String funCorrespondeCartaPoliza) {
        this.funCorrespondeCartaPoliza = funCorrespondeCartaPoliza;
    }

    public String getProcObtenerPolizasFacturasPagasMiBse() {
        return procObtenerPolizasFacturasPagasMiBse;
    }

    public void setProcObtenerPolizasFacturasPagasMiBse(String procObtenerPolizasFacturasPagasMiBse) {
        this.procObtenerPolizasFacturasPagasMiBse = procObtenerPolizasFacturasPagasMiBse;
    }

    public String getProcAdherirFacturaDigital() {
        return procAdherirFacturaDigital;
    }

    public void setProcAdherirFacturaDigital(String procAdherirFacturaDigital) {
        this.procAdherirFacturaDigital = procAdherirFacturaDigital;
    }

    // Métodos para obtener el CatalogName y ProcedureName

    public String getCatalogName(String property) {
        if (property != null && property.contains(".")) {
            return property.substring(0, property.lastIndexOf('.'));
        }
        return null;
    }

    public String getProcedureName(String property) {
        if (property != null && property.contains(".")) {
            return property.substring(property.lastIndexOf('.') + 1);
        }
        return null;
    }


}
