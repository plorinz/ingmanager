package it.reply.ingmanager.external.client;

import com.google.gson.annotations.SerializedName;

public class Account {
	
	 
	 public static final String SERIALIZED_NAME_ID = "id";
	 @SerializedName(SERIALIZED_NAME_ID)
	 private String id;
	 
	 public static final String SERIALIZED_NAME_UPDATE = "update";
	 @SerializedName(SERIALIZED_NAME_UPDATE)
	 private String update;
	 
	 public static final String SERIALIZED_NAME_NAME = "name";
	 @SerializedName(SERIALIZED_NAME_NAME)
	 private String name;
	  
	 public static final String SERIALIZED_NAME_PRODUCT = "product";
	 @SerializedName(SERIALIZED_NAME_PRODUCT)
	 private String product; 
	
	 public static final String SERIALIZED_NAME_STATUS = "status";
	 @SerializedName(SERIALIZED_NAME_STATUS)
	 private String status;
	 
	 public static final String SERIALIZED_NAME_TYPE = "type";
	 @SerializedName(SERIALIZED_NAME_TYPE)
	 private String type;
	 
	 public static final String SERIALIZED_NAME_BALANCE = "balance";
	 @SerializedName(SERIALIZED_NAME_BALANCE)
	 private String balance;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	 
	
}
