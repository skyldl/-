package com.itxiaofeng.spzx.product.mapper;

import com.itxiaofeng.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailsMapper {
    ProductDetails getByProductId(Long productId);
}
