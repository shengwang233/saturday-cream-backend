package com.saturday.service;

import com.saturday.dto.CategoryDTO;
import com.saturday.dto.CategoryPageQueryDTO;
import com.saturday.entity.Category;
import com.saturday.result.PageResult;
import java.util.List;

public interface CategoryService {

    /**
     *
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     *
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     *
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     *
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * list
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
