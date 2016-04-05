package com.mobanker.dao;

import java.util.List;

import com.mobanker.entity.SystemVariable;

/**
 * <b>Package Name:</b> com.mobanker.dao <br/>
 * <b>Description:</b>〈类型详细描述〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public class SystemVariableDao
{
    private final SystemVariable dao = new SystemVariable();
    
    public List<SystemVariable> getSys(String... nids)
    {
        StringBuilder sql = new StringBuilder("SELECT T.NID,T.VALUE FROM T_BAS_SYSTEM_VARIABLE T WHERE T.NID IN (");
        for(int i=0;i<nids.length;i++)
        {
            sql.append("?,");
        }
        sql.replace(sql.length()-1, sql.length(), ")");
        return dao.find(sql.toString(), (Object[])nids);
    }
}
