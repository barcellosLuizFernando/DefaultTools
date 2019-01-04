/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Component;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferna
 */
public class ClearFields {
    
    public static void ClearFields(JInternalFrame frm) {
        
        for (Component c : frm.getContentPane().getComponents()) {
            //System.out.println("Varrendo InternalFrame: " + c.getClass());
            CleanComponents(c);
        }
        
    }
    
    public static void ClearFields(JFrame frm) {
        
        for (Component c : frm.getContentPane().getComponents()) {
            //System.out.println("Varrendo Frame: " + c.getClass());
            CleanComponents(c);
        }
        
    }
    
    public static void ClearFields(JPanel pnl){
        for (Component c : pnl.getComponents()) {
            //System.out.println("Varrendo Frame: " + c.getClass());
            CleanComponents(c);
        }
    }
    
    private static void CleanComponents(Component c) {
        //System.out.println("Varrendo componente: " + c.getClass());
        if (c instanceof JPanel) {
            JPanel pnl = (JPanel) c;
            CleanJPanel(pnl);
        } else if (c instanceof JTextField) {
            JTextField field = (JTextField) c;
            CleanJTextField(field);
        } else if (c instanceof JTabbedPane) {
            JTabbedPane field = (JTabbedPane) c;
            CleanJTabbedPane(field);
        } else if (c instanceof JScrollPane) {
            JScrollPane field = (JScrollPane) c;
            CleanJScrollPane(field);
        } else if (c instanceof JTable) {
            JTable field = (JTable) c;
            CleanTable(field);

            /**
             * SE FOR UM JSPINNER. VERIFICA SE É FORMATO DATA OU NÚMERO. AINDA
             * NÃO TRATA OUTROS FORMATOS.
             */
        } else if (c instanceof JSpinner) {
            JSpinner spinner = (JSpinner) c;
            CleanJSpinner(spinner);
            
        } else if (c instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) c;
            CleanTextFormatted(field);
            
        } else if (c instanceof JTable) {
            JTable field = (JTable) c;
            CleanTable(field);
            
        } else if (c instanceof JScrollPane) {
            JScrollPane field = (JScrollPane) c;
            CleanJScrollPane(field);
        } else if (c instanceof JLabel) {
            // SEM AÇÃO
        } else if (c instanceof JButton) {
            // SEM AÇÃO
        } else if (c instanceof JCheckBox) {
            // SEM AÇÃO
        } else if (c instanceof JComboBox) {
            // SEM AÇÃO
        } else if (c instanceof JViewport) {
            JViewport field = (JViewport) c;
            CleanJViewport(field);
        } else if (c instanceof JTextArea) {
            JTextArea field = (JTextArea) c;
            CleanJTextArea(field);
        } else if (c instanceof JTextPane) {
            JTextPane field = (JTextPane) c;
            CleanJTextPane(field);
            
        } else {
            System.out.println("CLEAR_FIELDS - Configurar componente: " + c.getClass());
        }
    }
    
    private static void CleanJScrollPane(JScrollPane spnl) {
        for (Component c : spnl.getComponents()) {
            //System.out.println("Varrendo JScrollPane: " + c.getClass());
            CleanComponents(c);
        }
    }
    
    private static void CleanJTabbedPane(JTabbedPane tpnl) {
        
        for (Component c : tpnl.getComponents()) {
            //System.out.println("Varrendo JTabbedPane: " + c.getClass());
            CleanComponents(c);
        }
    }

    /**
     * Limpa um único TextField por vez.
     *
     * @param field
     */
    private static void CleanJTextField(JTextField field) {
        field.setText("");
    }

    /**
     * Recebe um painel e limpa todos os Fields Existentes
     *
     * @param pnl
     */
    private static void CleanJPanel(JPanel pnl) {
        
        try {
            for (Component x : pnl.getComponents()) {
                //System.out.println("Varrendo TextFields: " + x.getClass());

                CleanComponents(x);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Erro ao zerar campos", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private static void CleanTextFormatted(JFormattedTextField field) {
        field.setValue(null);
    }
    
    private static void CleanTable(JTable field) {
        DefaultTableModel lista = (DefaultTableModel) field.getModel();
        lista.setRowCount(0);
    }
    
    private static void CleanJSpinner(JSpinner spinner) {
        SpinnerModel dateModel = spinner.getModel();
        
        if (dateModel instanceof SpinnerDateModel) {
            spinner.setValue(new Date());
        } else if (dateModel instanceof SpinnerNumberModel) {
            spinner.setValue(0);
        }
    }
    
    private static void CleanJViewport(JViewport field) {
        for (Component c : field.getComponents()) {
            CleanComponents(c);
        }
    }
    
    private static void CleanJTextArea(JTextArea field) {
        field.setText("");
    }
    
    private static void CleanJTextPane(JTextPane field) {
        field.setText("");
    }
    
}
