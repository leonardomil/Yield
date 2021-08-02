package com.yieldstreet.accreditation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object used to map json object received 
 * @author Milani
 *
 */
public class AccreditationJson {
	
	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("payload")
	private PayloadJson pay;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public PayloadJson getPay() {
		return pay;
	}
	public void setPay(PayloadJson pay) {
		this.pay = pay;
	}
	
	

}
