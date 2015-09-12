package com.bss.proj.base.exception;

import org.apache.log4j.Logger;

/**
 * <b>Package:</b> com.bss.proj.base.exception<br/>
 * 〈类详细描述〉
 * @author hongchaoMao <br/>
 * Create date: 2015年9月6日
 * @version 1.0
 */
public class BSSException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private Logger logger = Logger.getLogger(getClass());

    public BSSException(String reason)
    {
        super(reason);
        logger.error("System error : " + reason);
    }

    public BSSException(Throwable throwable)
    {
        super(throwable);
        logger.error("System error : " + throwable.toString());
    }

    public BSSException(String reason, Throwable throwable)
    {
        super(reason, throwable);
        logger.error("System error : " + reason, throwable);
    }

}
