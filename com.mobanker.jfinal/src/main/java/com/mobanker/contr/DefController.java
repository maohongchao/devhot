package com.mobanker.contr;

import com.jfinal.core.Controller;

/**
 * <b>Package Name:</b> com.mobanker.contr <br/>
 * <b>Description:</b>〈默认页面〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
public class DefController extends Controller
{
    public void index()
    {
        render("index.html");
    }
}
