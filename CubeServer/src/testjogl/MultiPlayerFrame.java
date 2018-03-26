/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lev
 */
public class MultiPlayerFrame extends AbstractCubeFrame {

    private final JFrame context;
//    Timer timer;

    public MultiPlayerFrame(JFrame context) {
        super(WIDTH /2, HEIGTH/2);
        this.context = context;
        editComponents();

    }

    public void sendMessage(String m) {
        Renderer rendererSingl = ((CubeFrame) context).getRenderer();
        ((RendererSingle) rendererSingl).setEndGameDialog("Loose");
    }

    @Override
    void setupJOGL() {
        System.out.println("setupJOGL()");
        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities caps = new GLCapabilities(profile);
        canvas = new GLCanvas(caps);

        renderer = new MultiPlayerRenderer(this);
        ((GLCanvas) canvas).addGLEventListener(((MultiPlayerRenderer) renderer));
        ((MultiPlayerRenderer) renderer).initCube();
        mainPanel.add(((GLCanvas) canvas), BorderLayout.CENTER);
        animator = new FPSAnimator(((GLCanvas) canvas), 20, true);
        animator.start();
        mainPanel.setVisible(true);
        splashPanel.setVisible(false);
//        timer.start();
    }

    @Override
    final void editComponents() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        x = ((context.getWidth()/2) + (int) d.getWidth() / 2);
        y = ((context.getHeight()/2)+(int) d.getHeight()/2)-this.getHeight();

        mainPanel = new BlurPanel(new BorderLayout(), x, y);
        this.setContentPane(mainPanel);

        this.setUndecorated(true);
        splashPanel = new JPanel();
        splashPanel.setLayout(new BorderLayout());
        splashPanel.setOpaque(false);

        mainPanel.add(splashPanel, BorderLayout.WEST);
        this.setLocation(x, y);

    }

    void setMultiplayer(boolean b) {
        ((CubeFrame) context).setIsMultiPlayer(b);

    }

    @Override
    public void dispose() {
        super.dispose(); //To change body of generated methods, choose Tools | Templates.
    }

}
