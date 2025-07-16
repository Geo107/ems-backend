package com.geom.ems.mapper;

import com.geom.ems.dto.EmployeeDto;
import com.geom.ems.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto maptToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
