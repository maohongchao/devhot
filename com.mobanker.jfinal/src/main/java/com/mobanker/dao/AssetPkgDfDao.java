package com.mobanker.dao;

import com.mobanker.entity.AssetPkgDf;

/**
 * <b>Package Name:</b> com.mobanker.dao <br/>
 * <b>Description:</b>〈资产包代付记录数据访问类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
public class AssetPkgDfDao
{
    private final AssetPkgDf dao = new AssetPkgDf();

    /**
     * <b>Description:</b>〈根据借款单号获取资产包代付记录信息〉<br/>
     * @param borrowNid 借款单号
     * @return AssetPkgDf
     * @author hongchaoMao <br/>
     * Create date: 2016年2月19日
     */
    public AssetPkgDf getByBorrowNid(String borrowNid)
    {
        String sql = "SELECT * FROM T_ASSET_PKG_DF WHERE BORROW_NID = ?";
        return dao.findFirst(sql, borrowNid);
    }
}
