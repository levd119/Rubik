/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jhlabs.image.GaussianFilter;
import com.jogamp.opengl.GLDrawable;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lev
 */
public abstract class AbstractCubeFrame extends JFrame {
    
    public static final int WIDTH = 600, HEIGTH = 600;
    protected FPSAnimator animator;
    protected JPanel mainPanel;
    protected JPanel sidePanel;
    protected Renderer renderer;
    protected JPanel splashPanel;
    protected JPanel btnPanel;
    protected Image splashLableBG;
    protected JLabel animatedCube;
    protected Image img;
    protected boolean custom;
    protected int x, y;
    protected GLDrawable canvas;
    
    
    
        public void startBlur() {
        ((BlurPanel) mainPanel).startBlur(10);
        animator.pause();
        ((GLJPanel) canvas).setVisible(false);

    }

    public void stopBlur() {
        ((GLJPanel) canvas).setVisible(true);
        animator.resume();

    }

    @Override
    public void dispose() {
        System.out.println("Stop Animator?");
        if (animator != null && animator.isAnimating()) {
            System.out.println("Stop Animator");
            animator.stop();
        }
        super.dispose();

    }
    
    public AbstractCubeFrame(int width, int heigth) {
        this.setSize(new Dimension(width, heigth));
        this.getContentPane().setBackground(new Color(1, 0, 0, 0));
        
    }
    
    public void stopAnimator() {
        if (animator != null) {
            if (animator.isStarted()) {
                System.out.println("Animator Stopped");
                animator.stop();
            }
        }
    }
    
    void startThread() {
        Thread t = new Thread(new Runnable() {
            
            @Override
            public void run() {
                setupJOGL();
            }
        });
        
        t.start();
        
    }
    
    protected void setUpWindow() {
        
        splashPanel.add(animatedCube);

        mainPanel.add(splashPanel, BorderLayout.WEST);
        this.setLocation(x, y);
        
    }
    
    abstract  void setupJOGL();
    
    void btn_properties(JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
    }
    
    void editComponents() {
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        x = (d.width - this.getWidth()) / 2;
        y = (d.height - this.getHeight()) / 2;
        
        splashLableBG = new ImageIcon(getClass().getResource("/GamePics/LoadingPanel.png")).getImage();
        mainPanel = new BlurPanel(new BorderLayout(), x, y);
        this.setContentPane(mainPanel);
        
        this.setUndecorated(true);
        splashPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(splashLableBG, 0, 0, null);
                
            }
            
        };
        splashPanel.setLayout(new BorderLayout());
        splashPanel.setOpaque(false);
        animatedCube = new JLabel(new ImageIcon(getClass().getResource("/testjogl/animateRC.gif")));

        btnPanel = new JPanel();
        
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        
        btnPanel.setCursor(Cursor.getDefaultCursor());
        btnPanel.setOpaque(false);
        setUpWindow();
        
        this.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent we) {
                
                stopAnimator();
                System.exit(0);
            }
            
        });

    }
    
    public class BlurPanel extends JPanel {
        
        BufferedImage output = null;
        int x, y;
        
        public BlurPanel(LayoutManager lm, int x, int y) {
            super(lm);
            this.x = x;
            this.y = y;
        }
        
        public final void startBlur(float alpha) {
            
            try {
//                BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//                Graphics2D g= image.createGraphics();
//                canvas.printAll(g);
//                image.flush();
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(x, y, 600, 600));
                GaussianFilter gf = new GaussianFilter();
                
                gf.setRadius(alpha);
                
                output = gf.filter(image, output);

            } catch (AWTException ex) {

                System.out.println("sdfsdfsdfdfd NOOOOO");
            
            }

//            float[] karnelData = new float[400];
//            for (int i = 0; i < 400; i++) {
//                karnelData[i] = 1.0f / 400.0f;
//            }
//
//            BufferedImage image = null;
//            try {
//                image = new Robot().createScreenCapture(new Rectangle(x, y, 600, 600));
//            } catch (AWTException ex) {
//                ex.printStackTrace();
//            }
//
//            ConvolveOp bio = new ConvolveOp(new Kernel(20, 20, karnelData), ConvolveOp.EDGE_ZERO_FILL, null);
//            output = bio.filter(image, null);//= ImageIO.read(new File("/Users/lev/Desktop/11012782_1067168496650215_6044394165402934474_o.jpg"));
//
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (output != null) {
                g.drawImage(output, 0, 0, null);
                
            } else {
                g.drawImage(img, 0, 0, null);
                
            }
        }
        
    }
    
}
