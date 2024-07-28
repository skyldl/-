package com.itxiaofeng.spzx.product.mapper;

import com.itxiaofeng.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    Product getById(Long id);
}
