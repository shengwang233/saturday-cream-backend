package com.saturday.service.impl;

import com.saturday.dto.SetmealDTO;
import com.saturday.entity.Dish;
import com.saturday.entity.Setmeal;
import com.saturday.entity.SetmealDish;
import com.saturday.mapper.SetMealDishMapper;
import com.saturday.mapper.SetMealMapper;
import com.saturday.service.SetMealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealDishMapper setMealDishMapper;

    @Autowired
    private SetMealMapper setMealMapper;

    @Override
    @Transactional
    public void saveWithDish(SetmealDTO setmealDTO) {
        //1. insert setmeal into the set meal table
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setMealMapper.insert(setmeal);

        //2. insert dishes into setmeal_dish table
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        for(SetmealDish setmealDish : setmealDishes){
            setmealDish.setSetmealId(setmeal.getId());
        }

        setMealDishMapper.insertBatch(setmealDishes);

    }
}
