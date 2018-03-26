
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author lev
 */
public class GameMode extends javax.swing.JDialog {

    /**
     * Creates new form GameMode
     */
    JFrame context;

    public GameMode(JFrame frame) {
        super(frame,true);
        initComponents();

        getRootPane().setOpaque(false);
        getContentPane().setBackground(new Color(0, 0, 0, 0));
        setBackground(new Color(0, 0, 0, 0));
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension d = toolkit.getScreenSize();
        int x = (d.width - this.getWidth()) / 2;
        int y = (d.height - this.getHeight()) / 2;
        this.setLocation(x, y);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tutorialBTN = new javax.swing.JButton();
        singleBTN = new javax.swing.JButton();
        multiBTN = new javax.swing.JButton();
        exitGameMode = new javax.swing.JButton();
        gameModeBg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(null);
        setMinimumSize(new java.awt.Dimension(520, 620));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        tutorialBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/practiceResize.png"))); // NOI18N
        tutorialBTN.setBorderPainted(false);
        tutorialBTN.setContentAreaFilled(false);
        tutorialBTN.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/practiceResizeRollOver.png"))); // NOI18N
        tutorialBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutorialBTNActionPerformed(evt);
            }
        });
        getContentPane().add(tutorialBTN);
        tutorialBTN.setBounds(90, 400, 290, 90);

        singleBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/singleResized.png"))); // NOI18N
        singleBTN.setContentAreaFilled(false);
        singleBTN.setPreferredSize(new java.awt.Dimension(290, 90));
        singleBTN.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/singleResizedRollOver.png"))); // NOI18N
        singleBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singleBTNActionPerformed(evt);
            }
        });
        getContentPane().add(singleBTN);
        singleBTN.setBounds(90, 300, 290, 90);

        multiBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/onlineMatcResized.png"))); // NOI18N
        multiBTN.setBorderPainted(false);
        multiBTN.setContentAreaFilled(false);
        multiBTN.setPreferredSize(new java.awt.Dimension(350, 150));
        multiBTN.setRequestFocusEnabled(false);
        multiBTN.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/onlineMatchRollOverResized.png"))); // NOI18N
        multiBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiBTNActionPerformed(evt);
            }
        });
        getContentPane().add(multiBTN);
        multiBTN.setBounds(90, 200, 290, 90);

        exitGameMode.setBorderPainted(false);
        exitGameMode.setContentAreaFilled(false);
        exitGameMode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitGameMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitGameModeActionPerformed(evt);
            }
        });
        getContentPane().add(exitGameMode);
        exitGameMode.setBounds(443, 23, 60, 50);

        gameModeBg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/gameModeResize.png"))); // NOI18N
        gameModeBg.setText("hfjgfjh");
        gameModeBg.setMaximumSize(new java.awt.Dimension(600, 691));
        gameModeBg.setMinimumSize(new java.awt.Dimension(600, 691));
        gameModeBg.setName(""); // NOI18N
        gameModeBg.setPreferredSize(new java.awt.Dimension(520, 600));
        gameModeBg.setRequestFocusEnabled(false);
        getContentPane().add(gameModeBg);
        gameModeBg.setBounds(0, 0, 520, 600);
        gameModeBg.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void singleBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singleBTNActionPerformed
        // TODO add your handling code here:
        CubeFrame frame = new CubeFrame(false);
        frame.startThread();
        frame.setVisible(true);
        dispose();
        ((JFrame)getParent()).dispose();


    }//GEN-LAST:event_singleBTNActionPerformed

    private void multiBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiBTNActionPerformed
        // TODO add your handling code here:
        new SearchForGameFrame().setVisible(true);
        this.dispose();
        ((JFrame)getParent()).dispose();


    }//GEN-LAST:event_multiBTNActionPerformed

    private void tutorialBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorialBTNActionPerformed
        // TODO add your handling code here:
        FaceColorsSelectScreen frame = new FaceColorsSelectScreen();
        frame.setVisible(true);
        frame.startThread();
        dispose();
        ((JFrame)getParent()).dispose();


    }//GEN-LAST:event_tutorialBTNActionPerformed

    private void exitGameModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitGameModeActionPerformed
        dispose();
    }//GEN-LAST:event_exitGameModeActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitGameMode;
    private javax.swing.JLabel gameModeBg;
    private javax.swing.JButton multiBTN;
    private javax.swing.JButton singleBTN;
    private javax.swing.JButton tutorialBTN;
    // End of variables declaration//GEN-END:variables
}
