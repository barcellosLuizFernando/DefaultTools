/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author luiz.barcellos
 */
public class MascaraTextField {
    
    public static MaskFormatter phoneNumber(){
        MaskFormatter data = null;
        
        try {
            data = new javax.swing.text.MaskFormatter("(##) ####-####");
            data.setValueContainsLiteralCharacters(false);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        return data;
    }

    public static MaskFormatter dataBR() {

        MaskFormatter data = null;

        try {
            data = new javax.swing.text.MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return data;
    }

    public static MaskFormatter competenceBR() {

        MaskFormatter data = null;

        try {
            data = new javax.swing.text.MaskFormatter("##/####");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return data;
    }

    public static MaskFormatter dataUS() {

        MaskFormatter data = null;

        try {
            data = new javax.swing.text.MaskFormatter("####/##/##");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return data;
    }

    public static DateFormat dateBR() {
        DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");

        return dateIn;
    }

    public static DateFormat dateUS() {
        DateFormat dateOut = new SimpleDateFormat("yyyy/MM/yy");

        return dateOut;
    }

    public static DecimalFormat numeroBR() {
        DecimalFormat df = new DecimalFormat("#,##0.00");

        return df;
    }

    public static DecimalFormat numeroUS() {
        DecimalFormat df = new DecimalFormat("#.##0,00");

        return df;
    }

}
