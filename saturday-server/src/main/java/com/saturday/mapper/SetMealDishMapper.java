package com.saturday.mapper;

import com.github.pagehelper.Page;
import com.saturday.annotaion.AutoFill;
import com.saturday.dto.DishPageQueryDTO;
import com.saturday.entity.Dish;
import com.saturday.entity.DishFlavor;
import com.saturday.entity.SetmealDish;
import com.saturday.enumeration.OperationType;
import com.saturday.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealDishMapper {


    int countByDishIds(List<Long> id);

    void insertBatch(@Param("setmealDishes")List<SetmealDish> setmealDishes);

    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getBySetmealId(Long id);

    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteBySetmealId(Long id);


    void deleteBySetmealIds(@Param("categoryIds") List<Long> categoryIds);


}
