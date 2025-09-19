package com.saturday.controller.admin;

import com.github.pagehelper.PageHelper;
import com.saturday.dto.SetmealDTO;
import com.saturday.dto.SetmealPageQueryDTO;
import com.saturday.entity.Setmeal;
import com.saturday.result.PageResult;
import com.saturday.result.Result;
import com.saturday.service.SetMealService;
import com.saturday.vo.SetmealVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/setmeal")
public class SetMealController {
    @Autowired
    private SetMealService setMealService;
    /**
     * Add new set meal
     */

    @PostMapping
    public Result save(@RequestBody SetmealDTO setmealDTO){
        setMealService.saveWithDish(setmealDTO);
        return Result.success();
    }
    /**
     * page query
     */
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        PageResult pageResult = setMealService.pageQuery(setmealPageQueryDTO);

        return Result.success(pageResult);
    }
    /**
     * set meal enable, disable
     */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status, Long id){
        setMealService.startOrStop(status, id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id){
        SetmealVO setmealVO = setMealService.getById(id);
        return Result.success(setmealVO);
    }

}
