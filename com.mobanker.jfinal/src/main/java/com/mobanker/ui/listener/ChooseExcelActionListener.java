package com.mobanker.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;

import com.mobanker.biz.ExcelReaderManager;

/**
 * <b>Package Name:</b> com.mobanker.ui.listener <br/>
 * <b>Description:</b>〈选择要加载的Excel文件的按钮的监听类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月25日
 * @version v1.0.0
 */
public class ChooseExcelActionListener implements ActionListener
{
    private final Logger       logger             = Logger.getLogger(getClass());
    private ExcelReaderManager excelReaderManager = new ExcelReaderManager();
    private JFileChooser       fc                 = new JFileChooser();

    private JFrame             parentFrame        = null;
    private JTextField         pathText           = null;
    private JTextArea          borrowNidTextArea  = null;

    public ChooseExcelActionListener(JFrame parentFrame, JTextField pathText, JTextArea borrowNidTextArea)
    {
        this.parentFrame = parentFrame;
        this.pathText = pathText;
        this.borrowNidTextArea = borrowNidTextArea;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        fc.addChoosableFileFilter(new ExcelFileFilter());
        int returnVal = fc.showOpenDialog(parentFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File file = fc.getSelectedFile();
                pathText.setText(file.getCanonicalPath());
                List<String[]> result = new ArrayList<String[]>();
                String path = file.getCanonicalPath();
                String sufix = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
                if ("xls".equals(sufix))
                {
                    result = excelReaderManager.getXlsData(file, 0);
                }
                else if ("xlsx".equals(sufix))
                {
                    result = excelReaderManager.getXlsxData(file, 0);
                }
                StringBuilder sb = new StringBuilder();
                for (String[] row : result)
                {
                    sb.append(row[0] + "\r\n");
                }
                borrowNidTextArea.setText(sb.toString());
            }
            catch (Exception e2)
            {
                StackTraceElement[] stes = e2.getStackTrace();
                for (StackTraceElement ste : stes)
                {
                    logger.error(ste.toString());
                }
            }
        }
    }

    /**
     * <b>Package Name:</b> com.mobanker.ui.listener <br/>
     * <b>Description:</b>〈Excel文件过滤器类〉<br/>
     * @author hongchaoMao <br/>
     * Create date: 2016年2月25日
     * @version v1.0.0
     */
    class ExcelFileFilter extends FileFilter
    {
        @Override
        public boolean accept(File f)
        {
            try
            {
                String path = f.getCanonicalPath();
                String sufix = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
                if ("xls".equals(sufix) || "xlsx".equals(sufix))
                {
                    return true;
                }
            }
            catch (IOException e)
            {
                StackTraceElement[] stes = e.getStackTrace();
                for (StackTraceElement ste : stes)
                {
                    logger.error(ste.toString());
                }
            }

            return false;
        }
        
        @Override
        public String getDescription()
        {
            return "'.xls' and '.xlsx' Excel files !";
        }

    }

}
