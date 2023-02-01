package com.celalgundogdu.employeeservice.service;

import com.celalgundogdu.employeeservice.dto.OrganizationDto;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationFeignClient {

    Logger logger = LoggerFactory.getLogger(OrganizationFeignClient.class);

    @GetMapping("api/organizations/{organizationCode}")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultOrganization")
    OrganizationDto getOrganizationByCode(@PathVariable("organizationCode") String organizationCode);

    default OrganizationDto getDefaultOrganization(String organizationCode, Exception exception) {
        logger.info("inside getDefaultOrganization() method");
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName("default-organization");
        organizationDto.setOrganizationCode("default");
        return organizationDto;
    }
}
