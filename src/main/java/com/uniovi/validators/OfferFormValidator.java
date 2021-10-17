package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Offer;

@Component
public class OfferFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(Offer.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "details", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");

		if (offer.getTitle().length() < 5 || offer.getTitle().length() > 24) {
			errors.rejectValue("title", "Error.offer.add.title");
		}
		
		if (offer.getDetails().length() < 5 || offer.getTitle().length() > 24) {
			errors.rejectValue("details", "Error.offer.add.details");
		}
		
		if (offer.getPrice() < 0.0) {
			errors.rejectValue("price", "Error.offer.add.details");
		}
		
		if (offer.getPrice() < 0.0) {
			errors.rejectValue("price", "Error.offer.add.details");
		}
	}
	
}