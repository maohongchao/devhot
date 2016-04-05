package com.mobanker.conf;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.mobanker.contr.DefController;
import com.mobanker.contr.PdfController;
import com.mobanker.entity.Activity;
import com.mobanker.entity.Borrow;
import com.mobanker.entity.BorrowFee;
import com.mobanker.entity.SystemVariable;

/**
 * <b>Package Name:</b> com.mobanker.conf <br/>
 * <b>Description:</b>〈配置基础类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月3日
 * @version v1.0.0
 */
public class BaseConfig extends JFinalConfig
{

    /**
     * <b>Description:</b>〈配置系统常量〉<br/>
     * @param me
     * @author hongchaoMao <br/>
     * Create date: 2016年2月3日
     * @see com.jfinal.config.JFinalConfig#configConstant(com.jfinal.config.Constants)
     */
    @Override
    public void configConstant(Constants me)
    {
        me.setDevMode(true);
        me.setViewType(ViewType.VELOCITY);
    }

    /**
     * <b>Description:</b>〈配置系统访问路由〉<br/>
     * @param me
     * @author hongchaoMao <br/>
     * Create date: 2016年2月3日
     * @see com.jfinal.config.JFinalConfig#configRoute(com.jfinal.config.Routes)
     */
    @Override
    public void configRoute(Routes me)
    {
        me.add("/", DefController.class);
        me.add("/pdf", PdfController.class);
    }

    /**
     * <b>Description:</b>〈配置系统插件〉<br/>
     * @param me
     * @author hongchaoMao <br/>
     * Create date: 2016年2月3日
     * @see com.jfinal.config.JFinalConfig#configPlugin(com.jfinal.config.Plugins)
     */
    @Override
    public void configPlugin(Plugins me)
    {
        loadPropertyFile("conf/database.properties");
        
        String jdbcUrl = getProperty("jdbc.url");
        String user = getProperty("jdbc.username");
        String password = getProperty("jdbc.password");
        String driverClass = getProperty("jdbc.driver");
        Integer maxPoolSize = getPropertyToInt("pool.maxPoolSize");
        Integer minPoolSize = getPropertyToInt("pool.minPoolSize");
        Integer initialPoolSize = getPropertyToInt("pool.initialPoolSize");
        Integer maxIdleTime = getPropertyToInt("pool.maxIdleTime");
        Integer acquireIncrement = getPropertyToInt("pool.acquireIncrement");
        
        C3p0Plugin c3p0 = new C3p0Plugin(jdbcUrl, user, password, driverClass, maxPoolSize, minPoolSize,
                initialPoolSize, maxIdleTime, acquireIncrement);
        me.add(c3p0);
        
        ActiveRecordPlugin actRecord = new ActiveRecordPlugin(c3p0);
        actRecord.addMapping("t_activity", "id", Activity.class);
        actRecord.addMapping("t_borrow", "id", Borrow.class);
        actRecord.addMapping("t_borrow_fee", "id", BorrowFee.class);
        actRecord.addMapping("t_bas_system_variable", "id", SystemVariable.class);
        
        me.add(actRecord);
    }

    /**
     * <b>Description:</b>〈配置系统拦截器〉<br/>
     * @param me
     * @author hongchaoMao <br/>
     * Create date: 2016年2月3日
     * @see com.jfinal.config.JFinalConfig#configInterceptor(com.jfinal.config.Interceptors)
     */
    @Override
    public void configInterceptor(Interceptors me)
    {
    }

    /**
     * <b>Description:</b>〈配置系统处理器〉<br/>
     * @param me
     * @author hongchaoMao <br/>
     * Create date: 2016年2月3日
     * @see com.jfinal.config.JFinalConfig#configHandler(com.jfinal.config.Handlers)
     */
    @Override
    public void configHandler(Handlers me)
    {
    }

}
