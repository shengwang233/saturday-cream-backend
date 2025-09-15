package com.saturday.service;

import com.saturday.dto.EmployeeDTO;
import com.saturday.dto.EmployeeLoginDTO;
import com.saturday.entity.Employee;

public interface EmployeeService {
    /**
     * login
     *
     * @param employeeLoginDTO
     * @return login result
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * add a new employee in system
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);
}
