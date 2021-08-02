package com.yieldstreet.accreditation.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean of Accreditation 
 * @author Milani
 *
 */

@Entity
public class Accreditation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@JsonProperty("user_id")
	private String userId;
	
	@OneToMany(
	        cascade = CascadeType.ALL,	        
	        orphanRemoval = true
	    )
	@JsonProperty("documents")
	private List<Document> documents;
	
	private String accreditationType;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	public String getAccreditationType() {
		return accreditationType;
	}
	public void setAccreditationType(String accreditationType) {
		this.accreditationType = accreditationType;
	}
	

}
