package com.mobanker.dao;

import com.mobanker.entity.Bid;

/**
 * <b>Package Name:</b> com.mobanker.dao <br/>
 * <b>Description:</b>〈理财标的数据访问类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
public class BidDao
{
    private final Bid dao = new Bid();
    
    /**
     * <b>Description:</b>〈根据标的号查找标的信息〉<br/>
     * @param bidNo 查找的标的号
     * @return Bid 标的记录
     * @author hongchaoMao <br/>
     * Create date: 2016年2月19日
     */
    public Bid findBidByBidNo(String bidNo)
    {
        String sql = "SELECT BID.*,CAP.`UNAME` AS `USER_NAME` FROM `T_BID` BID "
                + "LEFT JOIN `T_CAPITALIST` CAP ON BID.`USER_ID` = CAP.`USER_ID` "
                + "AND CAP.`USER_ID` != '' WHERE BID.`BID_NO` = ?";
        return dao.findFirst(sql, bidNo);
    }
}
