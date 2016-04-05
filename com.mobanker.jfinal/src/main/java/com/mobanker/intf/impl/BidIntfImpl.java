package com.mobanker.intf.impl;

import org.apache.commons.lang.StringUtils;

import com.mobanker.dao.BidDao;
import com.mobanker.entity.Bid;
import com.mobanker.intf.BidIntf;

/**
 * <b>Package Name:</b> com.mobanker.intf.impl <br/>
 * <b>Description:</b>〈理财标的服务接口实现类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
public class BidIntfImpl implements BidIntf
{
    private BidDao bidDao = new BidDao();
    
    /**
     * <b>Description:</b>〈根据标的号查找标的信息〉<br/>
     * @param bidNo 查找的标的号
     * @return Bid 标的记录
     * @author hongchaoMao <br/>
     * Create date: 2016年2月19日
     * @see com.mobanker.intf.BidIntf#findBidByBidNo(java.lang.String)
     */
    @Override
    public Bid findBidByBidNo(String bidNo)
    {
        if (StringUtils.isBlank(bidNo))
        {
            return null;
        }
        return bidDao.findBidByBidNo(bidNo);
    }

}
