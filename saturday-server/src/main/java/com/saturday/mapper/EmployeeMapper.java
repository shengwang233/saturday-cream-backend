package com.saturday.mapper;

import com.github.pagehelper.Page;
import com.saturday.dto.EmployeeDTO;
import com.saturday.dto.EmployeePageQueryDTO;
import com.saturday.entity.Employee;
import com.saturday.result.PageResult;
import com.saturday.result.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Insert("INSERT INTO employee (name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES (#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime},#{updateTime},#{createUser}, #{updateUser})")
    void insert(Employee employee);

    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

//    @Update("UPDATE employee SET status = #{Status} WHERE id = #{id}")
    void startOrStopUpdate(Employee employee);

    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);
}
