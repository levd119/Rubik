/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;



/**
 *
 * @author Idansa
 */
public class HelpDialog extends javax.swing.JDialog {

    /**
     * Creates new form HelpDialog
     * @param parent
     */
    public HelpDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        int x = (d.width - this.getWidth()) / 2;
        int y = (d.height - this.getHeight()) / 2;
        this.setLocation(x, y);

    }



    
    

    private void initComponents() {

        exitHelp_btn = new javax.swing.JButton();
        helpLable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Help");
        setAlwaysOnTop(true);
        setUndecorated(true);
        getContentPane().setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        ((JPanel)getContentPane()).setOpaque(false);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        exitHelp_btn.setBorderPainted(false);
        exitHelp_btn.setContentAreaFilled(false);
        exitHelp_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitHelp_btn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitHelp_btnActionPerformed(evt);
            }
        });
        getContentPane().add(exitHelp_btn);

        helpLable.setMaximumSize(new java.awt.Dimension(350, 400));
        if (this.getParent() instanceof MainScreen) {
            exitHelp_btn.setBounds(435, 22, 60, 60);
            setPreferredSize(new java.awt.Dimension(520, 600));
            helpLable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/HelpMenuResized.png"))); // NOI18N
            helpLable.setBounds(0, 0, 520, 600);
            helpLable.setOpaque(false);
        } else {
            exitHelp_btn.setBounds(450, 20, 70, 70);

            setPreferredSize(new java.awt.Dimension(540, 540));
            helpLable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/CubeMenu.png"))); // NOI18N
            helpLable.setBounds(0, 0, 540, 508);
        }
        getContentPane().add(helpLable);

        pack();
    }// </editor-fold>                        

    private void exitHelp_btnActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();

    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton exitHelp_btn;
    private javax.swing.JLabel helpLable;
    // End of variables declaration    
        
        
    
    
}
