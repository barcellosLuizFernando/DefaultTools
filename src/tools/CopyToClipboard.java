/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author luiz.barcellos
 */
public class CopyToClipboard {

    public static void copyToClipboard(String x) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String text = x;
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }

}
