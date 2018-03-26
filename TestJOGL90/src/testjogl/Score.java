/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjogl;

import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.Timer;

/**
 *
 * @author lev&Rotem
 */
public final class Score {

    private static final int LIMIT = 50;
    private static final int SPACE = 25;
    private final TextRenderer text;
    private int seconds;
    private int minutes;
    private int hours;
    public Timer timer;
    private GregorianCalendar calender;
    private int width;
    private int height;
    private int moves;
    private int hints;
    private int offsetMinutes = 0;
    private int offsetSeconds = 0;
    private int offsetHours = 0;
    private ArrayList<String> message;
    private SimpleDateFormat ft;
//    private String time;

    public ArrayList<String> split(String str) {
        int n = str.indexOf(',', LIMIT / 2);
        n++;

        ArrayList<String> message2 = new ArrayList(2);

        String substring = str.substring(n);
        substring = substring.trim();
        str = str.substring(0, n + 1);
        message2.add(str);
        message2.add(substring);

        return message2;
    }

    public void setMessage(String m) {
        m = m.replace("[", "");
        m = m.replace("]", "");
        String str[] = m.split("&");
        message = new ArrayList();
        Collections.addAll(message, str);

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i).length() > LIMIT) {
                String str1 = message.get(i);
                message.remove(i);
                message.addAll(i, split(str1));
            }

        }

    }

    public void incHints() {
        hints++;
    }

    public int getMoves() {
        return moves;
    }

    public int getHints() {
        return hints;
    }

    GregorianCalendar getDate() {
        return calender;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    void setZero() {
        calender = new GregorianCalendar();
        calender.setTime(new Date(calender.get(GregorianCalendar.YEAR), calender.get(GregorianCalendar.MONTH), calender.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0));
        moves = 0;

    }

    public Score() {
        setZero();
        Font font;
        ft = new SimpleDateFormat("HH:mm:ss");

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (offsetSeconds == 60) {

                    offsetSeconds = 0;
                    offsetMinutes++;
                } else if (offsetMinutes == 60) {
                    offsetMinutes = 0;
                    offsetHours++;
                }

                calender.set(Calendar.SECOND, offsetSeconds++);
                seconds = calender.get(Calendar.SECOND);
                calender.set(Calendar.MINUTE, offsetMinutes);
                minutes = calender.get(Calendar.MINUTE);
                calender.set(Calendar.HOUR, offsetHours);
                hours = calender.get(Calendar.HOUR);
                ft.setCalendar(calender);

            }
        });
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getClassLoader().getResource("Fonts/Right_to_remain_silent.ttf").getFile()));
            font = font.deriveFont((float) SPACE);
            

        } catch (FontFormatException | IOException ex) {
            font = new Font("Verdana", Font.BOLD, 17);
        }

        text = new TextRenderer(font);
    }

    void startTimer() {
        timer.start();
    }

    void stopTimer() {
        timer.stop();
        timer = null;
    }

    public void drawMoves(int n) {
        moves = n;
        text.beginRendering(width, height);
        text.setColor(Color.yellow);
        text.draw("Moves: " + n, 0, (int) height - SPACE);
        text.endRendering();
    }

    public void drawTime() {
        text.beginRendering(width, height);
        text.setColor(Color.yellow);
        text.draw("Time: " + calender.get(Calendar.HOUR) + ":" + calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND), 0, (int) height - 30);
        text.endRendering();
    }

    public void drawHints() {
        text.beginRendering(width, height);
        text.setColor(Color.yellow);

        text.draw("Hints:" + hints, 0, (int) height - 45);
        text.endRendering();
    }

    public void drawMessage() {
        text.beginRendering(width, height);
        text.setColor(Color.LIGHT_GRAY);
        if (message != null) {
            int r = -SPACE * 4;

            for (String message1 : message) {
                text.draw(message1, 10, (int) height + r);
                r -= SPACE;
            }
        }
        text.endRendering();
    }

    public void drawAllData(boolean multi, int n) {
        moves = n;

        text.beginRendering(width, height);

        text.setColor(Color.yellow);
        text.draw("Moves: " + n, 10, (int) height - SPACE);

        text.draw("Time: " + ft.format(calender.getTime()), 10, (int) height - SPACE * 2);
        if (!multi) {
            text.draw("Hints:" + hints, 10, (int) height - SPACE * 3);
        }
        drawMessage();
        text.endRendering();
    }

}
