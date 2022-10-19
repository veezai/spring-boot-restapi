package com.example.whatsapp.response;

public class ClientDetails {

	
	public String getDisplay_phone_number() {
		return display_phone_number;
	}

	public void setDisplay_phone_number(String display_phone_number) {
		this.display_phone_number = display_phone_number;
	}

	public String getPhone_number_id() {
		return phone_number_id;
	}

	public void setPhone_number_id(String phone_number_id) {
		this.phone_number_id = phone_number_id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String display_phone_number;
	
	private String phone_number_id;
	
	private String from;
	
	private String userId;

	public ClientDetails(String display_phone_number, String phone_number_id, String from, String userId) {
		super();
		this.display_phone_number = display_phone_number;
		this.phone_number_id = phone_number_id;
		this.from = from;
		this.userId = userId;
	}
	
}
