package com.group1e.tankzone.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.File;

public class Credits extends JPanel {

    BufferedImage background = null;
    ImageIcon img = null;
    JTextArea text;
    JButton back;
    JLabel credit;
    Font font;
    File f = null;


    public Credits() {

        //Back button
        img = new ImageIcon("back.png");

        Image temp = img.getImage();
        temp = temp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(temp);
        back = new JButton(img);
        back.setBorderPainted(false);
        back.setBounds(20, 20, 50, 50);

        try {
            f = new File("thismeanswar.png");
            background = new BufferedImage(561, 466, BufferedImage.TYPE_INT_ARGB);
            background = ImageIO.read(f);
            //background = ImageIO.read(getClass().getResourceAsStream("thismeanswar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Text area
        text = new JTextArea();

        text.setText("\n\n CREDITS" + "\n\n Aybuke Ceren DURAN"
                + "\n\n Hakan TURKMENOGLU"
                + "\n\n Mert SEZER");

        text.setBorder(new LineBorder(Color.BLACK, 3));
        text.setOpaque(true);
        text.setEditable(false);
        font = new Font("CALIBRI", Font.BOLD | Font.ITALIC, 25);
        text.setFont(font);
        text.setForeground(Color.BLACK);
        text.setBackground(Color.LIGHT_GRAY);
        text.setBounds(900, 350, 300, 400);

        this.setPreferredSize(new Dimension(1200, 800));
        this.setLayout(null);

        this.add(text);
        this.add(back);
        //this.add(credit);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);


    }

    //Get button
    public JButton getButton() {

            return back;

    }
    /*public static void main( String[] args )
	  {
	    JFrame creditsFrame = new JFrame();
	    creditsFrame.add( new Credits());
	    creditsFrame.setSize(1000, 700);
	    creditsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    creditsFrame.setResizable(false);
	    creditsFrame.setLocationRelativeTo(null);
	    creditsFrame.setVisible(true);
	    creditsFrame.pack();
	  }*/
}
