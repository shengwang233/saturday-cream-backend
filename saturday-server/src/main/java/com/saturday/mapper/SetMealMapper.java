package com.saturday.mapper;

import com.github.pagehelper.Page;
import com.saturday.annotaion.AutoFill;
import com.saturday.dto.SetmealDTO;
import com.saturday.dto.SetmealPageQueryDTO;
import com.saturday.entity.Setmeal;
import com.saturday.enumeration.OperationType;
import com.saturday.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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


    List<Setmeal> getByIds(List<Long> ids);

    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);


    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);


    void deleteBatch(List<Long> ids);

}
