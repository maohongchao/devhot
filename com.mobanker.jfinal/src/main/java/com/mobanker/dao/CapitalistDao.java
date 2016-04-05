package com.mobanker.dao;

import com.mobanker.entity.Capitalist;

/**
 * <b>Package Name:</b> com.mobanker.dao <br/>
 * <b>Description:</b>〈理财人数据访问类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
public class CapitalistDao
{
    private final Capitalist dao = new Capitalist();

    /**
     * <b>Description:</b>〈根据理财用户ID查询理财人信息〉<br/>
     * @param userId 理财人用户ID
     * @return Capitalist
     * @author hongchaoMao <br/>
     * Create date: 2016年2月19日
     */
    public Capitalist getCapitalistByUserId(String userId)
    {
        String sql = "SELECT * FROM T_CAPITALIST T WHERE USER_ID = ?";
        return dao.findFirst(sql, userId);
    }
}
