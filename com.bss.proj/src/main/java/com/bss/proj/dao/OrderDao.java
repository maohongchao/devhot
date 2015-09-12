package com.bss.proj.dao;

import com.bss.proj.base.BaseDao;
import com.bss.proj.entity.Order;

/**
 * <b>Package:</b> com.bss.proj.dao<br/>
 * 〈类详细描述〉
 * @author hongchaoMao <br/>
 * Create date: 2015年8月22日
 * @version 1.0
 */
public interface OrderDao extends BaseDao<Order>
{
	/**
	 * 〈方法详细描述〉
	 * @param orderId 订单号ID
	 * @return Order
	 * @author hongchaoMao <br/>
	 * Create date: 2015年8月22日
	 */
	Order findOrderById(String orderId);
}
