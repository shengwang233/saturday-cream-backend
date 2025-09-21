package com.saturday.service;

import com.saturday.dto.SetmealDTO;
import com.saturday.dto.SetmealPageQueryDTO;
import com.saturday.entity.Setmeal;
import com.saturday.result.PageResult;
import com.saturday.vo.SetmealVO;

import java.util.List;

public interface SetMealService {

    void saveWithDish(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    void startOrStop(Integer status, Long id);

    SetmealVO getById(Long id);

    void updateWithDishes(SetmealDTO setmealDTO);

    void deleteBatch(List<Long> ids);

}
