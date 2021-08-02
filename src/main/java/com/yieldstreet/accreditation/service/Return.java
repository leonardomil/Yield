package com.yieldstreet.accreditation.service;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;


/**
 * Class with the Json structure to respond the accreditation call
 * <p>
 * Inform the status of success, accreditation and also the problems that occured
 * @author Milani
 *
 */
public class Return {
	
	@Expose
	protected boolean success;
	@Expose
    protected boolean accredited;
	
	protected String error;
	
	protected String accredited() {
		success= true;
		accredited = true;
		return new Gson().toJson(this);
	}
		
	protected String successful() {
		success= true;
		accredited = false;
		return new Gson().toJson(this);
	}
	
	protected String invalid(String message) {
		success= false;
		accredited = false;
		error = message;
		return new Gson().toJson(this);
	}

}
