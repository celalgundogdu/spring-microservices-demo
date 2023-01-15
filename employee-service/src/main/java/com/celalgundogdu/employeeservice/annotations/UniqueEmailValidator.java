package com.celalgundogdu.employeeservice.annotations;

import com.celalgundogdu.employeeservice.repository.EmployeeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final EmployeeRepository employeeRepository;

    public UniqueEmailValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean existsByEmail = employeeRepository.existsByEmail(value);
        if (existsByEmail) {
            return false;
        }
        return true;
    }
}
