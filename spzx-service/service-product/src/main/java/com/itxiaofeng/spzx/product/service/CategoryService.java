package com.itxiaofeng.spzx.product.service;

import com.itxiaofeng.spzx.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findOneCategory();

    List<Category> findCategoryTree();
}
