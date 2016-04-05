package com.mobanker.intf;

import com.mobanker.entity.AssetPkgDf;

/**
 * <b>Package Name:</b> com.mobanker.intf <br/>
 * <b>Description:</b>〈资产包代付记录服务接口〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
public interface AssetPkgDfIntf
{
    /**
     * <b>Description:</b>〈根据借款单号获取资产包代付记录信息〉<br/>
     * @param borrowNid 借款单号
     * @return AssetPkgDf
     * @author hongchaoMao <br/>
     * Create date: 2016年2月19日
     */
    AssetPkgDf getByBorrowNid(String borrowNid);
}
