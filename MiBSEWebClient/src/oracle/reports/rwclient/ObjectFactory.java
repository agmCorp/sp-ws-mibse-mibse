
package oracle.reports.rwclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oracle.reports.rwclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAPIVersion_QNAME = new QName("http://oracle.reports/rwclient/", "getAPIVersion");
    private final static QName _GetAPIVersionResponse_QNAME = new QName("http://oracle.reports/rwclient/", "getAPIVersionResponse");
    private final static QName _RunJob_QNAME = new QName("http://oracle.reports/rwclient/", "runJob");
    private final static QName _KillJobResponse_QNAME = new QName("http://oracle.reports/rwclient/", "killJobResponse");
    private final static QName _GetJobInfoResponse_QNAME = new QName("http://oracle.reports/rwclient/", "getJobInfoResponse");
    private final static QName _GetJobInfo_QNAME = new QName("http://oracle.reports/rwclient/", "getJobInfo");
    private final static QName _KillJob_QNAME = new QName("http://oracle.reports/rwclient/", "killJob");
    private final static QName _GetServerInfo_QNAME = new QName("http://oracle.reports/rwclient/", "getServerInfo");
    private final static QName _RunJobResponse_QNAME = new QName("http://oracle.reports/rwclient/", "runJobResponse");
    private final static QName _GetServerInfoResponse_QNAME = new QName("http://oracle.reports/rwclient/", "getServerInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oracle.reports.rwclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetServerInfoResponse }
     * 
     */
    public GetServerInfoResponse createGetServerInfoResponse() {
        return new GetServerInfoResponse();
    }

    /**
     * Create an instance of {@link GetServerInfo }
     * 
     */
    public GetServerInfo createGetServerInfo() {
        return new GetServerInfo();
    }

    /**
     * Create an instance of {@link RunJobResponse }
     * 
     */
    public RunJobResponse createRunJobResponse() {
        return new RunJobResponse();
    }

    /**
     * Create an instance of {@link KillJob }
     * 
     */
    public KillJob createKillJob() {
        return new KillJob();
    }

    /**
     * Create an instance of {@link GetJobInfo }
     * 
     */
    public GetJobInfo createGetJobInfo() {
        return new GetJobInfo();
    }

    /**
     * Create an instance of {@link GetJobInfoResponse }
     * 
     */
    public GetJobInfoResponse createGetJobInfoResponse() {
        return new GetJobInfoResponse();
    }

    /**
     * Create an instance of {@link KillJobResponse }
     * 
     */
    public KillJobResponse createKillJobResponse() {
        return new KillJobResponse();
    }

    /**
     * Create an instance of {@link GetAPIVersion }
     * 
     */
    public GetAPIVersion createGetAPIVersion() {
        return new GetAPIVersion();
    }

    /**
     * Create an instance of {@link GetAPIVersionResponse }
     * 
     */
    public GetAPIVersionResponse createGetAPIVersionResponse() {
        return new GetAPIVersionResponse();
    }

    /**
     * Create an instance of {@link RunJob }
     * 
     */
    public RunJob createRunJob() {
        return new RunJob();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAPIVersion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.reports/rwclient/", name = "getAPIVersion")
    public JAXBElement<GetAPIVersion> createGetAPIVersion(GetAPIVersion value) {
        return new JAXBElement<GetAPIVersion>(_GetAPIVersion_QNAME, GetAPIVersion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAPIVersionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.reports/rwclient/", name = "getAPIVersionResponse")
    public JAXBElement<GetAPIVersionResponse> createGetAPIVersionResponse(GetAPIVersionResponse value) {
        return new JAXBElement<GetAPIVersionResponse>(_GetAPIVersionResponse_QNAME, GetAPIVersionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunJob }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.reports/rwclient/", name = "runJob")
    public JAXBElement<RunJob> createRunJob(RunJob value) {
        return new JAXBElement<RunJob>(_RunJob_QNAME, RunJob.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KillJobResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.reports/rwclient/", name = "killJobResponse")
    public JAXBElement<KillJobResponse> createKillJobResponse(KillJobResponse value) {
        return new JAXBElement<KillJobResponse>(_KillJobResponse_QNAME, KillJobResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetJobInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.reports/rwclient/", name = "getJobInfoResponse")
    public JAXBElement<GetJobInfoResponse> createGetJobInfoResponse(GetJobInfoResponse value) {
        return new JAXBElement<GetJobInfoResponse>(_GetJobInfoResponse_QNAME, GetJobInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetJobInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.reports/rwclient/", name = "getJobInfo")
    public JAXBElement<GetJobInfo> createGetJobInfo(GetJobInfo value) {
        return new JAXBElement<GetJobInfo>(_GetJobInfo_QNAME, GetJobInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KillJob }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.reports/rwclient/", name = "killJob")
    public JAXBElement<KillJob> createKillJob(KillJob value) {
        return new JAXBElement<KillJob>(_KillJob_QNAME, KillJob.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.reports/rwclient/", name = "getServerInfo")
    public JAXBElement<GetServerInfo> createGetServerInfo(GetServerInfo value) {
        return new JAXBElement<GetServerInfo>(_GetServerInfo_QNAME, GetServerInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunJobResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.reports/rwclient/", name = "runJobResponse")
    public JAXBElement<RunJobResponse> createRunJobResponse(RunJobResponse value) {
        return new JAXBElement<RunJobResponse>(_RunJobResponse_QNAME, RunJobResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServerInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.reports/rwclient/", name = "getServerInfoResponse")
    public JAXBElement<GetServerInfoResponse> createGetServerInfoResponse(GetServerInfoResponse value) {
        return new JAXBElement<GetServerInfoResponse>(_GetServerInfoResponse_QNAME, GetServerInfoResponse.class, null, value);
    }

}
