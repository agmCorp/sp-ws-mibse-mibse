package uy.com.bse.mibse.sp.ws.mibse.config;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.binding.soap.SoapBindingConfiguration;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uy.com.bse.mibse.sp.ws.mibse.ws.IWsServiciosMiBse;

import javax.xml.namespace.QName;


@Configuration
public class WebServiceConfig {
    @Autowired
    private Bus bus;

    @Autowired
    private IWsServiciosMiBse wsServiciosMiBse;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, wsServiciosMiBse);
        endpoint.setServiceName(new QName("http://ws.bse.com.uy/", "wsServiciosMiBseService"));
        endpoint.setEndpointName(new QName("http://ws.bse.com.uy/", "wsServiciosMiBsePort"));
        endpoint.setBindingUri("http://schemas.xmlsoap.org/wsdl/soap/http");
        // Configuración de binding personalizada
        SoapBindingConfiguration bindingConfig = new SoapBindingConfiguration();
        bindingConfig.setBindingName(new QName("http://ws.bse.com.uy/", "wsServiciosMiBseBinding"));
        bindingConfig.setTransportURI("http://schemas.xmlsoap.org/soap/http");
        endpoint.setBindingConfig(bindingConfig);
        endpoint.publish("/WsServiciosMiBse");
        return endpoint;
    }

}
