package com.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cursomc.repositories.ClienteRepository;
import com.cursomc.resources.exception.FieldMessage;

public class EmailInsertValidator implements ConstraintValidator<ExistsEmail, String> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ExistsEmail ann) {

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (repo.existsByEmail(email)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
