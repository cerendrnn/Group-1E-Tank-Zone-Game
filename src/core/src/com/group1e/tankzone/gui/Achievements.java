package com.group1e.tankzone.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.border.*;


public class Achievements extends JPanel {

    BufferedImage background = null;
    ImageIcon img = null;
    JButton back;
    Font font;
    File f = null;


    public Achievements(){
        //Back button
        img = new ImageIcon("back.png");
        Image temp = img.getImage();
        temp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(temp);
        back = new JButton(img);
        back.setBorderPainted( false );
        back.setBounds(20, 20, 50, 50);

        try {
            f = new File("achievements.jpg");
            background = new BufferedImage(737, 413, BufferedImage.TYPE_INT_ARGB);
            background = ImageIO.read(f);
        } catch (IOException e){
            e.printStackTrace();
        }




        this.setPreferredSize(new Dimension(1200, 800));
        this.setLayout( null);

        this.add(back);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage( background, 0, 0, getWidth(), getHeight(), this);


    }

    //Get button
    public JButton getButton() {
        return back;
    }
    //@Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }


}
