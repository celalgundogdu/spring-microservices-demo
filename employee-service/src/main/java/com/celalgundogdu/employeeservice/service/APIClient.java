package com.celalgundogdu.employeeservice.service;

import com.celalgundogdu.employeeservice.dto.DepartmentDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    Logger logger = LoggerFactory.getLogger(APIClient.class);

    @GetMapping("api/departments/{department-code}")
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);

    default DepartmentDto getDefaultDepartment(String departmentCode, Exception exception){
        logger.info("Department not found by department code: " + departmentCode);
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");
        return departmentDto;
    }
}
