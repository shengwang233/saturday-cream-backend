package com.saturday.controller.admin;

import com.saturday.dto.DishDTO;
import com.saturday.dto.DishPageQueryDTO;
import com.saturday.result.PageResult;
import com.saturday.result.Result;
import com.saturday.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    /**
     * Add new dish
     * @param dishDTO
     * @return
     */
    @PostMapping
    public Result save (@RequestBody DishDTO dishDTO){
        dishService.saveWithFlavors(dishDTO);
        return Result.success();
    }
    /**
     * page query
     */
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * delete dish
     *
     */
    @DeleteMapping()
    public Result delete(@RequestParam List<Long> ids){
        dishService.deleteBatch(ids);
        return Result.success();
    }


}
