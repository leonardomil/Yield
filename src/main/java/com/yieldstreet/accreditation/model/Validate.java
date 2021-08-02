package com.yieldstreet.accreditation.model;


public class Validate {

	/**
	 * Check if all the required values were informed
	 * @param accreditationJson
	 * @return true if every parameter is informed
	 */
	public static String isValidJson(AccreditationJson accreditationJson) {
		StringBuilder issues = new StringBuilder("");
		String ret="";
		
		try {
				isFieldOk(issues, accreditationJson.getUserId(),"user_id");
				
				if (isFieldOk(issues, accreditationJson.getPay(),"payload")) {
					isFieldOk(issues, accreditationJson.getPay().getAccreditationType(),"accreditation_type");
					
					
			        if (isFieldOk(issues, accreditationJson.getPay().getDocs(),"documents")) {
						for (DocumentJson doc : accreditationJson.getPay().getDocs()) {
				        	isFieldOk(issues, doc.getName() ,"name");
				        	isFieldOk(issues, doc.getMimeType(),"mime_type");
				        	isFieldOk(issues, doc.getContent(),"content");
				        }
			        }
				}    
	        } catch (Exception e) {
	        	ret = "Invalid format";
	        } finally {
		        if (issues.length()>0) {
		        	ret = "Missing fields:" + issues.substring(0,issues.length()-1);
		        }
	        	
	        }
        
		return ret;
	}
	
	/**
	 * Check if an object is empty, null or ""
	 * @param missing String that will receive the name of empty values
	 * @param obj Object to evaluate
	 * @param fieldName Name of the object 
	 * @return false if the value is not fullfiled
	 */
	public static boolean isFieldOk(StringBuilder missing, Object obj,String fieldName) {
		
		if (obj == null || (obj instanceof String && ((String) obj).isEmpty()) || obj.toString().equals("[]") ) {
			// avoid insert more than one time the name of field to the missing values
			if (missing.indexOf(fieldName)==-1) {
				missing.append(" "+ fieldName + ",");
			}	
			return false;
		} else 
			return true;
	}

}
