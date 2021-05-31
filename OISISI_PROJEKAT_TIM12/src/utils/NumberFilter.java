package utils;

import aplikacija.Singleton;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;


//        PlainDocument docCenaKarte = (PlainDocument) tfCenaKarte.getDocument();
//        docCenaKarte.setDocumentFilter(new NumberFilter());

public class NumberFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string,
                             AttributeSet attr) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);

        if (test(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        } else {
            JOptionPane.showMessageDialog(null, "Cena karte je obavezna i mora biti broj!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean test(String text) {
        try {
            Float.parseFloat(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
                        AttributeSet attrs) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        if (test(sb.toString())) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            JOptionPane.showMessageDialog(null, "Cena karte je obavezna i mora biti broj!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
        }

    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.delete(offset, offset + length);

        if (test(sb.toString())) {
            super.remove(fb, offset, length);
        } else {
            JOptionPane.showMessageDialog(null, "Cena karte je obavezna i mora biti broj!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
        }

    }
}
