package corp.airbus.helicopters.myApp.security.authentication.stub;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import corp.airbus.helicopters.myApp.security.Role;
import corp.airbus.helicopters.myApp.security.authentication.AuthenticationDetail;

/**
 * The Class DevAuthenticationDetailProvider.
 */
public class DevAuthenticationDetailProvider implements AuthenticationDetailsSource<HttpServletRequest, AuthenticationDetail> {

	/** The principal request header. */
	private String principalRequestHeader;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.AuthenticationDetailsSource
	 * #buildDetails(java.lang.Object)
	 */
	@Override
	public AuthenticationDetail buildDetails(final HttpServletRequest context) {
		final String userId = context.getHeader(principalRequestHeader);

		assert userId != null : "authenticated user id should be not null";

		final List<Role> roles = new LinkedList<Role>();

		if ("admin".equalsIgnoreCase(userId)) {
			roles.add(Role.ADMIN);
		} else if ("customer".equalsIgnoreCase(userId)) {
			roles.add(Role.CUSTOMER);
		}

		AuthenticationDetail auth = new AuthenticationDetail(roles);
		auth.setLogin(userId);

		return auth;
	}

	/**
	 * Sets the principal request header.
	 * 
	 * @param principalRequestHeader
	 *            the new principal request header
	 */
	public void setPrincipalRequestHeader(final String principalRequestHeader) {
		this.principalRequestHeader = principalRequestHeader;
	}
}
