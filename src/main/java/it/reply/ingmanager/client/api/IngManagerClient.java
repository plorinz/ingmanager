package it.reply.ingmanager.client.api;

import com.google.gson.*;
import it.reply.client.util.*;
import it.reply.ingmanager.server.model.AccountsResponse;
import it.reply.ingmanager.server.model.TransactionsResponse;

/**
 *
 */
public class IngManagerClient extends ClientUtil {

	private String basePath;

	public IngManagerClient(String basePath) {
		this.basePath = basePath;
	}


	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public AccountsResponse accounts() throws Exception {
		String uri = basePath +"/accounts";
		ClientResponse response = sendRequest(uri, "GET", null);
		if (response.getCode() == 200) {
			System.out.println("response=" + response.getResponse());
			return new Gson().fromJson(response.getResponse(), AccountsResponse.class);
		}
		else if (response.getCode() == 400 || response.getCode() == 500)
			return new Gson().fromJson(response.getResponse(), AccountsResponse.class);
		else
			throw new Exception("bad response code: " + response.getCode());
	}
	
	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public TransactionsResponse transactions() throws Exception {
		String uri = basePath +"/transactions";
		ClientResponse response = sendRequest(uri, "GET", null);
		if (response.getCode() == 200) {
			System.out.println("response=" + response.getResponse());
			return new Gson().fromJson(response.getResponse(), TransactionsResponse.class);
		}
		else if (response.getCode() == 400 || response.getCode() == 500)
			return new Gson().fromJson(response.getResponse(), TransactionsResponse.class);
		else
			throw new Exception("bad response code: " + response.getCode());
	}
}
