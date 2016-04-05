package com.mobanker.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

/**
 * <b>Package Name:</b> com.mobanker.ui.listener <br/>
 * <b>Description:</b>〈选择PDF文件保存路径按钮的事件监听类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月26日
 * @version v1.0.0
 */
public class ChooseSavePathActionListener implements ActionListener
{
    private final Logger logger      = Logger.getLogger(getClass());
    private JFileChooser fc          = new JFileChooser();

    private JFrame       parentFrame = null;
    private JTextField   pathText    = null;

    public ChooseSavePathActionListener(JFrame parentFrame, JTextField pathText)
    {
        this.parentFrame = parentFrame;
        this.pathText = pathText;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(parentFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File file = fc.getSelectedFile();
                pathText.setText(file.getCanonicalPath());
            }
            catch (IOException e2)
            {
                StackTraceElement[] stes = e2.getStackTrace();
                for (StackTraceElement ste : stes)
                {
                    logger.error(ste.toString());
                }
            }
        }
    }

}
