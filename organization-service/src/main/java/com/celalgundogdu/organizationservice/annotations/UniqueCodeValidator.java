package com.celalgundogdu.organizationservice.annotations;

import com.celalgundogdu.organizationservice.repository.OrganizationRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCodeValidator implements ConstraintValidator<UniqueCode, String> {

    private final OrganizationRepository organizationRepository;

    public UniqueCodeValidator(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean existsByOrganizationCode = organizationRepository.existsByOrganizationCode(value);
        return !existsByOrganizationCode;
    }
}
