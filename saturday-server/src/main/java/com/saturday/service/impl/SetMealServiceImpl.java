package com.saturday.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.saturday.dto.SetmealDTO;
import com.saturday.dto.SetmealPageQueryDTO;
import com.saturday.entity.Dish;
import com.saturday.entity.Setmeal;
import com.saturday.entity.SetmealDish;
import com.saturday.mapper.SetMealDishMapper;
import com.saturday.mapper.SetMealMapper;
import com.saturday.result.PageResult;
import com.saturday.service.SetMealService;
import com.saturday.vo.SetmealVO;
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



    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());

        Page<SetmealVO> pages = setMealMapper.pageQuery(setmealPageQueryDTO);

        long total = pages.getTotal();
        List<SetmealVO> result = pages.getResult();

        return new PageResult(total, result);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Setmeal setmeal = setMealMapper.getById(id);
        setmeal.setStatus(status);
        setMealMapper.update(setmeal);

    }
}
