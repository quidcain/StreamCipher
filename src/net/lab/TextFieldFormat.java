package net.lab;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.regex.Pattern;

/**
 * Created by stoat on 11/23/16.
 */

class TextFieldFormat extends PlainDocument {
    private int limit;
    private static Pattern pattern = Pattern.compile("[01]");
    public TextFieldFormat(int limit) {
        super();
        this.limit = limit;
    }
    @Override
    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit && pattern.matcher(str).matches()) {
            super.insertString(offset, str, attr);
        }
    }
}
