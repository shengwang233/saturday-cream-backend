package com.saturday.controller.admin;

import com.saturday.dto.SetmealDTO;
import com.saturday.result.Result;
import com.saturday.service.SetMealService;
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
}
