package com.saturday.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.saturday.context.BaseContext;
import com.saturday.dto.CategoryDTO;
import com.saturday.dto.CategoryPageQueryDTO;
import com.saturday.entity.Category;
import com.saturday.exception.DeletionNotAllowedException;
import com.saturday.mapper.CategoryMapper;
import com.saturday.mapper.DishMapper;
import com.saturday.mapper.SetMealMapper;
import com.saturday.result.PageResult;
import com.saturday.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Category Service Implementation
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetMealMapper setmealMapper;

    /**
     * Add a new category
     * @param categoryDTO
     */
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        // Copy properties from DTO to entity
        BeanUtils.copyProperties(categoryDTO, category);

        // Default status is disabled (0)
        category.setStatus(0);

        // Set creation and update info
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.insert(category);
    }

    /**
     * Paginated query
     * @param categoryPageQueryDTO
     * @return PageResult
     */
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        // The next SQL will be executed with pagination (LIMIT automatically added)
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * Delete a category by ID
     * @param id
     */
    public void deleteById(Long id) {
        // Check if the category is associated with any dishes
        Integer count = dishMapper.countByCategoryId(id);
        if (count > 0) {
            throw new DeletionNotAllowedException("The category is associated with dishes and cannot be deleted.");
        }

        // Check if the category is associated with any set meals
        count = setmealMapper.countByCategoryId(id);
        if (count > 0) {
            throw new DeletionNotAllowedException("The category is associated with set meals and cannot be deleted.");
        }

        // Delete category
        categoryMapper.deleteById(id);
    }

    /**
     * Update category information
     * @param categoryDTO
     */
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        // Set update info
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.update(category);
    }

    /**
     * Enable or disable a category
     * @param status 0 = disabled, 1 = enabled
     * @param id category ID
     */
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    /**
     * Get categories by type
     * @param type
     * @return List<Category>
     */
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }
}
