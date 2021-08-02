package com.yieldstreet.accreditation.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yieldstreet.accreditation.domain.Accreditation;
import com.yieldstreet.accreditation.domain.Document;
import com.yieldstreet.accreditation.model.EInvalidMethod;
import com.yieldstreet.accreditation.model.AccreditationJson;
import com.yieldstreet.accreditation.model.DocumentJson;

/**
 * Resposible for persistence 
 * @author Milani
 *
 */
@Service
public class Process {
	

	@Autowired
	private AccreditationRepo accreditiationRepo;
		
	/**
	 * persist the info of Accreditation
	 * @param accreditationJson
	 * @return true if it was successful
	 * @throws EInvalidMethod 
	 */
	public boolean save(AccreditationJson accreditationJson) throws EInvalidMethod {
		
		// random number to give a pseudo accreditation status
		int rand = (int)(100*Math.random());
		
        if (rand % 2== 0) {  
		
        	Accreditation accreditationEntity = new Accreditation();
			
        	accreditationEntity.setUserId(accreditationJson.getUserId());
			accreditationEntity.setAccreditationType(accreditationJson.getPay().getAccreditationType());
			
			//check if already exists the accreditation
			if (accreditiationRepo.findByUserId(accreditationJson.getUserId())!=null) {
				throw new EInvalidMethod("The right method to use is PUT");
			}
			
			List<Document> documentList = new ArrayList<Document>();
			
			//load the documents
			for (DocumentJson d0 : accreditationJson.getPay().getDocs()) {

				Document tempDocument = new Document();
				tempDocument.setMimeType(d0.getMimeType());
				tempDocument.setContent(d0.getContent());
				tempDocument.setName(d0.getName());

				documentList.add(tempDocument);
			}

			accreditationEntity.setDocuments(documentList);

			accreditiationRepo.save(accreditationEntity);
			
			return true; 
			
        } else {
        	return false;
        }
	}
	
	public List<Accreditation> findAll() {
		return accreditiationRepo.findAll();
	}
	
	public Accreditation findById(String userId) {
		return accreditiationRepo.findByUserId(userId);
	}


}
