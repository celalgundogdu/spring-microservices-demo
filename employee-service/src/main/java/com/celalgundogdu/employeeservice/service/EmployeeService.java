package com.celalgundogdu.employeeservice.service;

import com.celalgundogdu.employeeservice.dto.EmployeeDto;
import com.celalgundogdu.employeeservice.entity.Employee;
import com.celalgundogdu.employeeservice.exception.EmployeeNotFoundException;
import com.celalgundogdu.employeeservice.mapper.EmployeeMapper;
import com.celalgundogdu.employeeservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("employee", "id", employeeId));
        return EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
    }
}
