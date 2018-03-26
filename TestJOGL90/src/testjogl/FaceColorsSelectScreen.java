/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author lev
 */
public class FaceColorsSelectScreen extends AbstractCubeFrame {

    private JButton restart;

    private JButton startGameBTN;
    private JButton QuitBtn;
    private final JButton btnTextures[] = new JButton[6];
    private JPanel colorSelect;
    private int color = 0;
    private final MyActionListener mol;
//    private Cursor selectColorCursor;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public FaceColorsSelectScreen() {
        super(WIDTH, HEIGTH);
        mol = new MyActionListener();

        editComponents();
    }

    @Override
    void setupJOGL() {
        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities caps = new GLCapabilities(profile);
        canvas = new GLJPanel(caps);
        ((GLJPanel) canvas).setLayout(new BorderLayout());

        renderer = new RendererSelectColors(this);
        ((RendererSelectColors) renderer).initCube();
        ((GLJPanel) canvas).addGLEventListener(renderer);
        ((GLJPanel) canvas).add(btnPanel, BorderLayout.SOUTH);
        ((GLJPanel) canvas).add(colorSelect, BorderLayout.WEST);

        ((GLJPanel) canvas).addMouseMotionListener(renderer);
        ((GLJPanel) canvas).addKeyListener(renderer);
        ((GLJPanel) canvas).addMouseListener(renderer);
        mainPanel.add(((GLJPanel) canvas), BorderLayout.CENTER);

        animator = new FPSAnimator(((GLJPanel) canvas), 60);
        animator.start();

        splashPanel.setVisible(false);
        ((GLJPanel) canvas).setFocusable(true);
        ((GLJPanel) canvas).requestFocusInWindow();

    }

    @Override
    final void editComponents() {
        super.editComponents();
        colorSelect = new JPanel();
        colorSelect.setLayout(new BoxLayout(colorSelect, BoxLayout.Y_AXIS));
        colorSelect.setPreferredSize(new Dimension(70, 370));
        colorSelect.setOpaque(false);

        for (int i = 0; i < btnTextures.length; i++) {

            btnTextures[i] = new JButton(new ImageIcon(getClass().getResource("/TextureMini/" + i + ".png")));
            btnTextures[i].setDisabledIcon(new ImageIcon(getClass().getResource("/TextureMini/Disabled/" + i + ".png")));

            btn_properties(btnTextures[i]);
            btnTextures[i].setPreferredSize(new Dimension(45, 45));
            btnTextures[i].addActionListener(mol);
            btnTextures[i].addChangeListener(mol);
            colorSelect.add(btnTextures[i]);

        }
        restart = new JButton(new ImageIcon(getClass().getResource("/GamePics/RestartN.png")));

        QuitBtn = new JButton(new ImageIcon(getClass().getResource("/GamePics/InGameQuit2.png")));

        QuitBtn.addActionListener(mol);
        QuitBtn.addChangeListener(mol);
        restart.addActionListener(mol);

        btn_properties(QuitBtn);
        btn_properties(restart);
        startGameBTN = new JButton(new ImageIcon(getClass().getResource("/GamePics/readyBtnResize.png")));
        btn_properties(startGameBTN);
        startGameBTN.setPreferredSize(new Dimension(75, 60));
        startGameBTN.addActionListener(mol);
        startGameBTN.addChangeListener(mol);
        restart.addChangeListener(mol);

        colorSelect.add(Box.createHorizontalGlue());
        btnPanel.add(startGameBTN);
        btnPanel.add(Box.createHorizontalGlue());
        btnPanel.add(restart);
        btnPanel.add(QuitBtn);
        btnTextures[0].setEnabled(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();

        x = (d.width - this.getWidth()) / 2;
        y = (d.height - this.getHeight()) / 2;
        this.setLocation(x, y);

    }

    private class MyActionListener implements ActionListener, ChangeListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == restart) {

//                startBlur();
                new RestartDialog(FaceColorsSelectScreen.this, true, renderer).setVisible(true);
//                stopBlur();
                
            }

            if (ae.getSource() == startGameBTN) {
                if (((RendererSelectColors) renderer).isValid()) {
                stopAnimator();
                CubeFrame frame = new CubeFrame(((RendererSelectColors) renderer).getCubeArray());
                frame.setVisible(true);
                frame.startThread();
                FaceColorsSelectScreen.this.setVisible(false);

                    System.out.println("Valid");
                } else {
                    System.out.println("not valid");
                    new NotValidDialog(FaceColorsSelectScreen.this, true).setVisible(true);
                }
            }
            if (ae.getSource() == QuitBtn) {

                startBlur();
                new QuitDialog(FaceColorsSelectScreen.this, true).setVisible(true);
                stopBlur();
            }
            for (int i = 0; i < btnTextures.length; i++) {
                if (ae.getSource() == btnTextures[i]) {
                    for (int j = 0; j < btnTextures.length; j++) {
                        if (!btnTextures[j].isEnabled()) {
                            btnTextures[j].setEnabled(true);
                        }
                    }
                    setColor(i);
                    btnTextures[i].setEnabled(false);

                }
            }
        }

        @Override
        public void stateChanged(ChangeEvent e) {

            for (int i = 0; i < btnTextures.length; i++) {
                if (e.getSource() == btnTextures[i]) {
                    btnTextures[i].setRolloverIcon(new ImageIcon(getClass().getResource("/TextureMini/RollOver/" + i + ".png")));
                }
            }
            if (e.getSource() == startGameBTN) {
                startGameBTN.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/ReadyBtnRllover.png")));

            } else if (e.getSource() == QuitBtn) {

                QuitBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/InGameQuit.png")));
            } else if (e.getSource() == restart) {
                restart.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/RestartP.png")));
            }

        }

    }

}
