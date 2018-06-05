package com.app.microservices2.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.app.microservices2.bean.ServiceError;
import com.app.microservices2.bean.ServiceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
    public static String printInformation(Object object) throws JsonProcessingException {

    	if(object == null) {
			return MicroServicesConstants.EMPTY;
		}
		return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
 
    public static String writeJsonSingleLine(Object object)  {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    
	public static void preparedErrorResponse(ServiceResponse serviceResponse, String message, String errorCode){
		List<ServiceError> errors = new ArrayList<>();
		ServiceError serviceError = new ServiceError(
				message,
				errorCode);
		errors.add(serviceError);
		serviceResponse.setErrors(errors);
	}

	public static void printResponse(HttpServletResponse response, String message) throws IOException{
        response.setContentType(MicroServicesConstants.CONTENT_TYPE_JSON);
        response.getOutputStream().println(message);
	}
	
}
