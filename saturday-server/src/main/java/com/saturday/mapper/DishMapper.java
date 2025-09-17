package com.saturday.mapper;

import com.saturday.annotaion.AutoFill;
import com.saturday.entity.Dish;
import com.saturday.entity.DishFlavor;
import com.saturday.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    @AutoFill(value = OperationType.INSERT)
    void insertBatch(DishFlavor dishFlavor);

}
