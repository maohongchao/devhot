package com.mobanker.jfinal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * <b>Package Name:</b> com.mobanker.jfinal <br/>
 * <b>Description:</b>〈类型详细描述〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月25日
 * @version v1.0.0
 */
public class MainTest
{
    private static final String[]                  TMPL_PATH = new String[] {"tmpl/uzone_borrow_tmpl.html"};
    
    public static void main(String[] args)
    {
      
    }

    public static void cacheTmpl()
    {
        for (int i = 0; i < TMPL_PATH.length; i++)
        {
            String path = TMPL_PATH[i];
            StringBuffer sb = new StringBuffer();
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8")))
            {
                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + System.getProperty("line.separator"));
                    System.out.println(line);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
