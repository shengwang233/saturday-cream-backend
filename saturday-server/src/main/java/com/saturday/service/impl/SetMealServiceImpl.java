package com.saturday.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.saturday.dto.SetmealDTO;
import com.saturday.dto.SetmealPageQueryDTO;
import com.saturday.entity.Dish;
import com.saturday.entity.Setmeal;
import com.saturday.entity.SetmealDish;
import com.saturday.exception.DeletionNotAllowedException;
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

        //2.get the set meal id of the setmeal
        Long categoryId = setmeal.getId();

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if (setmealDishes != null && setmealDishes.size() > 0){
            setmealDishes.forEach(setmealDish-> {
                setmealDish.setSetmealId(categoryId);
            });
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

    @Override
    public SetmealVO getById(Long id) {
        Setmeal setmeal = setMealMapper.getById(id);
        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal, setmealVO);
        List<SetmealDish> setmealDishes = setMealDishMapper.getBySetmealId(id);
        setmealVO.setSetmealDishes(setmealDishes);
        return setmealVO;
    }

    @Override
    public void updateWithDishes(SetmealDTO setmealDTO) {

        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        //1. update setmeal table
        setMealMapper.update(setmeal);

        //2. delete in setmeal_dish table
        setMealDishMapper.deleteBySetmealId(setmealDTO.getId());

        //3. add new dishes if exits
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if(setmealDishes != null && setmealDishes.size() > 0){
            setmealDishes.forEach(setmealDish -> setmealDish.setSetmealId(setmealDTO.getId()));
            setMealDishMapper.insertBatch(setmealDishes);

        }
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        List<Setmeal> setmeals = setMealMapper.getByIds(ids);
        //if set meal is active, cannot be deleted
        for(Setmeal setmeal : setmeals){
            if (setmeal.getStatus() == 1){
                throw new DeletionNotAllowedException("Set Meal is currently active, cannot be deleted");
            }
        }
        setMealMapper.deleteBatch(ids);
    }
}
