package com.itxiaofeng.spzx.manager.mapper;

import com.itxiaofeng.spzx.model.dto.order.OrderStatisticsDto;
import com.itxiaofeng.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatisticsMapper {
    void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
