package com.codespace.exchange.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codespace.exchange.service.ConversionServiceImpl;

/**
 * @author ugureli
 *
 */
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ConversionServiceImpl.class);

	private static final int INTERNAL_ERROR = 30001;
	private static final int RESOURCE_NOT_FOUND = 30002;
	private static final int BAD_REQUEST = 30003;

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ExceptionDetails> handleDefaultException(Exception ex) {
		ExceptionDetails ed = new ExceptionDetails(new Date(), INTERNAL_ERROR, ex.getMessage());
		logger.error("", ex);
		return new ResponseEntity<ExceptionDetails>(ed, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ExceptionDetails> handleNotFoundException(ResourceNotFoundException ex) {
		ExceptionDetails ed = new ExceptionDetails(new Date(), RESOURCE_NOT_FOUND, ex.getMessage());
		return new ResponseEntity<ExceptionDetails>(ed, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
		ExceptionDetails ed = new ExceptionDetails(new Date(), BAD_REQUEST, errors.isEmpty() ? "" : errors.get(0));
		return new ResponseEntity<Object>(ed, HttpStatus.BAD_REQUEST);
	}

}