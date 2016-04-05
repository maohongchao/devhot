package com.mobanker.ui.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JProgressBar;

/**
 * <b>Package Name:</b> com.mobanker.ui.listener <br/>
 * <b>Description:</b>〈批量生成PDF借款协议进度条属性改变监听器类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月26日
 * @version v1.0.0
 */
public class ProgressProChangeListener implements PropertyChangeListener
{
    private JProgressBar progressBar = null;

    public ProgressProChangeListener(JProgressBar progressBar)
    {
        this.progressBar = progressBar;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if ("progress" == evt.getPropertyName())
        {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
        }
    }
}
