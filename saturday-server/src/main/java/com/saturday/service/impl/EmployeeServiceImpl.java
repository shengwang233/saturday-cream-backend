package com.saturday.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.saturday.context.BaseContext;
import com.saturday.dto.EmployeeDTO;
import com.saturday.dto.EmployeeLoginDTO;
import com.saturday.dto.EmployeePageQueryDTO;
import com.saturday.entity.Employee;
import com.saturday.exception.AccountLockedException;
import com.saturday.exception.AccountNotFoundException;
import com.saturday.exception.PasswordErrorException;
import com.saturday.mapper.EmployeeMapper;
import com.saturday.result.PageResult;
import com.saturday.result.Result;
import com.saturday.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

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
            throw new AccountNotFoundException("Account Not Found");

        }
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!password.equals(employee.getPassword())){
            throw new PasswordErrorException("Invalid username or password");
        }

        if (employee.getStatus() == 0) {
            //locked
            throw new AccountLockedException("Account Locked");
        }

        return employee;
    }

    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        employee.setStatus(1); // 1 is active 0 is disabled
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes())); // default password is 123456
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(BaseContext.getCurrentId()); //admin user
        employee.setUpdateUser(BaseContext.getCurrentId()); //admin user
        // mapper layer
        employeeMapper.insert(employee);
    }

    @Override
    public PageResult page(EmployeePageQueryDTO employeePageQueryDTO) {
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        long total = page.getTotal();
        List<Employee> records = page.getResult();
        return new PageResult(total, records);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Employee employee = Employee.builder().status(status).id(id).updateTime(LocalDateTime.now()).updateUser(BaseContext.getCurrentId()).build();
        employeeMapper.update(employee);
    }

    @Override
    public Employee getById(Long id) {
        return employeeMapper.getById(id);

    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.update(employee);
    }


}
