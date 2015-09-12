/**
 * 
 */
package com.bss.proj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bss.proj.base.exception.BSSException;
import com.bss.proj.dao.OrderDao;
import com.bss.proj.entity.Order;
import com.bss.proj.service.OrderService;

/**
 * <b>Package:</b> com.bss.proj.service.impl<br/>
 * 〈类详细描述〉
 * @author hongchaoMao <br/>
 * Create date: 2015年8月23日
 * @version 1.0
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService
{
    @Resource
    private OrderDao orderDao;

    /**
     * 〈方法详细描述〉
     * @param orderId
     * @return
     * @author hongchaoMao <br/>
     * Create date: 2015年9月5日
     * @see com.bss.proj.service.OrderService#findOrderById(java.lang.String)
     */
    @Override
    public Order findOrderById(String orderId)
    {
        if (StringUtils.isEmpty(orderId))
        {
            throw new BSSException("The parameter 'orderId' is empty !");
        }
        return orderDao.findOrderById(orderId);
    }
    
    

}
