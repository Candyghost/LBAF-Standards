package corp.airbus.helicopters.myApp.facade.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import corp.airbus.helicopters.myApp.facade.AbstractRestTestSupport;
import corp.airbus.helicopters.myApp.security.Right;

/**
 * The Class MeControllerTest.
 */
public class MeControllerTest extends AbstractRestTestSupport {

	/**
	 * Me.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void me() throws Exception {
		authenticate(new Right[] { Right.GET_ME });

		mockMvc.perform(get("/me").header("host", "localhost:80").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("login").value("admin"));
	}

	/**
	 * Me_forbidden.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void me_forbidden() throws Exception {
		authenticate(new Right[] {});

		mockMvc.perform(get("/me").header("host", "localhost:80").accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}
}
