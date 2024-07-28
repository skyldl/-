package com.itxiaofeng.spzx.product.service;

import com.github.pagehelper.PageInfo;
import com.itxiaofeng.spzx.model.dto.h5.ProductSkuDto;
import com.itxiaofeng.spzx.model.entity.product.ProductSku;
import com.itxiaofeng.spzx.model.vo.h5.ProductItemVo;

import java.util.List;

public interface ProductService {
    List<ProductSku> findProductSkuBySale();

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductItemVo item(Long skuId);

    ProductSku getBySkuId(Long skuId);
}
