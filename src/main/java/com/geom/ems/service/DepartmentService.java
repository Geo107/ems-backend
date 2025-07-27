package com.geom.ems.service;

import com.geom.ems.dto.DepartmentDto;
import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long departmentId);
    void deleteDepartment(Long departmentId);
    List<DepartmentDto> getAllDepartment();
    DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto);
}
