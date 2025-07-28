package com.geom.ems.service.impl;

import com.geom.ems.dto.EmployeeDto;
import com.geom.ems.entity.Department;
import com.geom.ems.entity.Employee;
import com.geom.ems.exception.ResourceNotFoundException;
import com.geom.ems.mapper.EmployeeMapper;
import com.geom.ems.repository.DepartmentRepo;
import com.geom.ems.repository.EmployeeRepo;
import com.geom.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepo employeeRepo;
    private DepartmentRepo departmentRepo;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Department department = departmentRepo.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Dept_id NOT FOUND" + employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepo.save(employee);
        return EmployeeMapper.maptToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeId(Long employeeID) {
        Employee employee = employeeRepo.findById(employeeID)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with Id not found" + employeeID));
        return EmployeeMapper.maptToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.maptToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee Id not found!" + employeeId)
        );

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Department department = departmentRepo.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Dept_id NOT FOUND" + updatedEmployee.getDepartmentId()));
        employee.setDepartment(department);

        Employee updatedEmployeeObj = employeeRepo.save(employee);

        return EmployeeMapper.maptToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Id Not Found" + employeeId)
        );
        employeeRepo.deleteById(employeeId);
    }
}
