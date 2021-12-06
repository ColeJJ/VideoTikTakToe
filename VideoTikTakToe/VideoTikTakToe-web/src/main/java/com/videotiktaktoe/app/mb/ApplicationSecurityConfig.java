package com.videotiktaktoe.app.mb;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@FormAuthenticationMechanismDefinition(
		
		loginToContinue = @LoginToContinue(
				loginPage = "/pages/public/login.xhtml",
				errorPage = "/pages/public/loginError.xhtml"
		)
)
@DatabaseIdentityStoreDefinition(

		dataSourceLookup = "java:/OracleDS",
		callerQuery = "select PASSWORD from VTTT_user where USERNAME=?",
		//hashAlgorithm = PlainSHA512PasswordHash.class,
		hashAlgorithm = PlainTextPasswordHash.class

)
		
@ApplicationScoped
@Named
public class ApplicationSecurityConfig {
	
}
