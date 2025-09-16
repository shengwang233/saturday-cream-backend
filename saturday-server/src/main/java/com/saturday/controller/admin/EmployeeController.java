package com.saturday.controller.admin;

import com.saturday.dto.EmployeeDTO;
import com.saturday.dto.EmployeePageQueryDTO;
import com.saturday.properties.JwtProperties;
import com.saturday.result.PageResult;
import com.saturday.result.Result;
import com.saturday.dto.EmployeeLoginDTO;
import com.saturday.entity.Employee;
import com.saturday.service.EmployeeService;
import com.saturday.utils.JwtUtil;
import com.saturday.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        Employee employee = employeeService.login(employeeLoginDTO);
        //if success it will generate jwt token
        Map<String,Object> claims = new HashMap<>();
        claims.put("empId", employee.getId());
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();
        return Result.success(employeeLoginVO);
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.success();
    }

    /**
     * insert new employee
     * @param employeeDTO
     * @return
     */
    @PostMapping
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * pagination
     */
    @GetMapping("/page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        PageResult pageResult = employeeService.page(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * enable / disable state of employee
     */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable("status") Integer status, Long id) {
        employeeService.startOrStop(status, id);
        return Result.success();
    }


}
