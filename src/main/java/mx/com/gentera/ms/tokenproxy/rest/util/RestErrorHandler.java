package mx.com.gentera.ms.tokenproxy.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mx.com.gentera.ms.tokenproxy.exception.SecurityAccesException;

@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

	@ExceptionHandler(SecurityAccesException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<SecurityAccesException> handleSecurityAccesException(SecurityAccesException exception) {
		logger.error("handleSecurityAccesException(SecurityAccesException = {})", exception);
		exception.setStackTrace(new StackTraceElement[0]);
		ResponseEntity<SecurityAccesException> reponse = new ResponseEntity<SecurityAccesException>(exception, HttpStatus.UNAUTHORIZED);
		return reponse;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<Exception> handleException(Exception exception) {
		logger.error("handleException(Exception = {})", exception);
		exception.setStackTrace(new StackTraceElement[0]);
		ResponseEntity<Exception> reponse = new ResponseEntity<Exception>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		return reponse;
	}
}
