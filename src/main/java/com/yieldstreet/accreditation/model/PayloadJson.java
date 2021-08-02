package com.yieldstreet.accreditation.model;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

/**
 * Object used to map json object received
 * @author Milani
 *
 */
public class PayloadJson {

	@JsonProperty("accreditation_type")
	@NotBlank	
	private String accreditationType;
	
	@JsonProperty("documents")
	@NotNull	
	private Set<DocumentJson> docs;

	
	public String getAccreditationType() {
		return accreditationType;
	}

	public void setAccreditationType(String accreditationType) {
		this.accreditationType = accreditationType;
	}

	public Set<DocumentJson> getDocs() {
		return docs;
	}

	public void setDocs(Set<DocumentJson> docs) {
		this.docs = docs;
	}

}
