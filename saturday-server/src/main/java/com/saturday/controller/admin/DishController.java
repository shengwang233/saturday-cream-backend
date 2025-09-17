package com.saturday.controller.admin;

import com.saturday.dto.DishDTO;
import com.saturday.result.Result;
import com.saturday.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    public Result save (@RequestBody DishDTO dishDTO){
        dishService.saveWithFlavors(dishDTO);
        return Result.success();
    }
}
