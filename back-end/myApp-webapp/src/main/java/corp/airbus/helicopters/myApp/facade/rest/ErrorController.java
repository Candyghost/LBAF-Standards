package corp.airbus.helicopters.myApp.facade.rest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import corp.airbus.helicopters.efactory.commons.core.controller.AbstractController;
import corp.airbus.helicopters.efactory.commons.core.service.model.IdentifierDTO;
import corp.airbus.helicopters.myApp.model.ErrorDTO;

/**
 * The Class ErrorController.
 */
@Controller
public class ErrorController extends AbstractController<ErrorDTO> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * corp.airbus.helicopters.efactory.commons.core.controller.AbstractController
	 * #all()
	 */
	@ResponseBody
	public IdentifierDTO<Long> create(@RequestBody ErrorDTO error, HttpServletResponse response) {
		StringBuffer globalError = new StringBuffer();
		if (error != null) {
			globalError.append(error.getMessage());
			if (error.getStacktrace() != null) {
				for (String line : error.getStacktrace()) {
					globalError.append("\n\t");
					globalError.append(line);
				}
			}
			logger.error(globalError.toString());
		}
		response.setStatus(HttpServletResponse.SC_CREATED);

		return null;
	}
}
