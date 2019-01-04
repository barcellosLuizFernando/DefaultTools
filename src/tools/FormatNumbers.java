/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author ferna
 */
public class FormatNumbers {

    public static String numUsToBr(Double x) {

        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");

        return df.format(x);

    }
    
    public static String numUsToBrNoDots(Double x) {

        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");

        return df.format(x).replace(".", "").replace(",", "");

    }

    public static String dateBrDefault(java.util.Date x) {

        java.text.DateFormat dateIn = new java.text.SimpleDateFormat("dd/MM/yyyy");

        return dateIn.format(x);

    }
    
    public static String dateUsDefault(java.util.Date x) {

        java.text.DateFormat dateOut = new java.text.SimpleDateFormat("yyyy/MM/dd");

        return dateOut.format(x);

    }

}
