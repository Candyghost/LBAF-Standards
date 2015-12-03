/**
 * 
 */
package corp.airbus.helicopters.myApp.facade.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import corp.airbus.helicopters.efactory.commons.core.exception.FunctionalException;
import corp.airbus.helicopters.efactory.commons.core.exception.UnauthorizedAccess;

/**
 * Handler for exception which are thrown in REST web services.
 * 
 * @author stcortine
 * 
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

	/**
	 * Handle technical exception.
	 * 
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = { RuntimeException.class, Exception.class })
	public ResponseEntity<Object> handleTechnicalException(Exception ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/plain");
		return handleExceptionInternal(ex, "A technical problem is occured.", headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	/**
	 * Handle functionnal exception.
	 * 
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = { FunctionalException.class })
	public ResponseEntity<Object> handleFunctionnalException(FunctionalException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		ObjectMapper mapper = new ObjectMapper();

		try {
			return handleExceptionInternal(ex, mapper.writeValueAsString(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		} catch (JsonGenerationException e) {
			return handleExceptionInternal(ex, "", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		} catch (JsonMappingException e) {
			return handleExceptionInternal(ex, "", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		} catch (IOException e) {
			return handleExceptionInternal(ex, "", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		}
	}

	/**
	 * Handle access exception.
	 * 
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = { AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessException(Exception ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/plain");
		return handleExceptionInternal(ex, "The connected user has not the needed right to access this resource.", headers, HttpStatus.FORBIDDEN, request);
	}

	/**
	 * Handle unauthorized access exception.
	 * 
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = { UnauthorizedAccess.class })
	public ResponseEntity<Object> handleUnauthorizedAccessException(Exception ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/plain");
		return handleExceptionInternal(ex, "The connected user could not access to the requested resources.", headers, HttpStatus.FORBIDDEN, request);
	}

}
