package com.celalgundogdu.employeeservice.service;

import com.celalgundogdu.employeeservice.dto.APIResponseDto;
import com.celalgundogdu.employeeservice.dto.DepartmentDto;
import com.celalgundogdu.employeeservice.dto.EmployeeDto;
import com.celalgundogdu.employeeservice.entity.Employee;
import com.celalgundogdu.employeeservice.exception.EmployeeNotFoundException;
import com.celalgundogdu.employeeservice.mapper.EmployeeMapper;
import com.celalgundogdu.employeeservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final APIClient apiClient;

    public EmployeeService(EmployeeRepository employeeRepository, APIClient apiClient) {
        this.employeeRepository = employeeRepository;
        this.apiClient = apiClient;
    }

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("employee", "id", employeeId));

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDto employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto);

        return apiResponseDto;
    }
}
