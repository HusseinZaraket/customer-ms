package com.areeba.challenge.customer.service;

import java.text.MessageFormat;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.areeba.challenge.customer.exception.InvalidMobileException;

/**
 * Mobile number service(in service layer) that will validate the passed mobile number and return all needed details in case it's valid
 *  
 * @author Hussein Zaraket
 */
@Service
public class MobileNumberService {

	// value of mobilevalidator url is read from application.properties file
	@Value("${mobileValidator.url}")
	private String mobileValidatorUrl;

	/**
	 * Validate a specific mobile number, and return it's details in case is valid, else error message. 
	 * @param mobile
	 * @throws InvalidMobileException
	 */
	@SuppressWarnings("unchecked")
	public void validateMobileNumber(String mobile) throws InvalidMobileException {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, Object> result = restTemplate.getForObject(MessageFormat.format(mobileValidatorUrl, mobile),
				HashMap.class);
		if (!(boolean) result.get("valid")) {
			throw new InvalidMobileException(mobile);
		}
	}

}
