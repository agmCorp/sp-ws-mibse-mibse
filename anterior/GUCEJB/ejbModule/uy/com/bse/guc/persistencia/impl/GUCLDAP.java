package uy.com.bse.guc.persistencia.impl;

import uy.com.bse.guc.interfaces.ParamAltaUsuario;
import uy.com.bse.guc.interfaces.ParamCambioClave;
import uy.com.bse.guc.interfaces.ParamLogin;
import uy.com.bse.guc.interfaces.ParamOlvidoClave;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.guc.interfaces.ResultAltaUsuario;
import uy.com.bse.guc.interfaces.ResultCambioClave;
import uy.com.bse.guc.interfaces.ResultClaveUsada;
import uy.com.bse.guc.interfaces.ResultLogin;
import uy.com.bse.guc.interfaces.ResultOlvidoClave;
import uy.com.bse.guc.interfaces.ResultValidar;
import uy.com.bse.guc.persistencia.GUCDataProvider;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;

//FIXME OIGRES IMPLEMENTAR GUC LDAP
public class GUCLDAP implements GUCDataProvider {

	@Override
	public ResultLogin login(ParamLogin param) {
		return  getErrorNotImplemented(new ResultLogin());
	}

	@Override
	public ResultValidar validar(ParamValidar param) {
		return  getErrorNotImplemented(new ResultValidar());
	}

	@Override
	public ResultAltaUsuario altaUsuario(ParamAltaUsuario param) {
		return  getErrorNotImplemented(new ResultAltaUsuario());
	}

	@Override
	public ResultCambioClave cambioClave(ParamCambioClave param) {
		return  getErrorNotImplemented(new ResultCambioClave());
	}

	@Override
	public ResultOlvidoClave olvidoClave(ParamOlvidoClave param) {
		return getErrorNotImplemented(new ResultOlvidoClave());
	}
	
	private <T extends ResultGenerico>T getErrorNotImplemented(T result){
		result.setHayError(Boolean.TRUE);
		result.setError(new ServiciosError(600, "FUNCIONALIDAD NO IMPLEMENTADA"));
		return result;
	}

	@Override
	public ResultClaveUsada verificarClaveUsadaAnteriormente(ParamCambioClave param) {
		return getErrorNotImplemented(new ResultClaveUsada());
	}
	
	/*
	 LdapConnectionConfig config = new LdapConnectionConfig();
config.setLdapHost( hostname );
config.setLdapPort( port );
config.setName( adminDn );
config.setCredentials( adminPassword );

DefaultLdapConnectionFactory factory = new DefaultLdapConnectionFactory( config );
factory.setTimeOut( connectionTimeout );

// optional, values below are defaults
GenericObjectPool.Config poolConfig = new GenericObjectPool.Config();
poolConfig.lifo = true;
poolConfig.maxActive = 8;
poolConfig.maxIdle = 8;
poolConfig.maxWait = -1L;
poolConfig.minEvictableIdleTimeMillis = 1000L * 60L * 30L;
poolConfig.minIdle = 0;
poolConfig.numTestsPerEvictionRun = 3;
poolConfig.softMinEvictableIdleTimeMillis = -1L;
poolConfig.testOnBorrow = false;
poolConfig.testOnReturn = false;
poolConfig.testWhileIdle = false;
poolConfig.timeBetweenEvictionRunsMillis = -1L;
poolConfig.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;

LdapConnectionPool pool = new LdapConnectionPool(
    new DefaultPoolableLdapConnectionFactory( factory ), poolConfig ) );
    
    
    
    MAY ( pwdMinAge $ pwdMaxAge $ pwdInHistory $ pwdCheckQuality $
      pwdMinLength $ pwdExpireWarning $ pwdGraceAuthNLimit $ pwdLockout
      $ pwdLockoutDuration $ pwdMaxFailure $ pwdFailureCountInterval $
      pwdMustChange $ pwdAllowUserChange $ pwdSafeModify ) )
	 */

	
	/*PasswordPolicyResponseValue ::= SEQUENCE {
	      warning [0] CHOICE {
	         timeBeforeExpiration [0] INTEGER (0 .. maxInt),
	         graceAuthNsRemaining [1] INTEGER (0 .. maxInt) } OPTIONAL,
	      error   [1] ENUMERATED {
	         passwordExpired             (0),
	         accountLocked               (1),
	         changeAfterReset            (2),
	         passwordModNotAllowed       (3),
	         mustSupplyOldPassword       (4),
	         insufficientPasswordQuality (5),
	         passwordTooShort            (6),
	         passwordTooYoung            (7),
	         passwordInHistory           (8) } OPTIONAL }*/
}
