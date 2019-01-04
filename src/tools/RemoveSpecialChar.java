/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.Normalizer;
import javax.swing.JTextField;

/**
 *
 * @author luiz.barcellos
 */
public class RemoveSpecialChar {

    public static String removeSpecialChar(String x) {

        String text = x;
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        return text.replaceAll("[^\\p{ASCII}]", "");

    }

    public static void removeChars(JTextField textField) {

        textField.setText(textField.getText().replaceAll("[^0-9]", ""));

    }

}
