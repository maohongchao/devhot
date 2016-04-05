package com.mobanker.intf.impl;

import org.apache.commons.lang.StringUtils;

import com.mobanker.dao.AssetPkgDfDao;
import com.mobanker.entity.AssetPkgDf;
import com.mobanker.intf.AssetPkgDfIntf;

/**
 * <b>Package Name:</b> com.mobanker.intf.impl <br/>
 * <b>Description:</b>〈资产包代付记录服务接口实现类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
public class AssetPkgDfIntfImpl implements AssetPkgDfIntf
{
    
    private AssetPkgDfDao assetPkgDfDao = new AssetPkgDfDao();
    
    /**
     * <b>Description:</b>〈根据借款单号获取资产包代付记录信息〉<br/>
     * @param borrowNid 借款单号
     * @return AssetPkgDf
     * @author hongchaoMao <br/>
     * Create date: 2016年2月19日
     * @see com.mobanker.intf.AssetPkgDfIntf#getByBorrowNid(java.lang.String)
     */
    @Override
    public AssetPkgDf getByBorrowNid(String borrowNid)
    {
        if (StringUtils.isBlank(borrowNid))
        {
            return null;
        }
        return assetPkgDfDao.getByBorrowNid(borrowNid);
    }

}
