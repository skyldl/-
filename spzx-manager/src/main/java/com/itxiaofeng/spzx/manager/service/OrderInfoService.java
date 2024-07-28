package com.itxiaofeng.spzx.manager.service;

import com.itxiaofeng.spzx.model.dto.order.OrderStatisticsDto;
import com.itxiaofeng.spzx.model.vo.order.OrderStatisticsVo;

public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
