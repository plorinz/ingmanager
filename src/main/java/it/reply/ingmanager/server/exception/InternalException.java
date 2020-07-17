package it.reply.ingmanager.server.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class InternalException extends RuntimeException {

	private static final long serialVersionUID = 2938891849684058954L;
	private HttpStatus status;
	private Map<String, String> details;

	public InternalException(HttpStatus status, Map<String, String> details) {
		super(status.getReasonPhrase());
		this.status = status;
		this.details = details;
	}

	public InternalException(HttpStatus status, String detail) {
		super(status.getReasonPhrase());
		this.status = status;
		Map<String, String> detailsMap = new HashMap<>(1);
		detailsMap.put("description", detail);
		this.details = detailsMap;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "InternalException [status=" + status + ", details=" + details + "]";
	}

}
