package com.saturday.service.impl;

import com.saturday.dto.DishDTO;
import com.saturday.entity.Dish;
import com.saturday.entity.DishFlavor;
import com.saturday.mapper.DishFlavorMapper;
import com.saturday.mapper.DishMapper;
import com.saturday.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

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
}
