package it.reply.client.util;

public class ClientResponse {

	private String response;
	private int code;
	
	public ClientResponse(int code, String response) {
		this.code = code;
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public int getCode() {
		return code;
	}
	
}
