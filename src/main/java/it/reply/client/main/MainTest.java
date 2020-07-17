package it.reply.client.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;

import it.reply.client.util.Utils;
import it.reply.ingmanager.external.client.Account;
import it.reply.ingmanager.external.client.IngAccountsResponse;
import it.reply.ingmanager.external.client.IngClient;
import it.reply.ingmanager.external.client.IngLoginResponse;
import it.reply.ingmanager.server.model.AccountRecord;

public class MainTest {
	
	public static void main(String[] args) {
		IngClient ingClient = new IngClient("http://127.0.0.1:8080");
		String token="";
		
		try {
			System.out.println("--------- LOGIN ---------");
			IngLoginResponse ingResponse = ingClient.login();
			System.out.println("REQUEST : ingClient=" + ingClient.toString());
			System.out.println("RESPONSE : " + ingResponse.getToken());
			token=ingResponse.getToken();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("--------- ACCOUNTS ---------");
			List<Account> ingResponse = ingClient.getAccounts(token);
			System.out.println("REQUEST : ingClient=" + ingClient.toString());
			System.out.println("RESPONSE PRODUCT: " + ingResponse.get(0).getProduct());
			System.out.println("RESPONSE TYPE: " + ingResponse.get(0).getType());
		}
		catch (Exception e) {
			e.printStackTrace();
		}


	}
	
}
