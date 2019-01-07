/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author ferna
 */
public class JPopUpMenuDefault extends JFrame {

    private JPopupMenu pm;
    private JMenuItem mi_copy;
    private JMenuItem mi_paste;
    private JMenuItem mi_delete;

    public JPopUpMenuDefault(Object frm) {

        /**
         * Cria JPopUpMenu
         */
        pm = new JPopupMenu();

        mi_copy = new JMenuItem("Copiar");
        mi_paste = new JMenuItem("Colar");
        mi_delete = new JMenuItem("Excluir");

        pm.add(mi_copy);
        pm.add(mi_paste);
        pm.add(mi_delete);

        /**
         * Insere JPopUpMenu no parent
         */
        if (frm instanceof JFrame) {

            JFrame frame = (JFrame) frm;

            frame.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent me) {

                    if (MouseEvent.BUTTON3 == me.getButton()) {
                        pm.show(me.getComponent(), me.getX(), me.getY());
                    }
                }

            });

        } else if (frm instanceof JInternalFrame) {
            JInternalFrame frame = (JInternalFrame) frm;

            frame.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent me) {
                    pm.show(me.getComponent(), me.getX(), me.getY());
                }

            });
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPopUpMenuDefault p = new JPopUpMenuDefault(frame);
    }

}
