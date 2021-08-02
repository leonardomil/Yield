package com.yieldstreet.accreditation.model;

/**
 * exception to inform that the wrong method was called to execute an insert in the data base
 * @author Milani
 *
 */
public class EInvalidMethod extends Exception{

	public EInvalidMethod(String message) {
		super(message);
	}
	

}
