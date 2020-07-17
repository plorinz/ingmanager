package it.reply.ingmanager.server;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import it.reply.ingmanager.server.model.Response;

public abstract class BasicController {

	protected NativeWebRequest request;

	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	public static ResponseEntity<Response> responseOK() {
		Response response = new Response();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
