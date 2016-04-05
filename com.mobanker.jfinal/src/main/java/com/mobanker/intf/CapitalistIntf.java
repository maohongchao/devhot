package com.mobanker.intf;

import com.mobanker.entity.Capitalist;

/**
 * <b>Package Name:</b> com.mobanker.intf <br/>
 * <b>Description:</b>〈理财人服务接口〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
public interface CapitalistIntf
{
    /**
     * <b>Description:</b>〈根据理财用户ID查询理财人信息〉<br/>
     * @param userId 理财人用户ID
     * @return Capitalist
     * @author hongchaoMao <br/>
     * Create date: 2016年2月19日
     */
    Capitalist getCapitalistByUserId(String userId);
}
