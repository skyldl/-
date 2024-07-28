package com.itxiaofeng.spzx.product.service.Impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.itxiaofeng.spzx.model.entity.product.Category;
import com.itxiaofeng.spzx.product.mapper.CategoryMapper;
import com.itxiaofeng.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Override
    @Cacheable(value = "category" , key = "'all'")
    public List<Category> findOneCategory() {
        return categoryMapper.findOneCategory();
    }

//    @Override
//    public List<Category> findOneCategory() {
//
//        // 从Redis缓存中查询所有的一级分类数据
//        String categoryListJSON = redisTemplate.opsForValue().get("category:one");
//        if(!StringUtils.isEmpty(categoryListJSON)) {
//            List<Category> categoryList = JSON.parseArray(categoryListJSON, Category.class);
//            return categoryList ;
//        }
//
//        List<Category> categoryList = categoryMapper.findOneCategory();
//        redisTemplate.opsForValue().set("category:one" , JSON.toJSONString(categoryList) , 7 , TimeUnit.DAYS);
//        return categoryList ;
//    }




    // 接口实现类
    @Override
    public List<Category> findCategoryTree() {
        List<Category> categoryList = categoryMapper.findAll();
        //全部一级分类
        List<Category> oneCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == 0).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(oneCategoryList)) {
            oneCategoryList.forEach(oneCategory -> {
                List<Category> twoCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue()).collect(Collectors.toList());
                oneCategory.setChildren(twoCategoryList);

                if(!CollectionUtils.isEmpty(twoCategoryList)) {
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue()).collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;
    }
}
