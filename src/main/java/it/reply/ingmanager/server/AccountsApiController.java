package it.reply.ingmanager.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import it.reply.ingmanager.external.client.Account;
import it.reply.ingmanager.external.client.IngClient;
import it.reply.ingmanager.external.client.IngLoginResponse;
import it.reply.ingmanager.repository.model.AccountResponseWrapper;
import it.reply.ingmanager.server.exception.InternalException;
import it.reply.ingmanager.server.api.AccountsApi;
import it.reply.ingmanager.server.model.AccountRecord;
import it.reply.ingmanager.server.model.AccountsResponse;
import it.reply.ingmanager.server.model.Response;

@Controller
@RequestMapping("/ing/v1.0.0")
public class AccountsApiController extends BasicController implements AccountsApi {

	private static final Long RESET_FLAG = Long.valueOf(-1L);

	@Value("${wiremock.server}")
	private String wiremockServer;
	
	@Autowired
	public AccountsApiController(NativeWebRequest request) {
		this.request = request;
	}

	//@Autowired
	//private AccountsRepository repository;

	@Override
	public ResponseEntity<AccountsResponse> accounts() {

		//AccountResponseWrapper wrapper = repository.getAccounts();
		String token="";
		//IngClient ingClient = new IngClient("http://127.0.0.1:8080");
		IngClient ingClient = new IngClient(wiremockServer);
		try {
			IngLoginResponse ingResponse = ingClient.login();
			token=ingResponse.getToken();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Integer out = 0;//wrapper.getOut();

		switch (out) {
		case 0:
			ResponseEntity<AccountsResponse> response = getAccountsResponseOK();

			List<AccountRecord> accountList = new ArrayList<>();
			try {
				
				List<Account> ingResponse = ingClient.getAccounts(token);		
			    ingResponse.forEach(object -> {
			    			AccountRecord item= new AccountRecord();
			    			item.setId(object.getId());
			    			item.setUpdate(object.getUpdate());
			    			item.setName(object.getName());
			    			item.setProduct(object.getProduct());
			    			item.setStatus(object.getStatus());
			    			item.setTypeAccount(object.getType());
			    			item.setBalance(object.getBalance());
			    			accountList.add(item);
			    			});		
				accountList.forEach(acc -> response.getBody().addAccountsListItem(acc));
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			return response;

		case 1:
			throw new InternalException(HttpStatus.BAD_REQUEST, getDetails(out.toString()));

		default:
		case 99:
			throw new InternalException(HttpStatus.INTERNAL_SERVER_ERROR, getDetails(out.toString()));
		}
	}

	private static ResponseEntity<AccountsResponse> getAccountsResponseOK() {
		AccountsResponse response = new AccountsResponse();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<AccountsResponse>(response, HttpStatus.OK);
	}

	private static Map<String, String> getDetails(String detail) {
		Map<String, String> details = new HashMap<>();
		details.put("internal_code", detail);
		return details;
	}

}
