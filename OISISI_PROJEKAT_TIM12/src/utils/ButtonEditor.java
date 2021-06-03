package utils;

import aplikacija.Singleton;
import kontroler.PredstavaKontroler;
import model.Predstava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private int tblRowBtnClicked;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        tblRowBtnClicked = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            Predstava predstava = Singleton.getInstance().getPrikazanePredstave().get(new Long(tblRowBtnClicked));
            PredstavaKontroler predstavaKontroler = Singleton.getInstance().getPredstavaKontroler();
            predstavaKontroler.prikaziDetaljno(predstava);
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}