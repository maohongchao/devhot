package com.mobanker.intf;

import java.util.Map;

/**
 * <b>Package Name:</b> com.mobanker.intf <br/>
 * <b>Description:</b>〈系统变量接口类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public interface SystemVariableIntf
{
    /**
     * 根据nid集合标识查找系统变量
     * @param nids 系统变量Nid集合
     * @return Map<String, String>
     * @author hongchaoMao <br/>
     * Create date: 2016年2月17日
     */
    Map<String, String> getSys(String... nids);
}
