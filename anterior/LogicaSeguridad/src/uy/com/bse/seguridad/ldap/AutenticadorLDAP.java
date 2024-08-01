package uy.com.bse.seguridad.ldap;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.PartialResultException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.seguridad.properties.LdapConstants;
import uy.com.bse.seguridad.properties.SeguridadServiciosProperty;
import uy.com.bse.utilitario.seguridad.GroupMembership;

/**
1) Para importar el certificado en el JBOSS
[bfagalde@bfagaldeX230 security]$ sudo /home/bfagalde/myprogs/JBOSS/jres/jre1.6.0_45/bin/keytool -import -alias BSEca -file /media/ADATA\ UFD/CAs/CA-BSE.DER.x509.cer -keystore cacerts -storepass changeit
[bfagalde@bfagaldeX230 security]$ pwd
/home/bfagalde/myprogs/JBOSS/jres/jre1.6.0_45/lib/security
 *  
 * @author bfagalde
 *
 */
public final class AutenticadorLDAP {

	private static final Logger LOG = LogManager.getLogger(AutenticadorLDAP.class);
	private static final String LDAP_GROUP = "PORTAL_PRODUCTOR_SERVICIOS";
	private static Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11);
	public static String LDAPURL = SeguridadServiciosProperty.getLDAP_URL();

	private static LdapContext ldapContext = null;
	private static String defaultNamingContext = "";

	private static Properties buildInitialProps(String url) throws LDAPException {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		props.put(Context.REFERRAL, "follow");

		props.put(Context.SECURITY_AUTHENTICATION, "simple");

		if (url != null) {
			props.put(Context.PROVIDER_URL, url);
		} else {
			throw new LDAPException("There is no url defined!!!");
		}
		return props;

	}

	public static void checkLDAP(String url, String userName, String password) throws LDAPException {
		Properties props = buildInitialProps(url);
		InitialDirContext context = null;
		if (userName != null) {
			String principalName = buildPrincipal(userName);
			props.put(Context.SECURITY_PRINCIPAL, principalName);

		} else {
			throw new LDAPException("Usuario nulo!!!");
		}
		if (password != null) {
			props.put(Context.SECURITY_CREDENTIALS, password);
		} else {
			throw new LDAPException("Clave del usuario nula!!!");
		}

		if (url.toUpperCase().startsWith("LDAPS://")) {
			LOG.debug("checkLDAP con LDAPS: " + url);
			addSSLToEnvironment(props);
		} else {
			LOG.warn("checkLDAP con LDAP no seguro: " + url);
		}

		String timeoutLDAP = SeguridadServiciosProperty.getLDAP_Timeout();
		props.put("com.sun.jndi.ldap.read.timeout", timeoutLDAP);
		LOG.info("checkLDAP para usuario: " + userName);
		try {
			context = new InitialDirContext(props);
			LOG.info("checkLDAP OK");
		} catch (NamingException e) {
			processError(e);
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (NamingException ex) {
					LOG.error("Exception when closing context!", ex);
				}
			}
		}

	}

	public static void checkLDAPGroup(String url, String userName, String password) throws LDAPException {
		Properties props = buildInitialProps(url);
		InitialDirContext context = null;
		if (userName != null) {
			String principalName = buildPrincipal(userName);
			props.put(Context.SECURITY_PRINCIPAL, principalName);

		} else {
			throw new LDAPException("Usuario nulo!!!");
		}
		if (password != null) {
			props.put(Context.SECURITY_CREDENTIALS, password);
		} else {
			throw new LDAPException("Clave del usuario nula!!!");
		}

		if (url.toUpperCase().startsWith("LDAPS://")) {
			LOG.debug("checkLDAP con LDAPS: " + url);
			addSSLToEnvironment(props);
		} else {
			LOG.warn("checkLDAP con LDAP no seguro: " + url);
		}
		GroupMembership group = authenticateUserToGroup("bse.com.uy", userName, LDAP_GROUP);
		if (group.isMember()) {

			System.out.println("VIVA LUUUUUUU");
		} else {
			System.out.println("LOOSER VICKY");

		}

		String timeoutLDAP = SeguridadServiciosProperty.getLDAP_Timeout();
		props.put("com.sun.jndi.ldap.read.timeout", timeoutLDAP);
		LOG.info("checkLDAP para usuario: " + userName);
		try {
			context = new InitialDirContext(props);
			LOG.info("checkLDAP OK");
		} catch (NamingException e) {
			processError(e);
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (NamingException ex) {
					LOG.error("Exception when closing context!", ex);
				}
			}
		}

	}

	private static String buildPrincipal(String userName) {
		String dominio = "@bse.com.uy";
		int x = userName.indexOf(dominio);
		if (x <= 0) {
			return userName + dominio;
		} else {
			return userName;
		}
	}

	private static void processError(Exception e) throws LDAPException {

		if (e instanceof javax.naming.CommunicationException) {
			System.err.println("Cannot communicate to LDAP");
			LOG.error("Cannot communicate to LDAP");
			javax.naming.CommunicationException ce = (javax.naming.CommunicationException) e;
			Throwable cause = ce.getCause();
			if (cause != null) {
				System.err.println("Cause: " + cause.getMessage());
				LOG.error("Cause: " + cause.getMessage(), cause);
				cause.printStackTrace();

			} else {
				System.err.println("Comunication error unkown!");
				LOG.error("Comunication error unkown!", ce);
				ce.printStackTrace();
			}
		} else if (e instanceof javax.naming.AuthenticationException) {
			System.err.println("Autentication error : " + e.getMessage());
			LOG.error("Autentication error : " + e.getMessage(), e);
			/*
    		 *	Case of Microsoft AD:
    		 *  [LDAP: error code 49 - 80090308: LdapErr: DSID-0Cxxxxxx, comment: AcceptSecurityContext error, data xxx, vece ]
    		 *  xxx value: 
    		 *  525 	user not found
    		 *  52e 	invalid credentials
    		 *  530 	not permitted to logon at this time
    		 *  531 	not permitted to logon at this workstation
    		 *  532 	password expired
    		 *  533		account disabled
    		 *  534 	The user has not been granted the requested logon type at this machine
    		 *  701 	account expired
    		 *  773 	user must reset password
    		 *  775 	user account locked
    		 */
		} else {
			LOG.error("Unkown error!", e);
			e.printStackTrace();
		}
		throw new LDAPException("No se pudo autenticar");
	}

	private static void addSSLToEnvironment(Properties props) {
		props.put(Context.SECURITY_PROTOCOL, "ssl");
		props.put(Context.SECURITY_AUTHENTICATION, "simple");
	}

	public static boolean validate(String user, String password) {
		boolean salida = AutenticadorCache.validar(user, password);
		if (!salida) {
			salida = validateLDAP(user, password);
		}
		return salida;
	}

	public static boolean validateGroup(String user, String password) {
		boolean salida = AutenticadorCache.validar(user, password);
		if (!salida) {
			salida = validateLDAPGroup(user, password);
		}
		return salida;
	}

	private static boolean validateLDAP(String user, String password) {
		synchronized (user) {
			boolean salida = AutenticadorCache.validar(user, password);
			if (!salida) {
				try {

					String LDAP_URL = SeguridadServiciosProperty.getLDAP_URL();
					LOG.info("usando LDAP: " + LDAP_URL);

					checkLDAP(LDAP_URL, user, password);
					salida = true;
					AutenticadorCache.agregar(user, password);
				} catch (LDAPException e) {
					LOG.error("No pudo validar: " + e);
					salida = false;
				}
			}
			return salida;
		}
	}

	public static String getUserName(String fullName, String appUser, String password) throws uy.com.bse.seguridad.ldap.LDAPException {

		try {
			ldapEnv = new Hashtable(11);
			String LDAP_URL = SeguridadServiciosProperty.getLDAP_URL();
			ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			ldapEnv.put(Context.REFERRAL, "follow");
			ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			if (LDAP_URL.toUpperCase().startsWith("LDAPS://")) {
				ldapEnv.put(Context.SECURITY_PROTOCOL, "ssl");
			}
			ldapEnv.put(Context.PROVIDER_URL, LDAP_URL);
			ldapContext = new InitialLdapContext(ldapEnv, null);

			ldapContext.addToEnvironment(Context.SECURITY_PRINCIPAL, appUser);
			ldapContext.addToEnvironment(Context.SECURITY_CREDENTIALS, password);

			ldapContext.reconnect(null);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (ldapContext != null) {
			DirContext ctx = ldapContext;

			// Create the search controls
			SearchControls searchCtls = new SearchControls();

			// Specify the attributes to return
			String returnedAtts[] = { "uid", "sn", "givenName", "mail", "cn", "Initials", "name", "userPrincipalName", "description", "name", "displayName", "pwdLastSet", "distinguishedName",
					"memberOf", "userAccountControl", "title" };
			searchCtls.setReturningAttributes(returnedAtts);

			// Specify the search scope
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			// FIXME OIGRES BORRAR ESTO LUEGO
			// specify the LDAP search filter
			// String searchFilter = "(&(objectClass=person)(CN=Álvarez Pérez\\, Oigres
			// (IBM),OU=Usuarios externos)";
			// String searchFilter = "(&(objectClass=groupOfNames)(CN=Álvarez Pérez\\,
			// Oigres (IBM)))";
			// String searchFilter = "(&(CN=Álvarez Pérez\\, Oigres (IBM)))";
			String searchFilter = "(&(objectClass=person))";
			String searchBase = defaultNamingContext = fullName;

			try {
				NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);
				// answer =ctx.search(searchBase, searchFilter, new Object[]{"CN=Álvarez
				// Pérez\\, Oigres (IBM)","OU=Usuarios externos"}, searchCtls);
				if (answer.hasMoreElements()) {
					SearchResult sr = (SearchResult) answer.next();

					Attributes attrs = sr.getAttributes();
					if (attrs != null && attrs.get("userprincipalname") != null) {
						Attribute value = attrs.get("userprincipalname");
						return value.get(0).toString();
					}
				}
			} catch (NamingException e) {
				LOG.error(e);
			}

		}
		throw new uy.com.bse.seguridad.ldap.LDAPException("Usuario no encontrado para " + fullName);
	}

	private static boolean validateLDAPGroup(String user, String password) {
		synchronized (user) {
			boolean salida = AutenticadorCache.validar(user, password);
			if (!salida) {
				try {
					LDAPURL = SeguridadServiciosProperty.getLDAP_URL();

					LOG.info("usando LDAP: " + LDAPURL);

					checkLDAPGroup(LDAPURL, user, password);
					salida = true;
					AutenticadorCache.agregar(user, password);
				} catch (LDAPException e) {

					LOG.error("No pudo validar: " + e);
					salida = false;
				}
			}
			return salida;
		}
	}

	public static String obtenerNumeroCorredor(String userName) {
		String salida = null;
		InitialDirContext context = null;

		try {
			String LDAP_URL = SeguridadServiciosProperty.getLDAP_URL();
			String timeoutLDAP = SeguridadServiciosProperty.getLDAP_Timeout();
			Properties props = buildInitialProps(LDAP_URL);
			props.put("com.sun.jndi.ldap.read.timeout", timeoutLDAP);
			context = new InitialDirContext(props);
			LOG.info("Valid");
			Attributes atts = context.getAttributes("cn=" + userName + ", ou=BSE");

		} catch (Exception e) {
			// processError(e);
			LOG.error("al ir al ldap:" + e);
		} finally {
			LOG.debug("finally");
			try {
				if (context != null) {
					context.close();
				}
			} catch (Exception ex) {
				LOG.error("Exception when closing context!", ex);
			}
		}
		return salida;
	}

	// VICKY
	public static boolean groupMember(String groupName, String userName) throws LDAPException {
		InitialDirContext ldapContext = null;
		String dominio = "@bse.com.uy";
		String LDAP_URL = SeguridadServiciosProperty.getLDAP_URL();
		String timeoutLDAP = SeguridadServiciosProperty.getLDAP_Timeout();
		Properties props = buildInitialProps(LDAP_URL);
		props.put("com.sun.jndi.ldap.read.timeout", timeoutLDAP);

		String defaultNamingContext = "";
		try {
			ldapContext = new InitialDirContext(props);
			if (ldapContext != null) {
				// Create the initial directory context
				DirContext ctx = ldapContext;

				// Create the search controls
				SearchControls searchCtls = new SearchControls();

				// Specify the search scope
				searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

				// specify the LDAP search filter
				String searchFilter = "(&(objectClass=user)(sAMAccountName=" + userName + ")(memberOf=" + groupName + "))";
				// defaultNamingContext = getNamingContext(dominio);
				String searchBase = defaultNamingContext;

				// String returnedAtts[]={"memberOf"};
				// searchCtls.setReturningAttributes(returnedAtts);

				// Search for objects using the filter
				NamingEnumeration answer = ctx.search(dominio, searchFilter, searchCtls);

				// Loop through the search results
				if (answer.hasMoreElements()) {
					return true;
				}
				return false;
			} else {
				throw new Exception("groupMember: Error al conectar al AD.");
			}

		} catch (NamingException e) {
			// throw new LDAPException("groupMember: Problem searching directory. " +
			// e.getMessage());
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	// VICKY
	/**
	 * Busca el valor de un determinado atributo para el grupo groupName.
	 * 
	 * @param groupName
	 *            - sAMAccountName del grupo.
	 * @param propertyName
	 *            - nombre del atributo buscado.
	 * @return Retorna el valor del atributo propertyName para el grupo groupName.
	 *         Si no existe retorna null.
	 * @throws Exception
	 * @throws BSEException
	 *             - Si se obtiene una Exception.
	 */

	public static String searchGroupProperty(String groupName, String propertyName) throws Exception {

		Properties props = buildInitialProps(LDAPURL);
		InitialDirContext ldapContext = new InitialDirContext(props);

		try {
			if (ldapContext != null) {
				DirContext ctx = ldapContext;
				// Create the search controls
				SearchControls searchCtls = new SearchControls();

				// Specify the attributes to return
				String returnedAtts[] = { propertyName };
				searchCtls.setReturningAttributes(returnedAtts);

				// Specify the search scope
				searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

				String searchFilter = "(&(objectClass=group)(sAMAccountName=" + groupName + "))";

				defaultNamingContext = getNamingContext("bse.com.uy");

				String searchBase = defaultNamingContext;

				// Search for objects using the filter
				NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);

				// Loop through the search results
				// User user= null;
				if (answer.hasMoreElements()) {
					SearchResult sr = (SearchResult) answer.next();

					// Print out some of the attributes, catch the exception if the attributes have
					// no values
					Attributes attrs = sr.getAttributes();
					if (attrs != null) {
						try {
							return attrs.get(propertyName) != null ? (String) attrs.get(propertyName).get() : "";
						} catch (NullPointerException e) {
							throw new Exception("searchGroupProperty: Errors al listar los atributos. " + e.getMessage());
						}
					}
				}
				// ctx.close();
				return null;
			} else {
				throw new LDAPException("searchGroupProperty: Error al conectar al AD");
			}
		} catch (PartialResultException e) {
			throw new LDAPException("searchGroupProperty: Resultados parciales. " + e.getMessage());
		} catch (NamingException e) {
			throw new LDAPException("searchGroupProperty: NamingException searching directory. " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("searchGroupProperty: Exception al cargar las propiedades del usuario. " + e.getMessage());
		}
	}

	// VICKY
	public static String getNamingContext(String domain) {
		String searchBase = "";

		// Verifica que le dominio no estï¿½ vacï¿½o.
		if (domain != null && !domain.equals("")) {
			// Obtiene los componentes del dominio separados por el "."
			String[] domainComponents = domain.split("\\.");
			// Verifica que el dominio tenga lo tres componentes
			if (domainComponents.length == 3) {
				for (int i = 0; i < domainComponents.length; i++) {
					searchBase += "dc=" + domainComponents[i];
					if (i < domainComponents.length - 1) {
						searchBase += ",";
					}
				}
				// return searchBase;
			}
		}
		return searchBase;
	}

	// VICKY
	public static void crearConnection(String domain, boolean ssl) throws Exception, LDAPException {
		try {
			ldapEnv = new Hashtable(11);
			ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			ldapEnv.put(Context.REFERRAL, "follow");
			ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");

			// FILELog.info("[ADConnection] NO utilizando SSL");
			ldapEnv.put(Context.PROVIDER_URL, LDAPURL);

			ldapContext = new InitialLdapContext(ldapEnv, null);
			defaultNamingContext = getNamingContext(domain);
		} catch (NamingException e) {
			throw new LDAPException("ADConnection: NamingException al crear la conexiï¿½n. Message=" + e.getMessage() + ". Cause=" + e.getCause().getMessage() + ". Keystore=");
		} catch (Exception e) {
			throw new Exception("ADConnection: Exception al crear la conexiï¿½n. " + e.getMessage());
		}

	}

	// VICKY
	public static GroupMembership authenticateUserToGroup(String domain, String userName, String groupName) {

		GroupMembership member = new GroupMembership();

		try {

			if (domain != null && !domain.equals("") && userName != null && !userName.equals("") && groupName != null && !groupName.equals("")) {

				// Primero se autentica al usuario

				// Primero se autentica al usuario
				String respCode = authenticate(domain, userName, "Julio.2018");
				member.setErrorCode(respCode);
				if (respCode.equals(LdapConstants.OK)) {

					String distinguishedName = searchGroupProperty(groupName, "distinguishedName");
					member.setMember(groupMember(distinguishedName, userName));
				}
			}
		} catch (LDAPException e) {
			member.setErrorCode(LdapConstants.LDAP_CONNECTION_ERROR);
			//FILELog.error("[authenticateUserToGroup] ERROR (domain=" + domain + ", user=" + user + ", clientIP=" + clientIP + ") ERROR_CODE=" + member.getErrorCode() + ", EXCEPTION=" + e.getMessage());
			return member;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return member;
		}
		return member;
	}

	/**
	   * Autentica al usuario en el dominio en el AD
	   * @param domain - indica en que dominio se realiza la autenticacion.
	   * @param user - sAMAaccountName del usuario a autenticar.
	   * @param password - clave del usuario a autenticar.
	   * @return Retorna un codigo de error obtenido al realizar la autenticacion.
	   * ==00 - OK
	   * <>00 - Error de autenticacion
	   * 
	   */
	public static String authenticate(String domain, String user, String password) {
		String toReturn = "";

		try {
			String loginName = user + "@" + domain;

			// ldapEnv.put(Context.SECURITY_PRINCIPAL, loginName);
			// ldapEnv.put(Context.SECURITY_CREDENTIALS, password);

			ldapContext.addToEnvironment(Context.SECURITY_PRINCIPAL, loginName);
			ldapContext.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
			ldapContext.reconnect(null);

			toReturn = LdapConstants.OK;

		} catch (AuthenticationException ae) {
			if (ae.getMessage().contains("comment: AcceptSecurityContext error, data 773,"))
				return LdapConstants.USER_MUST_RESET_PASSWORD;
			else if ((ae.getMessage().contains("comment: AcceptSecurityContext error, data 525,")) || (ae.getMessage().contains("comment: AcceptSecurityContext error, data 52e,")))
				return LdapConstants.INVALID_CREDENTIALS;
			else if (ae.getMessage().contains("comment: AcceptSecurityContext error, data 533,"))
				return LdapConstants.ACCOUNT_DISABLED;
			else if (ae.getMessage().contains("comment: AcceptSecurityContext error, data 701,"))
				return LdapConstants.ACCOUNT_EXPIRED;
			else if (ae.getMessage().contains("comment: AcceptSecurityContext error, data 530,"))
				return LdapConstants.NOT_PERMITED_TO_LOG_ON;
			else if (ae.getMessage().contains("comment: AcceptSecurityContext error, data 532,"))
				return LdapConstants.PASSWORD_EXPIRES;
			else if (ae.getMessage().contains("comment: AcceptSecurityContext error, data 775,"))
				return LdapConstants.USER_LOCKED;
			else if (ae.getMessage().contains("comment: AcceptSecurityContext error, data 531,"))
				return LdapConstants.NOT_PERMITED_TO_LOG_ON_IN_HOST;
		} catch (NamingException e) {
			return LdapConstants.LDAP_CONNECTION_ERROR;
		} catch (Exception e) {
			return LdapConstants.SYSTEM_ERROR;
		}
		return toReturn;
	}

}
