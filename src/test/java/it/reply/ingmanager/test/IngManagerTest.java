package it.reply.ingmanager.test;

import com.google.gson.Gson;

import it.reply.client.util.Utils;
import it.reply.ingmanager.client.api.IngManagerClient;
import it.reply.ingmanager.external.client.Account;
import it.reply.ingmanager.external.client.IngClient;
import it.reply.ingmanager.external.client.IngLoginResponse;
import it.reply.ingmanager.server.model.AccountsResponse;
import it.reply.ingmanager.server.model.TransactionsResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class IngManagerTest {
    public static final String URL_ING_MANAGER = "http://127.0.0.1:20580/ing/v1.0.0";

    private IngManagerClient ingManagerClient;
  

    @org.junit.Before
    public void setUp() throws Exception {
    	ingManagerClient = new IngManagerClient(URL_ING_MANAGER);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }  

    @org.junit.Test
    public void getAccounts() {
    	
        try {
            System.out.println("--------- GET ACCOUNTS---------");
            AccountsResponse ingAccountsResponse = ingManagerClient.accounts();
          
            System.out.println("REQUEST : ingClient=" + ingManagerClient.toString());

            assertEquals(200, ingAccountsResponse.getCode().intValue());
            assertEquals("OK", ingAccountsResponse.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @org.junit.Test
    public void getTransactions() {
    	
        try {
            System.out.println("--------- GET TRANSACTIONS---------");
            TransactionsResponse ingTransactionsResponse = ingManagerClient.transactions();
          
            System.out.println("REQUEST : ingClient=" + ingManagerClient.toString());

            assertEquals(200, ingTransactionsResponse.getCode().intValue());
            assertEquals("OK", ingTransactionsResponse.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}