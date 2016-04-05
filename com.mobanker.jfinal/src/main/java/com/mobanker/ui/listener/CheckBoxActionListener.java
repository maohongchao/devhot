package com.mobanker.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import lombok.Setter;

import org.apache.commons.lang.StringUtils;

/**
 * <b>Package Name:</b> com.mobanker.ui.listener <br/>
 * <b>Description:</b>〈多选框选择监听事件类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年3月2日
 * @version v1.0.0
 */
@Setter
public class CheckBoxActionListener implements ActionListener
{
    private JTextField tenderPhoneText = null;
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (StringUtils.equals("enablePhoneText", e.getActionCommand()))
        {
            if (e.getSource() instanceof JCheckBox)
            {
                JCheckBox cbx = (JCheckBox)e.getSource();
                tenderPhoneText.setEditable(cbx.isSelected());
            }
        }
    }

}
