/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author ferna
 */
public class JNumberField extends JTextField {

    public JNumberField() {
        this(null);
    }

    public JNumberField(String text) {
        super(text);
        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                //normalmente apenas uma letra é inserida por vez,
                //mas fazendo assim também previne caaso o usuário
                //cole algum texto
                for (int i = 0; i < str.length(); i++) {
                    if (Character.isDigit(str.charAt(i)) == false) {
                        return;
                    }
                }
                super.insertString(offs, str, a);
            }
        });
    }
}
