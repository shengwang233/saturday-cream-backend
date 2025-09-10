package com.saturday.service.impl;

import com.saturday.dto.EmployeeLoginDTO;
import com.saturday.entity.Employee;
import com.saturday.exception.AccountLockedException;
import com.saturday.exception.AccountNotFoundException;
import com.saturday.exception.PasswordErrorException;
import com.saturday.mapper.EmployeeMapper;
import com.saturday.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        Employee employee = employeeMapper.getByUsername(username);
        if (employee == null){
            throw new AccountNotFoundException("user not existed");

        }
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!password.equals(employee.getPassword())){
            throw new PasswordErrorException("wrong password");
        }

        if (employee.getStatus() == 0) {
            //locked
            throw new AccountLockedException("user locked");
        }

        return employee;
    }
}
