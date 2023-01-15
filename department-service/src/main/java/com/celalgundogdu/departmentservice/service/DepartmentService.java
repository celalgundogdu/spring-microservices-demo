package com.celalgundogdu.departmentservice.service;

import com.celalgundogdu.departmentservice.dto.DepartmentDto;
import com.celalgundogdu.departmentservice.entity.Department;
import com.celalgundogdu.departmentservice.exception.DepartmentNotFoundException;
import com.celalgundogdu.departmentservice.mapper.DepartmentMapper;
import com.celalgundogdu.departmentservice.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.MAPPER.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);
    }

    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new DepartmentNotFoundException("Department", "departmentCode", departmentCode));
        return DepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }
}
