/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class DefaultMsg {

    public static void saveDataSuccessfull() {
        JOptionPane.showMessageDialog(null, "Dados gravados com sucesso.", "Gravar dados", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void errorMsg(final String e) {
        JOptionPane.showMessageDialog(null, e, "Erro ao processar comando", JOptionPane.ERROR_MESSAGE);
    }

}
