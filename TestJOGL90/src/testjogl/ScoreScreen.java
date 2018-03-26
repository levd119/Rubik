/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author lev&Rotem
 */
public final class ScoreScreen extends JDialog {

    private static final int X = 30, Y = 130;
    private ArrayList<User> arrayOfUsers;
    private ScoresCanvas sc;
    private Font font;
    private String message;
    private void setFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getClassLoader().getResource("Fonts/Right_to_remain_silent.ttf").getFile()));
            font = font.deriveFont((float) 30);

        } catch (FontFormatException | IOException ex) {
            font = new Font("Arial Black", Font.ITALIC, 15);
        }

    }

    public ScoreScreen(JFrame frame) {
        super(frame, true);
        arrayOfUsers = new ArrayList<>();

        this.setSize(520, 600);

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        int x = (d.width - this.getWidth()) / 2;
        int y = (d.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        setFont();
        sc = new ScoresCanvas();

        this.add(sc);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addScores();
        System.out.println("end scores");
    }

    void addScores() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                ConnectionUtil cu = new ConnectionUtil();
                try {
                    message="Loading";

                    ConnectionUtil.openConnectionScore();
                    arrayOfUsers = cu.getUserLists();

                    System.out.println("size " + arrayOfUsers.size());
                    for (int i = 0; i < arrayOfUsers.size(); i++) {
                        User user = arrayOfUsers.get(i);
                        String time = user.getTime().get(Calendar.HOUR) + " : " + user.getTime().get(Calendar.MINUTE) + " : " + user.getTime().get(Calendar.SECOND);

                    }

                } catch (IOException ex) {

                    message="Can't Connect";

                }
                ScoreScreen.this.sc.repaint();

            }
        });
        thread.start();
    }

    private class ScoresCanvas extends JComponent {

        JButton exitScoresBtn = new JButton();

        ScoresCanvas() {
            setOpaque(false);
            exitScoresBtn.setLocation(443, 23);
            exitScoresBtn.setSize(60, 50);
            exitScoresBtn.setOpaque(false);
            exitScoresBtn.setContentAreaFilled(false);
            exitScoresBtn.setBorderPainted(false);
            exitScoresBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            exitScoresBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    dispose();
                }
            });

            add(exitScoresBtn);
        }

        private void draw(Graphics g) {
            int space = 0;

            g.setFont(font);

            space = 104;
            Iterator it = arrayOfUsers.iterator();
            int i = 0;
            if (!arrayOfUsers.isEmpty()) {
                while (it.hasNext()) {
                    User user = (User) it.next();
                    g.setColor(Color.BLUE);
                    g.drawString((i + 1) + ".", X, Y + space);

                    g.setColor(Color.BLUE);
                    g.drawString(user.getName(), X * 2, Y + space);

                    g.drawString((user.getTime().getTime().getHours() + ":" + user.getTime().getTime().getMinutes() + ":" + user.getTime().getTime().getSeconds()), X * 5, Y + space);
                    g.setColor(Color.BLUE);

                    g.drawString(Integer.toString(user.getMoves()), X * 9, Y + space);
                    g.setColor(Color.BLUE);

                    g.drawString(Integer.toString(user.getHints()), X * 12, Y + space);
                    g.setColor(Color.BLUE);

                    space += 40;
                    i++;
                }
            } else {


                g.setColor(Color.RED);
                g.drawString(message, X + 150, Y + 200);

            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            BufferedImage bi = null;
            try {
                bi = ImageIO.read(getClass().getResource("/GamePics/scores.png"));
            } catch (IOException ex) {

            }
            g.drawImage(bi, 0, 0, null);
            draw(g);

        }

    }

}
