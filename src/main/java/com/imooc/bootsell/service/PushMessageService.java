package com.imooc.bootsell.service;

import com.imooc.bootsell.dto.OrderDTO;

public interface PushMessageService {
    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);

}
