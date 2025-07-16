package com.geom.ems.service.impl;

import com.geom.ems.dto.DepartmentDto;
import com.geom.ems.entity.Department;
import com.geom.ems.entity.Employee;
import com.geom.ems.mapper.DepartmentMapper;
import com.geom.ems.repository.DepartmentRepo;
import com.geom.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
