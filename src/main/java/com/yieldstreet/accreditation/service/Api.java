package com.yieldstreet.accreditation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.yieldstreet.accreditation.domain.Accreditation;
import com.yieldstreet.accreditation.model.EInvalidMethod;
import com.yieldstreet.accreditation.model.AccreditationJson;
import com.yieldstreet.accreditation.model.Validate;
import com.yieldstreet.accreditation.repository.Process;  

/**
 * Class that will process the requisitions made to the service
 * @author Milani
 *
 */
@RestController  //it will control all the request
@RequestMapping("user/accreditation")   
public class Api {
	
	
	//class that persists the information and validate it
    @Autowired
	private Process repository ;
	
    /**
     * method that responds to a post call 
     * @param accreditationJson 
     * @return
     */
	@PostMapping
	public ResponseEntity<Object> addAccreditation(@RequestBody AccreditationJson accreditationJson) {
		Return returnedJson = new Return();
	
		try {
		
			//check if the json format is valid
			String errorMessage = Validate.isValidJson(accreditationJson);
					
			if (!errorMessage.equals("")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnedJson.invalid(errorMessage));
			}
			//persist the information 
			else if (!repository.save(accreditationJson)) {
				//the client is not accredited
				return ResponseEntity.status(HttpStatus.OK).body(returnedJson.successful());
			} 
			//the client is accredited 
			else {
			   return ResponseEntity.status(HttpStatus.CREATED).body(returnedJson.accredited());
			}

		} catch (EInvalidMethod e) {
			//invalid method used
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnedJson.invalid(e.getMessage()));
	    }catch (Exception e) {
			//return http status in case of a non specified error
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnedJson.invalid(e.getMessage()));
		} 
		
	}

	/**
	 * Retrieve all the accreditations
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Object> getAccreditation() {
		List<Accreditation> accreditationList = repository.findAll();
		if (accreditationList.size()!=0)  {
			return ResponseEntity.status(HttpStatus.OK).body( new Gson().toJson(accreditationList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
		}
		
		
	}

	/**
	 * Get an specific accreditation
	 * @param userId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getAccreditationById(@PathVariable("id") String userId) {
		
		Accreditation accreditation = repository.findById(userId);
		if (accreditation != null)  {
			return ResponseEntity.status(HttpStatus.OK).body( new Gson().toJson(accreditation));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
		}
		
	}


	
}
