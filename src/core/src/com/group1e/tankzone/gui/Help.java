package com.group1e.tankzone.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.LineBorder;

public class Help extends JPanel {

    BufferedImage background = null;
    ImageIcon img = null;
    private JTextArea textArea;
    private JButton back;
    Font font;
    public Help() {

        //Back button
        img = new ImageIcon("C:\\Users\\Ceren\\IdeaProjects\\Group-1E-Tank-Zone-Game\\src\\desktop\\assets\\back.png");
        Image temp = img.getImage();
        temp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(temp);
        back = new JButton(img);
        back.setBorderPainted( false );
        back.setBounds(20, 20, 50, 50);
        File f = null;

        //Text area
        textArea = new JTextArea();
        textArea.setText("\n\n MISSION: DESTROY THE ENEMIES" +"\n\n PRESS DIRECTION KEYS TO MOVE TANK"
                +"\n\n USE MOUSE TO TARGET"
                +"\n\n DO NOT FALL INTO TRAP DURING THE WAR"
                + "\n\n POWER-UPS WILL BE GIVEN ");
        textArea.setBorder(new LineBorder(Color.BLACK, 3));
        textArea.setOpaque(true);
        textArea.setEditable(false);
        font = new Font("CALIBRI", Font.BOLD | Font.ITALIC, 25);
        textArea.setFont(font);
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setBounds(700, 400, 650, 550);


        this.setPreferredSize(new Dimension(1200, 800));
        this.setLayout(null);
        this.add(textArea);
        this.add(back);

        try {
            //background = ImageIO.read(getClass().getResourceAsStream("help.png"));
            f = new File("C:\\Users\\Ceren\\IdeaProjects\\Group-1E-Tank-Zone-Game\\src\\desktop\\assets\\help.png");
            background = new BufferedImage(561, 466, BufferedImage.TYPE_INT_ARGB);
            background = ImageIO.read(f);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage( background, 0, 0, getWidth(), getHeight(), this);

    }

    //@Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    //Get button
    public JButton getButton(String name) {
        if(name.equals("back"))
        return back;
        return null;
    }

}
