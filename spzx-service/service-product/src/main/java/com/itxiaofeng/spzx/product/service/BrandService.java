package com.itxiaofeng.spzx.product.service;

import com.itxiaofeng.spzx.model.entity.product.Brand;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface BrandService {
    @Cacheable(value = "brandList", unless="#result.size() == 0")
    List<Brand> findAll();
}
