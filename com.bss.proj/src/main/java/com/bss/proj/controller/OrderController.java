/**
 * 
 */
package com.bss.proj.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bss.proj.entity.Order;
import com.bss.proj.service.OrderService;

/**
 * <b>Package:</b> com.bss.proj.controller<br/>
 * 〈类详细描述〉
 * @author hongchaoMao <br/>
 * Create date: 2015年8月24日
 * @version 1.0
 */
@Controller
@RequestMapping("order")
public class OrderController
{
    @Resource
    private OrderService orderService;
    
    @ResponseBody
    @RequestMapping(value="getOrderById/{orderId}",method=RequestMethod.GET)
    public ResponseEntity<Order> getOrderById(@PathVariable(value="orderId") String orderId)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("BSS-HEADER", "BSS-PROJ");
        
        Order order = orderService.findOrderById(orderId);
        ResponseEntity<Order> res = new ResponseEntity<Order>(order,headers,HttpStatus.CREATED);
        return res;
    }
}
