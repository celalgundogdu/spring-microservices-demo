package com.celalgundogdu.employeeservice.service;

import com.celalgundogdu.employeeservice.dto.APIResponseDto;
import com.celalgundogdu.employeeservice.dto.DepartmentDto;
import com.celalgundogdu.employeeservice.dto.EmployeeDto;
import com.celalgundogdu.employeeservice.dto.OrganizationDto;
import com.celalgundogdu.employeeservice.entity.Employee;
import com.celalgundogdu.employeeservice.exception.EmployeeNotFoundException;
import com.celalgundogdu.employeeservice.mapper.EmployeeMapper;
import com.celalgundogdu.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentFeignClient departmentFeignClient;
    private final OrganizationFeignClient organizationFeignClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentFeignClient departmentFeignClient,
                           OrganizationFeignClient organizationFeignClient) {
        this.employeeRepository = employeeRepository;
        this.departmentFeignClient = departmentFeignClient;
        this.organizationFeignClient = organizationFeignClient;
    }

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    public APIResponseDto getEmployeeById(Long employeeId) {
        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("employee", "id", employeeId));

        DepartmentDto departmentDto = departmentFeignClient.getDepartment(employee.getDepartmentCode());
        OrganizationDto organizationDto = organizationFeignClient.getOrganizationByCode(employee.getOrganizationCode());
        EmployeeDto employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto, organizationDto);

        return apiResponseDto;
    }
}
