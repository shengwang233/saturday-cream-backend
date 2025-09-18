package com.saturday.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.saturday.dto.DishDTO;
import com.saturday.dto.DishPageQueryDTO;
import com.saturday.entity.Dish;
import com.saturday.entity.DishFlavor;
import com.saturday.exception.DeletionNotAllowedException;
import com.saturday.mapper.DishFlavorMapper;
import com.saturday.mapper.DishMapper;
import com.saturday.mapper.SetMealDishMapper;
import com.saturday.result.PageResult;
import com.saturday.result.Result;
import com.saturday.service.DishService;
import com.saturday.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetMealDishMapper setMealDishMapper;

    @Override

    public void saveWithFlavors(DishDTO dishDTO) {
        Dish dish = new Dish();
        // insert 1 item into dish table
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);

        // insert n items into dish_flavor table if dishDTO.flavors is not empty
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors  != null && flavors.size() > 0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> dishes = dishMapper.pageQuery(dishPageQueryDTO);
        long total = dishes.getTotal();
        List<DishVO> records = dishes.getResult();
        PageResult pageResult = new PageResult(total, records);

        return pageResult;
    }

    @Transactional
    @Override
    public void deleteBatch(List<Long> ids) {
        List<Dish> dishList = dishMapper.getByIds(ids);
        if(dishList == null || dishList.size() == 0){
            return;
        }
        for(Dish dish : dishList){
            if (dish.getStatus() == 1){
                throw new DeletionNotAllowedException("Dish is currently active, cannot be deleted");
            }
        }
        int relatedCount = setMealDishMapper.countByDishIds(ids);
        if (relatedCount > 0){
            throw new DeletionNotAllowedException("Some dishes are in set meal, cannot be deleted");
        }

        ids.forEach(dishId -> {
            dishMapper.deleteById(dishId);
            dishFlavorMapper.deleteByDishId(dishId);
        });
    }
}
