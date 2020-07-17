package it.reply.ingmanager.server.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.reply.ingmanager.server.model.Response;;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(InternalException.class)
	public ResponseEntity<Response> handleGWException(InternalException e) {
		Response error = new Response();
		error.setCode(e.getStatus().value());
		error.setMessage(e.getStatus().getReasonPhrase());
		error.setDetails(e.getDetails());

		return new ResponseEntity<Response>(error, e.getStatus());
	}

	@ExceptionHandler({ MissingServletRequestParameterException.class, JsonProcessingException.class, ConversionFailedException.class,
			ConstraintViolationException.class, MethodArgumentNotValidException.class })
	public ResponseEntity<Response> handleMissingParameters(Exception e) {
		Response error = new Response();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.putDetailsItem("description", e.getMessage());

		return new ResponseEntity<Response>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ RestClientException.class, HttpClientErrorException.class })
	public ResponseEntity<Response> handleException(Exception e) {
		Response error = new Response();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		error.putDetailsItem("description", e.getMessage());

		return new ResponseEntity<Response>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
