package com.mobanker.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.mobanker.enums.AgreementType;

/**
 * <b>Package Name:</b> com.mobanker.tkj.cw.borrow.util <br/>
 * <b>Description:</b>〈将HTML文件生成为PDF文件〉<br/>
 * @author hongchaoMao <br/>
 */
public class PDFGenerator
{

    private static final Logger                    logger    = Logger.getLogger(PDFGenerator.class);

    private static final Map<String, StringBuffer> TMPL_MAP  = new HashMap<String, StringBuffer>();

    private static final String[]                  TMPL_PATH = new String[] {
            "tmpl/shoujidai_borrow_tmpl.html", "tmpl/uzone_borrow_tmpl.html",
            "tmpl/shoujidai_withholding_tmpl.html", "tmpl/uzone_withholding_tmpl.html" };

    private static final String                    TEMP_PATH = System.getProperty("java.io.tmpdir")
                                                                     + "/BorAgreementFile/";

    /**
     * <b>Description:</b>〈初始化加载手机贷和U族HTML模版文件〉<br/>
     * @author hongchaoMao <br/>
     * Create date: 2015年12月14日
     */
    private static void initLoadTmpl()
    {
        if (TMPL_MAP.isEmpty())
        {
            synchronized (PDFGenerator.class)
            {
                if (TMPL_MAP.isEmpty())
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
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("------>initLoadTmpl() error: {}", e);
                        }
                        TMPL_MAP.put(path.substring(path.indexOf("/") + 1, path.lastIndexOf("_")).toUpperCase(), sb);
                    }
                }
            }
        }
    }

    /**
     * <b>Description:</b>〈根据模版内容生成PDF文件〉<br/>
     * @param params 要替换模版中的变量值
     * @param agreementType PDF协议类型
     * @param pdfName 生成的PDF文件路径
     * @return PDF文档file对象
     */
    public static File generatePDF(Map<String, String> params, AgreementType agreementType, String pdfCanonicalName)
    {
        initLoadTmpl();
        // 将模版内容生成HTML源文件
        String htmlSourceDoc = getHtmlSourceDoc(params, TMPL_MAP.get(agreementType.name()).toString());
        // 构建HTML文件
        File htmlFile = generateHtml(htmlSourceDoc);
        // 构建PDF文件
        File pdfFile = new File(pdfCanonicalName);
        pdfFile.getParentFile().mkdirs();
        try
        {
            if (!pdfFile.exists())
            {
                pdfFile.createNewFile();
            }
        }
        catch (Exception e)
        {
            logger.error("------>generatePDF() error:{}", e);
            throw new RuntimeException("创建pdf文件异常!", e);
        }
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = null;
        document.setPageSize(PageSize.A4);
        try
        {
            writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            // step 3
            document.open();
            // step 4
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(htmlFile), null,
                    Charset.forName("GBK"));
            logger.info("------>generatePDF() pdf 创建成功! file:" + pdfCanonicalName);
        }
        catch (DocumentException e)
        {
            logger.error("------>generatePDF() html转pdf报错：DocumentException" + e);
        }
        catch (IOException e)
        {
            logger.error("------>generatePDF() html转pdf报错：IOException" + e);
        }
        catch (Exception e)
        {
            logger.error("------>generatePDF() html转pdf报错：Exception" + e);
        }
        finally
        {
            document.close();
            if (writer != null)
            {
                writer.close();
            }
            htmlFile.delete();
        }
        return pdfFile;
    }

    /**
     * <b>Description:</b>〈方法详细描述〉<br/>
     * @param params 要替换文档模版中的参数 
     * @param doc 文档模版内容
     * @return 替换后的文档模版
     * @author hongchaoMao <br/>
     * Create date: 2015年11月17日
     */
    private static String getHtmlSourceDoc(Map<String, String> params, String doc)
    {
        StringBuilder sb = new StringBuilder(doc);
        Iterator<Entry<String, String>> its = params.entrySet().iterator();
        try
        {
            while (its.hasNext())
            {
                Entry<String, String> entry = its.next();
                String value = entry.getValue() == null ? "" : entry.getValue();
                int index = 0;
                while ((index = sb.indexOf("#{" + entry.getKey() + "}")) != -1)
                {
                    sb.replace(index, index + entry.getKey().length() + 3, value);
                }
            }
            return sb.toString();
        }
        catch (Exception e)
        {
            logger.error("------>getHtmlSourceDoc() error：", e);
        }
        return "";
    }

    /**
     * 创建html文件 （doc传入参数需要带）
     * @param content
     */
    private static File generateHtml(String sourceDoc)
    {
        String str = new String(); // 原有txt内容
        StringBuilder s1 = new StringBuilder();// 内容更新
        String filePath = TEMP_PATH + System.currentTimeMillis() + "" + (int) (Math.random() * 10000) + ".html";
        File htmlFile = new File(filePath);
        BufferedReader input = null;
        BufferedWriter output = null;
        try
        {
            htmlFile.getParentFile().mkdirs();
            if (!htmlFile.exists())
            {
                htmlFile.createNewFile();// 不存在则创建
            }
            input = new BufferedReader(new FileReader(htmlFile));
            while ((str = input.readLine()) != null)
            {
                s1.append(str + "\n");
            }
            s1.append(sourceDoc);
            // 以指定GBK编码写入HTML文件中
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "GBK"));
            output.write(s1.toString());
            output.flush();
        }
        catch (Exception e)
        {
            htmlFile = null;
            logger.error("创建html报错：" + e);
        }
        finally
        {
            try
            {
                if (input != null)
                {
                    input.close();
                }
                if (output != null)
                {
                    output.close();
                }
            }
            catch (IOException e)
            {
                logger.error("System error:" + e.getMessage());
            }
        }
        return htmlFile;
    }

}
