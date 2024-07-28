package com.itxiaofeng.spzx.product.mapper;

import com.itxiaofeng.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> findOneCategory();

    List<Category> findAll();
}
