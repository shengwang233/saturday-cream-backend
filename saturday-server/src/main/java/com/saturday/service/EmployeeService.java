package com.saturday.service;

import com.saturday.dto.EmployeeDTO;
import com.saturday.dto.EmployeeLoginDTO;
import com.saturday.dto.EmployeePageQueryDTO;
import com.saturday.entity.Employee;
import com.saturday.result.PageResult;
import com.saturday.result.Result;

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
    /**
     * page query
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * start or stop employee
     */
    void startOrStop(Integer status,Long id);

    /**
     * get employee by id
     */
    Employee getById(Long id);
}
