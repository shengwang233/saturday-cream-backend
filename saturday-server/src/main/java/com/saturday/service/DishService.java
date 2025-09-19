package com.saturday.service;

import com.github.pagehelper.Page;
import com.saturday.dto.DishDTO;
import com.saturday.dto.DishPageQueryDTO;
import com.saturday.entity.Dish;
import com.saturday.result.PageResult;
import com.saturday.vo.DishVO;

import java.util.List;

public interface DishService {
    void saveWithFlavors(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);

    DishVO getByIdWithFlavors(Long id);

    void updateWithFlavors(DishDTO dishDTO);

    List<Dish> list(Long categoryId);

}
