package com.itxiaofeng.spzx.order.service;

import com.github.pagehelper.PageInfo;
import com.itxiaofeng.spzx.model.dto.h5.OrderInfoDto;
import com.itxiaofeng.spzx.model.entity.order.OrderInfo;
import com.itxiaofeng.spzx.model.vo.h5.TradeVo;

public interface OrderInfoService {
    TradeVo getTrade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus);
}
