package it.reply.ingmanager.external.client;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import it.reply.client.util.*;

/**
 *
 */
public class IngClient extends ClientUtil {

	private String basePath;

	public IngClient(String basePath) {
		this.basePath = basePath;
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public IngLoginResponse login() throws Exception {
		String uri = basePath + "/login";
		ClientResponse response = sendLoginRequest(uri, "POST", null);
		if (response.getCode() == 200) {
			return new Gson().fromJson(response.getResponse(), IngLoginResponse.class);
		}
		else if (response.getCode() == 400 || response.getCode() == 500)
			return new Gson().fromJson(response.getResponse(), IngLoginResponse.class);
		else
			throw new Exception("bad response code: " + response.getCode());
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Account> getAccounts(String token) throws Exception {
		String uri = basePath + "/accounts";
		ClientResponse response = sendTokenRequest(uri, "GET", null,token);
		if (response.getCode() == 200) {
			Type collectionType = new TypeToken<List<Account>>(){}.getType();
			List<Account> lcs = (List<Account>) new Gson()
			               .fromJson( response.getResponse() , collectionType);
			
			return lcs;
		}
		else if (response.getCode() == 400 || response.getCode() == 500)
			return null;// new Gson().fromJson(response.getResponse(), IngAccountsResponse.class);
		else
			throw new Exception("bad response code: " + response.getCode());
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Transaction> getTransactions(String token) throws Exception {
		String uri = basePath + "/transactions";
		ClientResponse response = sendTokenRequest(uri, "GET", null,token);
		if (response.getCode() == 200) {
			//return "test";
			Type collectionType = new TypeToken<List<Transaction>>(){}.getType();
			List<Transaction> lcs = (List<Transaction>) new Gson()
			               .fromJson(response.getResponse() , collectionType);
			return lcs;
		}
		else if (response.getCode() == 400 || response.getCode() == 500)
			return null;// new Gson().fromJson(response.getResponse(), IngAccountsResponse.class);
		else
			throw new Exception("bad response code: " + response.getCode());
	}
}
