

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author lev&Rotem
 *
 */
public class MainScreen extends javax.swing.JFrame {

    private Animator animator;
    private GLCanvas canvas;
    private RendererSingle renderer;
    public JPanel canvasPanel;

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        int x = (d.width - this.getWidth()) / 2;
        int y = (d.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        tryInternet();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newGame_btn = new javax.swing.JButton();
        scores_btn = new javax.swing.JButton();
        help_btn = new javax.swing.JButton();
        exit_btn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 640));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(600, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(600, 600));
        getContentPane().setLayout(null);

        newGame_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/GreenN.png"))); // NOI18N
        newGame_btn.setBorderPainted(false);
        newGame_btn.setContentAreaFilled(false);
        newGame_btn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                newGame_btnStateChanged(evt);
            }
        });
        newGame_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGame_btnActionPerformed(evt);
            }
        });
        getContentPane().add(newGame_btn);
        newGame_btn.setBounds(190, 200, 220, 200);

        scores_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/YellowN.png"))); // NOI18N
        scores_btn.setText("scores");
        scores_btn.setBorderPainted(false);
        scores_btn.setContentAreaFilled(false);
        scores_btn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                scores_btnStateChanged(evt);
            }
        });
        scores_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scores_btnActionPerformed(evt);
            }
        });
        getContentPane().add(scores_btn);
        scores_btn.setBounds(0, 0, 210, 200);

        help_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/BlueN.png"))); // NOI18N
        help_btn.setText("Help");
        help_btn.setBorderPainted(false);
        help_btn.setContentAreaFilled(false);
        help_btn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                help_btnStateChanged(evt);
            }
        });
        help_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                help_btnActionPerformed(evt);
            }
        });
        getContentPane().add(help_btn);
        help_btn.setBounds(400, -10, 215, 220);

        exit_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/WhiteN.png"))); // NOI18N
        exit_btn.setText("Exit");
        exit_btn.setBorderPainted(false);
        exit_btn.setContentAreaFilled(false);
        exit_btn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                exit_btnStateChanged(evt);
            }
        });
        exit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_btnActionPerformed(evt);
            }
        });
        getContentPane().add(exit_btn);
        exit_btn.setBounds(0, 390, 210, 220);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GamePics/Background-Cube.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -10, 630, 620);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newGame_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGame_btnActionPerformed

        new GameMode(this).setVisible(true);
//        this.setVisible(false);
//        this.dispose();


    }//GEN-LAST:event_newGame_btnActionPerformed

    private void scores_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scores_btnActionPerformed
        new ScoreScreen(this).setVisible(true);
    }//GEN-LAST:event_scores_btnActionPerformed

    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_btnActionPerformed
        System.exit(0);

        //to add dialg are you sure you want to quit
    }//GEN-LAST:event_exit_btnActionPerformed

    private void help_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_help_btnActionPerformed
        HelpDialog d = new HelpDialog(this);
        
        d.setVisible(true);
    }//GEN-LAST:event_help_btnActionPerformed

    private void exit_btnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_exit_btnStateChanged

        JButton btn = (JButton) evt.getSource();
        btn.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/WhiteP.png")));

    }//GEN-LAST:event_exit_btnStateChanged

    private void newGame_btnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_newGame_btnStateChanged
        JButton btn = (JButton) evt.getSource();
        btn.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/GreenP.png")));

    }//GEN-LAST:event_newGame_btnStateChanged

    private void scores_btnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_scores_btnStateChanged
        JButton btn = (JButton) evt.getSource();
        btn.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/YellowP1.png")));
    }//GEN-LAST:event_scores_btnStateChanged

    private void help_btnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_help_btnStateChanged
        JButton btn = (JButton) evt.getSource();
        btn.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/BlueP.png")));
    }//GEN-LAST:event_help_btnStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    private boolean tryInternet() {
        File f;
        String name = "";
        Date time;
        int moves;
        int hints;
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        GregorianCalendar gk;
        FileReader fr;
        try {
            f = new File("C:/Users/USER/Documents/NetBeansProjects/TestJOGL90/src/testjogl/score.txt");
            fr = new FileReader(f);
            BufferedReader buffer = new BufferedReader(fr);
            ConnectionUtil cu = new ConnectionUtil();
            ConnectionUtil.openConnectionScore();

            while ((name = buffer.readLine()) != null) {
                time = df.parse(buffer.readLine());
                moves = Integer.parseInt(buffer.readLine());
                gk = new GregorianCalendar(0, 0, 0, time.getHours(), time.getMinutes(), time.getSeconds());
                 hints = Integer.parseInt(buffer.readLine());
                User u = new User(name, gk, moves,hints);
                cu.addUser(u);

            }
            ConnectionUtil.closeConnection();
            buffer.close();

            if (f.delete()) {
                System.out.println("file deleted");
            } else {
                System.out.println("not deleted");
            }
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            System.out.println("no internet");
        } catch (ParseException ex) {
            System.out.println("parse Exception");
        }
        return true;

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit_btn;
    private javax.swing.JButton help_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton newGame_btn;
    private javax.swing.JButton scores_btn;
    // End of variables declaration//GEN-END:variables
}
