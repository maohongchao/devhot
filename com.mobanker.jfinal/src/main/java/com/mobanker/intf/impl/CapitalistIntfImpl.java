package com.mobanker.intf.impl;

import org.apache.commons.lang.StringUtils;

import com.mobanker.dao.CapitalistDao;
import com.mobanker.entity.Capitalist;
import com.mobanker.intf.CapitalistIntf;

/**
 * <b>Package Name:</b> com.mobanker.intf.impl <br/>
 * <b>Description:</b>〈理财人服务接口实现类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
public class CapitalistIntfImpl implements CapitalistIntf
{
    private CapitalistDao capitalistDao = new CapitalistDao();
    
    /**
     * <b>Description:</b>〈根据理财用户ID查询理财人信息〉<br/>
     * @param userId 理财人用户ID
     * @return Capitalist
     * @author hongchaoMao <br/>
     * Create date: 2016年2月19日
     * @see com.mobanker.intf.CapitalistIntf#getCapitalistByUserId(java.lang.String)
     */
    @Override
    public Capitalist getCapitalistByUserId(String userId)
    {
        if (StringUtils.isBlank(userId))
        {
            return null;
        }
        return capitalistDao.getCapitalistByUserId(userId);
    }

}
