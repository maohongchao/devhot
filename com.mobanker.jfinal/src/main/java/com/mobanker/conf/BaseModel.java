package com.mobanker.conf;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.jfinal.plugin.activerecord.Model;
import com.mobanker.util.DateUtils;

/**
 * <b>Package Name:</b> com.mobanker.conf <br/>
 * <b>Description:</b>〈实体基类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseModel<M extends BaseModel<?>> extends Model<M>
{
    private static final long serialVersionUID = 1L;

    protected String          id;

    protected Date            createTime;

    protected String          createUser;

    protected Date            updateTime;

    protected String          updateUser;

    @Override
    public List<M> find(String sql)
    {
        List<M> list =  super.find(sql.toUpperCase());
        for(M m : list)
        {
            m.initProperties();
        }
        return list;
    }
    
    @Override
    public List<M> find(String sql, Object... paras)
    {
        List<M> list =  super.find(sql.toUpperCase(), paras);
        for(M m : list)
        {
            m.initProperties();
        }
        return list;
    }
    
    protected void initProperties()
    {
        try
        {
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor prop : props)
            {
                Method write = prop.getWriteMethod();
                if (write != null)
                {
                    Class<?> type = prop.getPropertyType();
                    String propName = prop.getName();
                    Object value = get(propName.toUpperCase());
                    if (value == null)
                    {
                        String regex = "[a-z][A-Z]";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(propName);
                        StringBuilder name = new StringBuilder(propName);
                        while (m.find())
                        {
                            int index = name.indexOf(m.group());
                            name.insert(index+1, "_");
                        }
                        value = get(name.toString().toUpperCase());
                    }
                    if (value != null)
                    {
                        if (Enum.class.isAssignableFrom(type))
                        {
                            Object[] conts = type.getEnumConstants();
                            for (Object cont : conts)
                            {
                                Enum<?> cons = (Enum<?>) cont;
                                if (cons.name().equals(value.toString()))
                                {
                                    write.invoke(this, cont);
                                    break;
                                }
                            }
                        }
                        else if (Date.class.isAssignableFrom(type))
                        {
                            write.invoke(this, DateUtils.convert(value.toString()));
                        }
                        else if (BigDecimal.class.isAssignableFrom(type))
                        {
                            write.invoke(this, new BigDecimal(value.toString()));
                        }
                        else if (Integer.class.isAssignableFrom(type))
                        {
                            write.invoke(this, Integer.valueOf(value.toString()));
                        }
                        else
                        {
                            write.invoke(this, value);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
