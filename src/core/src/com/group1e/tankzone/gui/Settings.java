package com.group1e.tankzone.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Settings extends JPanel {

    BufferedImage background = null;
    ImageIcon img = null;
    JButton back;
    private JTextArea textArea;
    private JButton yesButton;
    private JButton noButton;
    Font font;
    File f = null;



    public Settings(){
        //Back button
        textArea = new JTextArea();
        textArea.setText("\n\n" + "         " +"SOUND ON OR OFF?");
        textArea.setBorder(new LineBorder(Color.BLACK, 3));
        textArea.setOpaque(true);
        textArea.setEditable(false);
        font = new Font("CALIBRI", Font.BOLD | Font.ITALIC, 25);
        textArea.setFont(font);
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.LIGHT_GRAY);
        img = new ImageIcon("back.png");
        Image temp = img.getImage();
        temp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(temp);
        back = new JButton(img);
        back.setBorderPainted( false );
        back.setBounds(20, 20, 50, 50);
        textArea.setBounds(350, 200, 350, 150);

        yesButton = new JButton("ON");
        noButton = new JButton("OFF");
        yesButton.setFont(new Font("CALIBRI", Font.BOLD | Font.ITALIC, 30));
        noButton.setFont(new Font("CALIBRI", Font.BOLD | Font.ITALIC, 30));
        yesButton.setBorder(new LineBorder(Color.BLACK, 3));
        noButton.setBorder(new LineBorder(Color.BLACK, 3));
        yesButton.setOpaque(true);
        noButton.setOpaque(true);
        yesButton.setBackground(Color.LIGHT_GRAY);
        noButton.setBackground(Color.LIGHT_GRAY);

        //yesButton.setPreferredSize( new Dimension(300, 50));
        //noButton.setPreferredSize( new Dimension(300, 50));
        yesButton.setBounds(340,380,80,50);
        noButton.setBounds(500,380,80,50);
        try {
            f = new File("settings1.jpg");
            background = new BufferedImage(627, 626, BufferedImage.TYPE_INT_ARGB);
            background = ImageIO.read(f);
        } catch (IOException e){
            e.printStackTrace();
        }



        this.add(textArea);
        this.add(yesButton);
        this.add(noButton);
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
