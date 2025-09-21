package com.saturday.mapper;

import com.github.pagehelper.Page;
import com.saturday.annotaion.AutoFill;
import com.saturday.dto.DishDTO;
import com.saturday.dto.DishPageQueryDTO;
import com.saturday.entity.Dish;
import com.saturday.entity.DishFlavor;
import com.saturday.enumeration.OperationType;
import com.saturday.vo.DishVO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);


    List<Dish> getByIds(@Param("ids") List<Long> ids);


    @Delete("DELETE FROM dish WHERE id = #{id}")
    void deleteById(Long id);

    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    List<Dish> list(Dish dish);

    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);


}
