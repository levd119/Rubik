/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author lev
 */
public final class SearchForGameFrame extends JFrame {

    MyCanvas canvas;
//    JLabel label;

    SearchForGameFrame() {
        editComponents();
        connect();

    }

    void connect() {

        MyThread t = new MyThread();
        t.start();

    }

    void editComponents() {
        setUndecorated(true);

        this.setSize(new Dimension(800, 350));
        getRootPane().setOpaque(false);
        this.getRootPane().setBackground(new Color(0, 0, 0, 0));

        this.getContentPane().setBackground(new Color(0, 0, 0, 0));
        this.setBackground(new Color(0, 0, 0, 0));

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        int x = (d.width - this.getWidth()) / 2;
        int y = (d.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        canvas = new MyCanvas();
//        label = new JLabel(new ImageIcon(getClass().getResource("/GamePics/findMatchGif.gif")));
//        label.setLayout(new FlowLayout());
        add(canvas);
    }

    class MyThread extends Thread {

        boolean connected = false;

        @Override
        public void run() {

            try {
                System.out.println("openConnection");

                ConnectionUtil.openConnectionMultiPlayer();

                do {
                    System.out.println("do");
                    ConnectionData cd;
                    cd = (ConnectionData) ConnectionUtil.receiveData();

                    if (cd != null) {
                        if (cd.getRequestCode() == ConnectionData.IS_ALIVE) {
                            cd.setResponseStatus(true);
                            ConnectionUtil.sendData(cd);
                            System.out.println("Alive");
                            continue;
                        }
                        System.out.println("get");
                        if (cd.getResponseStatus()) {
                            System.out.println("cd.getResponseStatus()==true");

                            connected = true;
                            CubeFrame frame = new CubeFrame(true);
                            frame.startThread();
                            frame.setVisible(true);
                            System.out.println("frame.setVisible(true);");

                            MultiPlayerFrame multiPlayerFrame = new MultiPlayerFrame(frame);
                            frame.setMultiplayerFrame(multiPlayerFrame);
                            multiPlayerFrame.startThread();
                            multiPlayerFrame.setVisible(true);
                            SearchForGameFrame.this.dispose();

                        }

                    } else {
                        connected = true;
                    }

                } while (!connected);

            } catch (IOException ex) {

                
                System.out.println("No ethernet");
                JOptionPane.showMessageDialog(SearchForGameFrame.this,
                        "No ethernet.Call Natan Or");
                  
                    new MainScreen().setVisible(true);
                SearchForGameFrame.this.dispose();

            }
        }

    }

    class MyCanvas extends JComponent {

        JButton b;
        ImageIcon bi;
        public MyCanvas() {
            b = new JButton();
            b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    ConnectionUtil.closeConnection();
                    new MainScreen().setVisible(true);
                    SearchForGameFrame.this.dispose();

                }
            });

            b.setLocation(650, 55);
            b.setSize(60, 60);
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));

            add(b);
            bi = null;
                URL imgURL=SearchForGameFrame.class.getResource("/GamePics/findMatchGif.gif");
                bi = new ImageIcon(imgURL);

        }

        @Override
        protected void paintComponent(Graphics grphcs) {
            super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
//            grphcs.drawImage(bi.getImage(), 0, 0, null);
            bi.paintIcon(this, grphcs, WIDTH, WIDTH);

        }

    }
}
