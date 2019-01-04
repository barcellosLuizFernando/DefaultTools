/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class BarraProgresso extends JDialog {

    public static void main(String[] args) {
        new BarraProgresso("titulo teste");
    }

    private JProgressBar jProgressBar;
    private String titulo;

    public BarraProgresso(String titulo){
        this.titulo = titulo;
        initComponents();
    }
    
    public BarraProgresso() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initComponents();
            }
        });

        // operação demorada  
        /*try {  
            Thread.sleep(3000);  
        } catch (InterruptedException ex) {  
            Thread.currentThread().interrupt();  
        }  
          
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                //Principal pg = new Principal();  
                //JOptionPane.showMessageDialog(null, "Abrindo barra de progresso.");
//pg.setEnabled(true);    // setVisible(true); ??  
                dispose();  
            }  
        });*/
    }

    private void initComponents_old() {
        jProgressBar = new JProgressBar();
        jProgressBar.setIndeterminate(true);

        setTitle(titulo);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(jProgressBar);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>  

}
