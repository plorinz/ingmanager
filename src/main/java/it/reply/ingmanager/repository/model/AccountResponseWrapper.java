package it.reply.ingmanager.repository.model;

import java.util.List;

import it.reply.ingmanager.server.model.AccountRecord;
import it.reply.ingmanager.server.model.AccountsResponse;

public class AccountResponseWrapper {

	private Integer out;
	private AccountsResponse response;

	public AccountResponseWrapper(Integer out, List<AccountRecord> accountsList) {
		super();
		this.out = out;
		this.response = new AccountsResponse();
		this.response.accountsList(accountsList);
	}

	public Integer getOut() {
		return out;
	}

	public AccountsResponse getResponse() {
		return response;
	}

}
