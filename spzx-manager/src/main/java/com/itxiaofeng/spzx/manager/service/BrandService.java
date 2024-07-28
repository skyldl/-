package com.itxiaofeng.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.itxiaofeng.spzx.model.entity.product.Brand;

import java.util.List;

public interface BrandService {
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);

    List<Brand> findAll();
}
