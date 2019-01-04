/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author luiz.barcellos
 */
public class AcaoPadrao {

    /**
     * VERIFICA BOTAO E LOCAL ONDE FOI PRESSIONADO. SE FOR O BOTAO ENTER, PASSA
     * PARA O PRÓXIMO CAMPO.
     *
     * @param evt
     * @param obCampo
     * @param obTela
     */
    public static void acaoPadraoTeclado(KeyEvent evt, Object obCampo, Object obTela) {

        System.out.println("Classe campo: " + obCampo.getClass());
        Component comp = null;

        if (obCampo.getClass().equals(JTextField.class)) {
            JTextField x = (JTextField) obCampo;
            comp = x.getNextFocusableComponent();
        } else if (obCampo.getClass().equals(JCheckBox.class)) {
            JCheckBox x = (JCheckBox) obCampo;
            comp = x.getNextFocusableComponent();
        } else if (obCampo.getClass().equals(JRadioButton.class)) {
            JRadioButton x = (JRadioButton) obCampo;
            comp = x.getNextFocusableComponent();
        } else if (obCampo.getClass().equals(JButton.class)) {
            JButton x = (JButton) obCampo;
            comp = x.getNextFocusableComponent();
        } else if (obCampo.getClass().equals(JFormattedTextField.class)) {
            JFormattedTextField x = (JFormattedTextField) obCampo;
            comp = x.getNextFocusableComponent();
        } else if (obCampo.getClass().equals(JTable.class)) {
            JTable x = (JTable) obCampo;
            comp = x.getNextFocusableComponent();
        } else if (obCampo.getClass().equals(tools.JNumberFormatField99.class)) {
            tools.JNumberFormatField99 x = (JNumberFormatField99) obCampo;
            comp = x.getNextFocusableComponent();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER && comp != null) {

            comp.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            if (obTela.getClass().getSuperclass().equals(JInternalFrame.class)) {
                JInternalFrame x = (JInternalFrame) obTela;
                x.dispose();
            } else if (obTela.getClass().getSuperclass().equals(JFrame.class)) {
                JFrame x = (JFrame) obTela;
                x.dispose();
            }
        }

    }

    /**
     * SELECIONA VÁRIAS LINHAS APENAS CLICANDO
     *
     * @param tbl
     * @param linha
     */
    public static void acaoPadraoTabela(JTable tbl, int linha) {

    }

    /**
     * TESTAR, ZERA O CAMPO NOME QUANDO A PESQUISA RETORNA FALSO.
     *
     * @param lastId
     * @param id
     * @param name
     * @return
     */
    public static boolean acaoPadraoFocusLost(String lastId, JTextField id, JTextField name) {
        if (id.getText().equals("")) {
            name.setText("");
            return false;
        } else if (!id.getText().equals(lastId) && !"".equals(id.getText())) {

            if ("".equals(name.getText())) {

                id.setText("");
                return false;

            } else {
                return false;
            }
        } else {

            return true;
        }

    }

    /**
     * ENCONTRA O PRÓXIMO CÓDIGO, QUANDO JÁ HÁ UM NÚMERO INFORMADO. SETA O
     * CÓDIGO PARA "01" QUANDO NÃO HÁ CÓDIGO INFORMADO.
     *
     * @param textField
     * @param evt
     * @return
     */
    public static Boolean acaoPadraoCodigoSequencial(JTextField textField, KeyEvent evt) {
        System.out.println("Campo: " + textField.getSelectedText() + " - " + textField.getClass());
        System.out.println("Conteúdo: " + textField.getText());
        System.out.println("Tecla: " + evt.getKeyCode());

        int id = 0;
        String codigo = textField.getText();

        if (evt.getKeyCode() == KeyEvent.VK_UP
                | evt.getKeyCode() == KeyEvent.VK_DOWN) {

            try {

                id = Integer.parseInt(textField.getText());

                if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    id++;
                } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    id--;
                    if (id < 1) {
                        textField.setText("2");
                        throw new UnsupportedOperationException();

                    }
                }

                textField.setText(id + "");

            } catch (UnsupportedOperationException e) {
                //NÃO REALIZA NENHUM TRATAMENTO.
                acaoPadraoCodigoSequencial(textField, evt);
            } catch (Exception e) {
                //tools.ClearFields.ClearFields(this);
                textField.setText("1");
                //acaoPadraoCodigoSequencial(textField, evt);
            }
            return true;
        } else {
            return false;
        }
    }

    public static void acaoPadraoResultSet(ResultSet rs, JTextField textField) throws SQLException {

        textField.setText("");
        while (rs.next()) {
            textField.setText(rs.getString(1));
        }

    }

}
