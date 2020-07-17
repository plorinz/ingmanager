package it.reply.ingmanager.repository.model;

import java.util.List;

import it.reply.ingmanager.server.model.TransactionRecord;
import it.reply.ingmanager.server.model.TransactionsResponse;

public class TransactionsResponseWrapper {

	private Integer out;
	private TransactionsResponse response;

	public TransactionsResponseWrapper(Integer out, List<TransactionRecord> transactionsList) {
		super();
		this.out = out;
		this.response = new TransactionsResponse();
		this.response.transactionsList(transactionsList);
	}

	public Integer getOut() {
		return out;
	}

	public TransactionsResponse getResponse() {
		return response;
	}

}
