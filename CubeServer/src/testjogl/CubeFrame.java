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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author lev&Rotem
 */
public class CubeFrame extends AbstractCubeFrame {

    private JButton pause;
    private JButton restart;
    private JButton quit;
    private JButton hintButton;
    private JButton cubeButton;
    private MyOnClickListener mol;
    private SmallCube[][][] cubeArray;
    private MultiPlayerFrame MultiPlayerFrame;
    private boolean isMultiPlayer = false;
    private Timer timer;
    private static final int TIME = 1000;

    public void setIsMultiPlayer(boolean isMultiPlayer) {
        this.isMultiPlayer = isMultiPlayer;
        ((RendererSingle) renderer).setIsMultiplayer(isMultiPlayer);

        if (!isMultiPlayer) {
            MultiPlayerFrame.dispose();
            
            btnPanel.removeAll();
            btnPanel.add(pause);
            btnPanel.add(Box.createRigidArea(new Dimension(-18, 0)));
            btnPanel.add(restart);
            btnPanel.add(Box.createRigidArea(new Dimension(-18, 0)));
            btnPanel.add(hintButton);
            btnPanel.add(Box.createRigidArea(new Dimension(-18, 0)));
            btnPanel.add(cubeButton);
            btnPanel.add(Box.createHorizontalGlue());
            btnPanel.add(quit);

            btnPanel.updateUI();

            btnPanel.repaint();

        }
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public CubeFrame(SmallCube[][][] cubeArray) {
        this(false);
        this.cubeArray = cubeArray;
        custom = true;
    }

    public CubeFrame(boolean isMulti) {
        super(WIDTH, HEIGTH);
        isMultiPlayer = isMulti;

        editComponents();
    }

    @Override
    void setupJOGL() {
        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities caps = new GLCapabilities(profile);
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);
        canvas = new GLJPanel(caps);

        ((GLJPanel) canvas).setLayout(new BorderLayout());
        renderer = new RendererSingle(this);
        if (!custom) {
            ((RendererSingle) renderer).initCube();
            ((RendererSingle) renderer).setIsMultiplayer(isMultiPlayer);

        } else {
            ((RendererSingle) renderer).initCube(cubeArray);
        }
        ((GLJPanel) canvas).add(btnPanel, BorderLayout.SOUTH);

        ((GLJPanel) canvas).addGLEventListener((RendererSingle) renderer);
        ((GLJPanel) canvas).addMouseMotionListener((RendererSingle) renderer);
        ((GLJPanel) canvas).addKeyListener((RendererSingle) renderer);
        ((GLJPanel) canvas).addMouseListener((RendererSingle) renderer);
        mainPanel.add(((GLJPanel) canvas), BorderLayout.CENTER);

        animator = new FPSAnimator(((GLJPanel) canvas), 80);
        animator.start();
        ((GLJPanel) canvas).setFocusable(true);
        ((GLJPanel) canvas).requestFocusInWindow();
        splashPanel.setVisible(false);
    }

