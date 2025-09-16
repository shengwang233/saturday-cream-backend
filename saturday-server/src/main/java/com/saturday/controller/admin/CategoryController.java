package com.saturday.controller.admin;

import com.saturday.dto.CategoryDTO;
import com.saturday.dto.CategoryPageQueryDTO;
import com.saturday.entity.Category;
import com.saturday.result.PageResult;
import com.saturday.result.Result;
import com.saturday.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Category Management Controller
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Add a new category
     * @param categoryDTO
     * @return Result<String>
     */
    @PostMapping
    public Result<String> save(@RequestBody CategoryDTO categoryDTO){
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     * Paginated query for categories
     * @param categoryPageQueryDTO
     * @return Result<PageResult>
     */
    @GetMapping("/page")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Delete a category by ID
     * @param id
     * @return Result<String>
     */
    @DeleteMapping
    public Result<String> deleteById(Long id){
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * Update category information
     * @param categoryDTO
     * @return Result<String>
     */
    @PutMapping
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * Enable or disable a category
     * @param status category status (0 = disabled, 1 = enabled)
     * @param id category ID
     * @return Result<String>
     */
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id){
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * Get a list of categories by type
     * @param type category type
     * @return Result<List<Category>>
     */
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
