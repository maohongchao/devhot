package com.mobanker.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import lombok.Setter;

import org.apache.commons.lang.StringUtils;

/**
 * <b>Package Name:</b> com.mobanker.ui.listener <br/>
 * <b>Description:</b>〈理财人指定/随机选择监听事件〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年3月2日
 * @version v1.0.0
 */
@Setter
public class RadioChooseActionListener implements ActionListener
{
    private JComboBox<String> tenderCbx       = null;
    private JTextField        tenderIDText    = null;
    private JCheckBox         tenderPhoneCbox = null;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (StringUtils.equals("manualAction", e.getActionCommand()))
        {
            tenderCbx.setEditable(true);
            tenderCbx.setEnabled(true);
            tenderIDText.setEditable(true);
        }
        else if (StringUtils.equals("randomAction", e.getActionCommand()))
        {
            tenderCbx.setEditable(false);
            tenderCbx.setEnabled(false);
            tenderIDText.setEditable(false);
            if (tenderPhoneCbox.isSelected())
            {
                tenderPhoneCbox.doClick();
            }
        }
    }

}
