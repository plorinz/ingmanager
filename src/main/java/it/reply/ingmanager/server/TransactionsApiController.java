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
import it.reply.ingmanager.external.client.Transaction;
import it.reply.ingmanager.external.client.TransactionExchangeRate;
import it.reply.ingmanager.external.client.TransactionOriginalAmount;
import it.reply.ingmanager.server.exception.InternalException;
import it.reply.ingmanager.server.api.TransactionsApi;
import it.reply.ingmanager.server.model.AccountRecord;
import it.reply.ingmanager.server.model.AccountsResponse;
import it.reply.ingmanager.server.model.TransactionRecord;
import it.reply.ingmanager.server.model.TransactionRecordCreditor;
import it.reply.ingmanager.server.model.TransactionRecordDebtor;
import it.reply.ingmanager.server.model.TransactionRecordExchangeRate;
import it.reply.ingmanager.server.model.TransactionRecordOriginalAmount;
import it.reply.ingmanager.server.model.TransactionsResponse;

@Controller
@RequestMapping("/ing/v1.0.0")
public class TransactionsApiController extends BasicController implements TransactionsApi {

	private static final Long RESET_FLAG = Long.valueOf(-1L);
	
	@Value("${wiremock.server}")
	private String wiremockServer;

	@Autowired
	public TransactionsApiController(NativeWebRequest request) {
		this.request = request;
	}

	//@Autowired
	//private TransactionsRepository repository;


	@Override
	public ResponseEntity<TransactionsResponse> transactions() {

		//TransactionsResponseWrapper wrapper = repository.getTransactions();
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
			ResponseEntity<TransactionsResponse> response = getTransactionsOK();

			List<TransactionRecord> transactionList = new ArrayList<>();
			try {
				
				List<Transaction> ingResponse = ingClient.getTransactions(token);		
			    ingResponse.forEach(object -> {
			    			TransactionRecord transaction= new TransactionRecord();
			    			transaction.setId(object.getId());
			    			transaction.setAccountId(object.getAccountId());
			    			TransactionRecordExchangeRate exchangeRate= new TransactionRecordExchangeRate();
			    			exchangeRate.setCurrencyFrom(object.getExchangeRate().getCurrencyFrom());
			    			exchangeRate.setCurrencyTo(object.getExchangeRate().getCurrencyTo());
			    			exchangeRate.setRate(object.getExchangeRate().getRate());
			    			transaction.setExchangeRate(exchangeRate);
			    			TransactionRecordOriginalAmount originalAmount= new TransactionRecordOriginalAmount();
			    			originalAmount.setAmount(object.getOriginalAmount().getAmount());
			    			originalAmount.setCurrency(object.getOriginalAmount().getCurrency());
			    			transaction.setOriginalAmount(originalAmount);
			    			TransactionRecordCreditor creditor= new TransactionRecordCreditor();
			    			creditor.setMaskedPan(object.getCreditor().getMaskedPan());
			    			creditor.setName(object.getCreditor().getName());
			    			transaction.setCreditor(creditor);
			    			TransactionRecordDebtor debtor= new TransactionRecordDebtor();
			    			debtor.setMaskedPan(object.getDebtor().getMaskedPan());
			    			debtor.setName(object.getDebtor().getName());
			    			transaction.setDebtor(debtor);
			    			transaction.setStatus(object.getStatus());
			    			transaction.setCurrency(object.getCurrency());
			    			transaction.setAmount(object.getAmount());
			    			transaction.setUpdate(object.getUpdate());
			    			transaction.setDescription(object.getDescription());
			    			transactionList.add(transaction);
			    			});		
			    transactionList.forEach(tr -> response.getBody().addTransactionsListItem(tr));
				
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

	private static ResponseEntity<TransactionsResponse> getTransactionsOK() {
		TransactionsResponse response = new TransactionsResponse();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<TransactionsResponse>(response, HttpStatus.OK);
	}

	private static Map<String, String> getDetails(String detail) {
		Map<String, String> details = new HashMap<>();
		details.put("internal_code", detail);
		return details;
	}

}