    @Override
    final void editComponents() {

        super.editComponents();
        timer = new Timer(TIME, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                hintButton.setEnabled(true);
                timer.stop();
            }
        });

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (animator.isPaused()) {
                    stopBlur();
                    ((RendererSingle) renderer).getScore().timer.start();
                                ((GLJPanel) canvas).requestFocusInWindow();

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        pause = new JButton(new ImageIcon(getClass().getResource("/GamePics/InGamePause.png")));
        quit = new JButton(new ImageIcon(getClass().getResource("/GamePics/InGameQuit2.png")));
        restart = new JButton(new ImageIcon(getClass().getResource("/GamePics/RestartN.png")));
        hintButton = new JButton(new ImageIcon(getClass().getResource("/GamePics/hintbtn.png")));
        cubeButton = new JButton(new ImageIcon(getClass().getResource("/GamePics/cubeBtnResized.png")));

        mol = new CubeFrame.MyOnClickListener();

        btn_properties(pause);
        btn_properties(quit);
        btn_properties(restart);
        btn_properties(hintButton);
        btn_properties(cubeButton);

        if (!isMultiPlayer) {

            btnPanel.add(pause);
            btnPanel.add(Box.createRigidArea(new Dimension(-18, 0)));
        }
        btnPanel.add(restart);
        if (!isMultiPlayer) {//// && if !singlegame
            btnPanel.add(Box.createRigidArea(new Dimension(-18, 0)));
            btnPanel.add(hintButton);
            btnPanel.add(Box.createRigidArea(new Dimension(-18, 0)));

            btnPanel.add(cubeButton);

        }
        btnPanel.add(Box.createHorizontalGlue());
        btnPanel.add(quit);

        pause.addActionListener(mol);
        restart.addActionListener(mol);
        quit.addActionListener(mol);
        hintButton.addActionListener(mol);
        cubeButton.addActionListener(mol);

        pause.addChangeListener(mol);
        restart.addChangeListener(mol);
        hintButton.addChangeListener(mol);
        cubeButton.addChangeListener(mol);
        quit.addChangeListener(mol);

    }

    void setMultiplayerFrame(MultiPlayerFrame multiPlayerFrame) {
        MultiPlayerFrame = multiPlayerFrame;

    }

    private class MyOnClickListener implements ActionListener, ChangeListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == pause) {

                if (!(animator.isPaused())) {
                    startBlur();
                    ((RendererSingle) renderer).getScore().timer.stop();

//                    pause.setIcon(new ImageIcon(getClass().getResource("/GamePics/InGamePlay.png")));

                } else {
                    stopBlur();
                    ((RendererSingle) renderer).getScore().timer.start();

                    pause.setIcon(new ImageIcon(getClass().getResource("/GamePics/InGamePause.png")));
                }
            }

            if (ae.getSource() == restart) {
                startBlur();
                new RestartDialog(CubeFrame.this, true, renderer).setVisible(true);
                stopBlur();
            }

            if (ae.getSource() == quit) {
                startBlur();
                new QuitDialog(CubeFrame.this, true).setVisible(true);
                stopBlur();

            }
            if (ae.getSource() == hintButton) {
                ((RendererSingle) renderer).getHint();
                if (!isMultiPlayer) {
                    hintButton.setEnabled(false);
                    timer.start();
                }
            }
            if (ae.getSource() == cubeButton) {
                startBlur();

                new HelpDialog(CubeFrame.this).setVisible(true);
                stopBlur();

            }
            ((GLJPanel) canvas).requestFocusInWindow();

        }

        @Override
        public void stateChanged(ChangeEvent ce) {
            JButton btn = (JButton) ce.getSource();
            if (ce.getSource() == quit) {

                btn.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/InGameQuit.png")));
            } else if (ce.getSource() == pause) {
                btn.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/pauseBtnRllover.png")));
            } else if (ce.getSource() == restart) {
                btn.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/RestartP.png")));
            } else if (ce.getSource() == hintButton) {
                btn.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/hintBtnRllover.png")));
            } else if (ce.getSource() == cubeButton) {
                btn.setRolloverIcon(new ImageIcon(getClass().getResource("/GamePics/cubeBtnRollOverResized.png")));
            }

        }

    }

    public void finishGameDialog(Score score, String status) {
        startBlur();
        if (status.equalsIgnoreCase("Win")) {
            new FinishGameDialog(this, true, score).setVisible(true);

        } else {
            new YouLoseDialog(this, true).setVisible(true);

        }
        stopBlur();

//            new MainScreen().setVisible(true);
//            this.dispose();
    }

    @Override
    public void dispose() {
        super.dispose(); //To change body of generated methods, choose Tools | Templates.
        if (isMultiPlayer) {
            ConnectionData cd = new ConnectionData();
            cd.setRequestCode(ConnectionData.DISCONNECT);
            try {
                ConnectionUtil.sendData(cd);
            } catch (IOException ex) {
                System.out.println("Cant Send data");
            
            }
            ConnectionUtil.closeConnection();

            MultiPlayerFrame.dispose();
        }
    }

}
