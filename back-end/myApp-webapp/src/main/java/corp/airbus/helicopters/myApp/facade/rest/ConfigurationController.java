package corp.airbus.helicopters.myApp.facade.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import corp.airbus.helicopters.efactory.commons.core.config.configuration.ReloadablePlaceholderConfig;

/**
 * The Class ConfigurationController.
 */
@Controller
public class ConfigurationController {

	/** The configuration file. */
	@Autowired
	private ReloadablePlaceholderConfig configurationFile;

	/**
	 * Gets the.
	 *
	 * @return the properties
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@ResponseBody
	public Properties get() throws IOException {
		Properties prop = new Properties();
		Resource resource = getConfigFrontResouce();
		if (resource != null) {
			prop.load(resource.getInputStream());
		}
		return prop;
	}

	/**
	 * Gets the config front resouce.
	 *
	 * @return the config front resouce
	 */
	private Resource getConfigFrontResouce() {
		List<Resource> resources = new ArrayList<Resource>(configurationFile.getResources());
		Collections.reverse(resources);
		for (Resource resource : resources) {
			if (resource.getFilename().equals("config-front.properties")) {
				return resource;
			}
		}

		return null;
	}
}
