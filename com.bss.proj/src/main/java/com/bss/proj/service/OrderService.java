package com.bss.proj.service;

import com.bss.proj.entity.Order;

public interface OrderService
{
    /**
     * 〈方法详细描述〉
     * @param orderId
     * @return
     * @author hongchaoMao <br/>
     * Create date: 2015年9月5日
     */
    Order findOrderById(String orderId);
}
