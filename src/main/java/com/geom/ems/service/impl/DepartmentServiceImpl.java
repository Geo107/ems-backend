package com.geom.ems.service.impl;

import com.geom.ems.dto.DepartmentDto;
import com.geom.ems.entity.Department;
import com.geom.ems.exception.ResourceNotFoundException;
import com.geom.ems.mapper.DepartmentMapper;
import com.geom.ems.repository.DepartmentRepo;
import com.geom.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepo departmentRepo;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto){
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department saveDepartment = departmentRepo.save(department);
        return  DepartmentMapper.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Id Not Found"+departmentId));
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
         Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Id Not Found"+departmentId));
         departmentRepo.delete(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> departments = departmentRepo.findAll();
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found" + departmentId));
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());

        Department updatedDepartment = departmentRepo.save(department);
        return DepartmentMapper.mapToDepartmentDto(updatedDepartment);
    }
}
