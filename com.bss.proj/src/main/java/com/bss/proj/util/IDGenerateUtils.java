/**
 * 
 */
package com.bss.proj.util;

import java.util.Random;
import java.util.UUID;

/**
 * <b>Package:</b> com.bss.proj.util<br/>
 * 〈ID随机生成器〉
 * @author hongchaoMao <br/>
 * Create date: 2015年8月23日
 * @version 1.0
 */
public class IDGenerateUtils
{
	/**
	 * 〈生成一个32位长度的随机字符串〉
	 * @return String
	 * @author hongchaoMao <br/>
	 * Create date: 2015年8月23日
	 */
	public static String generate32()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 〈生成一个20位以下长度的长整型随机数〉
	 * @return Long
	 * @author hongchaoMao <br/>
	 * Create date: 2015年8月23日
	 */
	public static long generate20()
	{
		long timeLong = System.currentTimeMillis();
		long rdmLong = new Random(timeLong).nextLong();
		return Math.abs(timeLong+rdmLong);
	}
	/**
	 * 〈生成一个10位长度的长整型随机数〉
	 * @return Long
	 * @author hongchaoMao <br/>
	 * Create date: 2015年8月23日
	 */
	public static long generate10()
	{
		long timeLong = System.currentTimeMillis();
		long rdm = Math.abs(timeLong+new Random(timeLong).nextLong());
		return rdm%(long)Math.pow(10, 10);
	}
	
}
