package com.mobanker.biz;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mobanker.util.DateUtils;

/**
 * <b>Package Name:</b> com.mobanker.biz <br/>
 * <b>Description:</b>〈Excel内容读取管理类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月25日
 * @version v1.0.0
 */
public class ExcelReaderManager
{
    private final Logger logger = Logger.getLogger(getClass());
    
    /**
     * <b>Description:</b>〈读取Excel文件中的内容并作为一个集合返回其中的内容〉<br/>
     * @param file 读取的Excel(xlsx)格式的文件
     * @param ignoreRows 忽略到第几行
     * @return List<String[]>
     * @author hongchaoMao <br/>
     * Create date: 2016年2月25日
     */
    public List<String[]> getXlsxData(File file, int ignoreRows)
    {
        List<String[]> result = new ArrayList<String[]>();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file)))
        {
            XSSFWorkbook xwb = new XSSFWorkbook(in);
            for (int sheetIndex = 0; sheetIndex < xwb.getNumberOfSheets(); sheetIndex++)
            {
                XSSFSheet xst = xwb.getSheetAt(sheetIndex);
                // 忽略行不取
                for (int rowIndex = ignoreRows; rowIndex <= xst.getLastRowNum(); rowIndex++)
                {
                    XSSFRow row = xst.getRow(rowIndex);
                    if (row == null)
                        continue;
                    // row 和 cell 都是从0开始
                    int tempCellSize = row.getLastCellNum() + 1;
                    String[] values = new String[tempCellSize];
                    Arrays.fill(values, "");
                    boolean hasValue = false;
                    for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++)
                    {
                        XSSFCell cell = row.getCell(columnIndex);
                        String value = "";
                        if (cell != null)
                        {
                            switch (cell.getCellType())
                            {
                            case XSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell))
                                {
                                    Date date = cell.getDateCellValue();
                                    if (date != null)
                                        value = DateUtils.convert(date);
                                    else
                                        value = "";
                                }
                                else
                                {
                                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                                }
                                break;
                            case XSSFCell.CELL_TYPE_FORMULA: // 导入时如果为公式生成的数据则无值
                                if (!cell.getStringCellValue().equals(""))
                                    value = cell.getStringCellValue();
                                else
                                    value = cell.getNumericCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                value = "";
                                break;
                            case XSSFCell.CELL_TYPE_BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "是" : "否");
                                break;
                            default:
                                value = "";
                            }
                            values[columnIndex] = value.replaceAll("[\"|\'|\\s]", "");
                            hasValue = true;
                        }
                    }
                    if (hasValue)
                        result.add(values);
                }
            }
        }
        catch (Exception e)
        {
            StackTraceElement[] stes = e.getStackTrace();
            for (StackTraceElement ste : stes)
            {
                logger.error(ste.toString());
            }
        }
        return result;
    }

    /**
     * <b>Description:</b>〈读取Excel文件中的内容并作为一个集合返回其中的内容〉<br/>
     * @param file 读取的Excel(xls)格式的文件
     * @param ignoreRows 忽略到第几行
     * @return List<String[]>
     * @author hongchaoMao <br/>
     * Create date: 2016年2月25日
     */
    public List<String[]> getXlsData(File file, int ignoreRows)
    {
        List<String[]> result = new ArrayList<String[]>();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file)))
        {
            POIFSFileSystem fs = new POIFSFileSystem(in);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFCell cell = null;
            for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++)
            {
                HSSFSheet st = wb.getSheetAt(sheetIndex);
                // 忽略行不取
                for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++)
                {
                    HSSFRow row = st.getRow(rowIndex);
                    if (row == null)
                        continue;
                    int cellSize = row.getLastCellNum() + 1;
                    String[] values = new String[cellSize];
                    Arrays.fill(values, "");
                    boolean hasValue = false;
                    for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++)
                    {
                        String value = "";
                        cell = row.getCell(columnIndex);
                        if (cell != null)
                        {
                            switch (cell.getCellType())
                            {
                            case HSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell))
                                {
                                    Date date = cell.getDateCellValue();
                                    if (date != null)
                                        value = DateUtils.convert(date);
                                    else
                                        value = "";
                                }
                                else
                                {
                                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                                }
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA: // 导入时如果为公式生成的数据则无值
                                if (!cell.getStringCellValue().equals(""))
                                {
                                    value = cell.getStringCellValue();
                                }
                                else
                                {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                value = "";
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "是" : "否");
                                break;
                            default:
                                value = "";
                            }
                        }
                        values[columnIndex] = value.replaceAll("[\"|\'|\\s]", "");
                        hasValue = true;
                    }
                    if (hasValue)
                        result.add(values);
                }
            }
        }
        catch (Exception e)
        {
            StackTraceElement[] stes = e.getStackTrace();
            for (StackTraceElement ste : stes)
            {
                logger.error(ste.toString());
            }
        }
        return result;
    }

    /**
     * <b>Description:</b>〈去掉字符串右边的空格〉<br/>
     * @param str 要处理的字符串
     * @return 处理后的字符串
     * @author hongchaoMao <br/>
     * Create date: 2016年2月25日
     */
    public String rightTrim(String str)
    {
        if (str == null)
        {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--)
        {
            if (str.charAt(i) != 0x20)
            {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }
}
