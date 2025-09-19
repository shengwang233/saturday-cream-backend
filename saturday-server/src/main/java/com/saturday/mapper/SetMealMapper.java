package com.saturday.mapper;

import com.github.pagehelper.Page;
import com.saturday.annotaion.AutoFill;
import com.saturday.dto.SetmealPageQueryDTO;
import com.saturday.entity.Setmeal;
import com.saturday.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetMealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);

    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    Page<Setmeal> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);


    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

}
