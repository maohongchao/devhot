package com.mobanker.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

/**
 * <b>Package Name:</b> com.mobanker.handler <br/>
 * <b>Description:</b>〈路由处理〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月3日
 * @version v1.0.0
 */
public class MyHandler extends Handler
{

    /**
     * <b>Description:</b>〈路由处理方法〉<br/>
     * @author hongchaoMao <br/>
     * Create date: 2016年2月3日
     * @see com.jfinal.handler.Handler#handle(java.lang.String, javax.servlet.http.HttpServletRequest, 
     * javax.servlet.http.HttpServletResponse, boolean[])
     */
    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled)
    {
        System.out.println("---"+target);
    }

}
