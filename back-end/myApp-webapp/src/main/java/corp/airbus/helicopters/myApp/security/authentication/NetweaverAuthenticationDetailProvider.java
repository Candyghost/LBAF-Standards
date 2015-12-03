package corp.airbus.helicopters.myApp.security.authentication;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import corp.airbus.helicopters.myApp.security.Right;

/**
 * Retrieve user data from application server.
 */
public class NetweaverAuthenticationDetailProvider implements AuthenticationDetailsSource<HttpServletRequest, AuthenticationDetail> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.AuthenticationDetailsSource
	 * #buildDetails(java.lang.Object)
	 */
	@Override
	public AuthenticationDetail buildDetails(final HttpServletRequest context) {
		final List<Right> rights = new LinkedList<Right>();

		for (Right code : Right.values()) {
			if (context.isUserInRole(code.name())) {
				rights.add(Right.valueOf(code.name()));
			}
		}

		AuthenticationDetail auth = new AuthenticationDetail(rights);
		if (context.getUserPrincipal() != null) {
			auth.setLogin(context.getUserPrincipal().getName());
		}

		return auth;
	}
}
