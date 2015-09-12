package com.bss.proj.base;

import java.util.List;

public interface BaseDao<T>
{
	/**
	 * 〈方法详细描述〉
	 * @param param 查询一条记录的条件
	 * @return T 单条记录，查询到多条则取第一条
	 * @author hongchaoMao <br/>
	 * Create date: 2015年8月23日
	 */
	T selectOne(T param);
	
	/**
	 * 〈方法详细描述〉
	 * @param param 查询列表条件
	 * @return List<T>
	 * @author hongchaoMao <br/>
	 * Create date: 2015年8月23日
	 */
	List<T> selectList(T param);
	
	/**
	 * 〈方法详细描述〉
	 * @param param 查询条件
	 * @return int 记录数
	 * @author hongchaoMao <br/>
	 * Create date: 2015年8月23日
	 */
	int selectCount(T param);
	
	
}
