package com.mobanker.intf;

import com.mobanker.entity.Bid;

/**
 * <b>Package Name:</b> com.mobanker.intf <br/>
 * <b>Description:</b>〈理财标的服务接口〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
public interface BidIntf
{
    /**
     * <b>Description:</b>〈根据标的号查找标的信息〉<br/>
     * @param bidNo 查找的标的号
     * @return Bid 标的记录
     * @author hongchaoMao <br/>
     * Create date: 2016年2月19日
     */
    Bid findBidByBidNo(String bidNo);
}
