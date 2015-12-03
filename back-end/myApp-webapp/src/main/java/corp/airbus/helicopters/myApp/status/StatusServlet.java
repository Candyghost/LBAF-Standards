package corp.airbus.helicopters.myApp.status;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

import corp.airbus.helicopters.efactory.commons.core.config.configuration.ReloadablePlaceholderConfig;
import corp.airbus.helicopters.myApp.dao.BookDao;

/**
 * The Class StatusServlet.
 */
public class StatusServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5710376737401935025L;

	/** The Constant CONTENT_TYPE. */
	private static final String CONTENT_TYPE = "text/html;charset=utf-8";

	/** The TE status dao. */
	private BookDao daoDataSource;

	/** The error initialization. */
	private String errorInitialization;

	private String appVersion = "[DevMode]";

	@Autowired
	private ReloadablePlaceholderConfig propertyConfigurator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(final ServletConfig config) throws ServletException {
		try {

			final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX + "springMVCServlet");

			if (springContext != null) {
				daoDataSource = (BookDao) springContext.getBean("bookDao");

				springContext.getAutowireCapableBeanFactory().autowireBean(this);

				String version = propertyConfigurator.getProperties().getProperty("application.version");
				if (version != null && !version.equals("${project.version}")) {
					appVersion = version;
				}
			} else {
				errorInitialization = "Initialization failed";
			}
		} catch (final BeansException e) {
			errorInitialization = e.getMessage();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		// header
		String header = "<html>\r<head>\r<title>myApp - Status page</title>\r";
		header += "<style type=\"text/css\">";
		header += "body {font-family: Arial,sans-serif;font-size: 12px;text-align: center;}";
		header += ".panel {border-color: #dcdcdc;border-width: 1px;padding:10px;border-radius: 7px;border-style: solid;margin: 20 auto;width: 400px;background-image: linear-gradient(#e6e6e6,#fafafa);background-image: -webkit-gradient(linear,50% 0,50% 100%,color-stop(0%,#e6e6e6),color-stop(100%,#fafafa));background-image: -webkit-linear-gradient(#e6e6e6,#fafafa);background-image: -moz-linear-gradient(#e6e6e6,#fafafa);background-image: -o-linear-gradient(#e6e6e6,#fafafa);}";
		header += "h1{font-size:18px}";
		header += ".result{font-weight:bold}";
		header += ".ok{font-size: 24px;color:#8FD600;}";
		header += ".ko{font-size: 14px;color:#FF3C14;}";
		header += "</style>";
		header += "</head><body><h1>myApp v" + appVersion + " - Status page</h1>";

		// Test context initialization
		final StringBuilder contextBuilder = new StringBuilder();
		contextBuilder.append("<p class=\"panel\">Context initialization: ");
		if (errorInitialization != null && !errorInitialization.isEmpty()) {
			contextBuilder.append("<span class=\"result ko\"> ERROR</span>");
			contextBuilder.append("<br /> <br /><span class=\"result\">Error message:</span><br />");
			contextBuilder.append(errorInitialization);
			contextBuilder.append("<br /></p>");
		} else {
			contextBuilder.append("<span class=\"result ok\"> &#x2713;</span></p>");
		}

		// Test database GCH
		final StringBuilder statusGCHBuilder = new StringBuilder();
		final boolean isStatusDataSourceOk = testDatasource(statusGCHBuilder);

		final String footer = "</body>\r</html>";

		// Building result
		final StringBuilder resultBuilder = new StringBuilder();
		resultBuilder.append(header);
		resultBuilder.append(contextBuilder.toString());
		resultBuilder.append(statusGCHBuilder.toString());
		resultBuilder.append(footer);

		// format response
		response.setContentType(CONTENT_TYPE);
		response.getWriter().write(resultBuilder.toString());

		if (!isStatusDataSourceOk) {
			// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			// result);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Test the database GCH.
	 * 
	 * @param statusGCHBuilder
	 *            the text of status
	 * @return true if the test is ok;otherwise false.
	 */
	private boolean testDatasource(final StringBuilder statusGCHBuilder) {
		boolean isStatusGCHOk = false;
		statusGCHBuilder.append("<p class=\"panel\">Database connexion test: ");
		try {
			if (daoDataSource == null) {
				throw new RuntimeException("Initialization failed");
			}
			daoDataSource.get(1L);

			isStatusGCHOk = true;
			statusGCHBuilder.append("<span class=\"result ok\"> &#x2713;</span></p>");
		} catch (final RuntimeException e2) {
			statusGCHBuilder.append("<span class=\"result ko\"> ERROR</span>");
			statusGCHBuilder.append("<br /> <br /><span class=\"result\">Error message:</span><br />");
			statusGCHBuilder.append(e2.getMessage());
			statusGCHBuilder.append("<br /></p>");
		}
		return isStatusGCHOk;
	}
}
