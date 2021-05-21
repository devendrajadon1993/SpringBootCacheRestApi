package com.employee.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex) {

		LOGGER.info("Exception in resourceNotFoundException method {}", ex.getMessage());
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errorList("", ex.getMessage()));
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
		LOGGER.info("Exception in globalExceptionHandler method {}", ex.getMessage());
		ex.printStackTrace();
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				errorList("", "Some error occurred on server, please try later."));
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceAlreadyExitsException.class)
	public ResponseEntity<ErrorMessage> resourceAlreadyExitsException(ResourceAlreadyExitsException ex) {
		LOGGER.info("Exception in resourceAlreadyExitsException method {}", ex.getMessage());
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errorList("", ex.getMessage()));
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		LOGGER.info("Exception in methodArgumentNotValidException method {}", ex.getMessage());
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		List<FieldMessage> errors = new ArrayList<>();
		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			FieldMessage fielderror = new FieldMessage();
			fielderror.setKey(fieldError.getField());
			fielderror.setValue(fieldError.getDefaultMessage());
			errors.add(fielderror);
		}
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errors);
	}

	public List<FieldMessage> errorList(String key, String msg) {

		FieldMessage message = new FieldMessage();
		List<FieldMessage> fielderrors = new ArrayList<>();
		message.setKey(key);
		message.setValue(msg);
		fielderrors.add(message);
		return fielderrors;

	}

}
