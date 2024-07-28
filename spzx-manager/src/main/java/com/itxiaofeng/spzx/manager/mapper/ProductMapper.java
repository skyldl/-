package com.itxiaofeng.spzx.manager.mapper;

import com.itxiaofeng.spzx.model.dto.product.ProductDto;
import com.itxiaofeng.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    public abstract List<Product> findByPage(ProductDto productDto);

    void save(Product product);

    Product selectById(Long id);

    void updateById(Product product);

    void deleteById(Long id);
}
