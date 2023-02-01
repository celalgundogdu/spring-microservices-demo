package com.celalgundogdu.organizationservice.service;

import com.celalgundogdu.organizationservice.dto.OrganizationDto;
import com.celalgundogdu.organizationservice.entity.Organization;
import com.celalgundogdu.organizationservice.mapper.OrganizationMapper;
import com.celalgundogdu.organizationservice.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Optional<Organization> optionalOrganization = organizationRepository.findByOrganizationCode(organizationCode);
        if (optionalOrganization.isEmpty()) {
            //throw new Exception("");
        }
        return OrganizationMapper.mapToOrganizationDto(optionalOrganization.get());
    }
}
