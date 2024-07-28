package com.itxiaofeng.spzx.manager.mapper;

import com.itxiaofeng.spzx.model.dto.product.CategoryBrandDto;
import com.itxiaofeng.spzx.model.entity.product.Brand;
import com.itxiaofeng.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryBrandMapper {
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
