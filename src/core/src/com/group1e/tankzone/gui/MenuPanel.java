package com.group1e.tankzone.gui;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MenuPanel extends JPanel implements ActionListener {


    BufferedImage background = null;
    File f = null;
    //Image background;
    ImageIcon img;
    JPanel buttons;
    JButton newGame;
    JButton settings;
    JButton highScores;
    JButton achievements;
    JButton help;
    JButton credits;
    JButton exit;


    public MenuPanel() {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Ceren\\IdeaProjects\\Group-1E-Tank-Zone-Game\\src\\desktop\\assets\\World War Z Theme Song.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }


        buttons = new JPanel();
        newGame = new JButton("START");
        newGame.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        newGame.setBorder(new LineBorder(Color.BLACK, 3));
        newGame.setOpaque(true);
        newGame.setBackground(Color.LIGHT_GRAY);
        credits = new JButton("CREDITS");
        credits.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        credits.setBorder(new LineBorder(Color.BLACK, 3));
        credits.setOpaque(true);
        credits.setBackground(Color.LIGHT_GRAY);
        exit = new JButton("QUIT");
        exit.setFont(new Font("SNAP ITC",Font.ITALIC, 30));
        exit.setBorder(new LineBorder(Color.BLACK, 3));
        exit.setOpaque(true);
        exit.setBackground(Color.LIGHT_GRAY);
        settings = new JButton("SETTINGS");
        settings.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 35));
        settings.setBorder(new LineBorder(Color.BLACK, 3));
        settings.setOpaque(true);
        settings.setBackground(Color.LIGHT_GRAY);
        highScores = new JButton("HIGH SCORES");
        highScores.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        highScores.setBorder(new LineBorder(Color.BLACK, 3));
        highScores.setOpaque(true);
        highScores.setBackground(Color.LIGHT_GRAY);
        help = new JButton("HELP");
        help.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        help.setBorder(new LineBorder(Color.BLACK, 3));
        help.setOpaque(true);
        help.setBackground(Color.LIGHT_GRAY);
        achievements = new JButton("ACHIEVEMENTS");
        achievements.setFont(new Font("SNAP ITC",Font.BOLD |Font.ITALIC, 30));
        achievements.setBorder(new LineBorder(Color.BLACK, 3));
        achievements.setOpaque(true);
        achievements.setBackground(Color.LIGHT_GRAY);

        buttons.setOpaque(false);

        buttons.setLayout( new BoxLayout( buttons, BoxLayout.Y_AXIS));
        buttons.setAlignmentY( JComponent.CENTER_ALIGNMENT);
        buttons.add(Box.createVerticalGlue());
        buttons.setLayout( new FlowLayout());
        buttons.add(newGame);
        buttons.add(settings);
        buttons.add(highScores);
        buttons.add(achievements);
        buttons.add(help);
        buttons.add(credits);
        buttons.add(exit);

        buttons.setPreferredSize(new Dimension(300, 560));
        newGame.setPreferredSize( new Dimension(300, 50));
        highScores.setPreferredSize( new Dimension(300, 50));
        achievements.setPreferredSize( new Dimension(340, 50));
        help.setPreferredSize( new Dimension(300, 50));
        credits.setPreferredSize( new Dimension(300, 50));
        settings.setPreferredSize( new Dimension(300, 50));
        exit.setPreferredSize( new Dimension(100, 50));


        this.setPreferredSize(new Dimension(1200, 800));
        this.setLayout(null);

        this.add( buttons);
        buttons.setBounds(480, 200, 570, 570);

        try {
            f = new File("C:\\Users\\Ceren\\IdeaProjects\\Group-1E-Tank-Zone-Game\\src\\desktop\\assets\\tank_backg_0.png");
            background = new BufferedImage(737, 413, BufferedImage.TYPE_INT_ARGB);
            background = ImageIO.read(f);
            //background = ImageIO.read(getClass().getResourceAsStream("tank_backg_0.png"));
            //background = ImageIO.read(getClass().getResource("\\com\\group1e\\tankzone\\gui\\resources.images\\tank_backg_0.png"));



        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public JButton getButton( String name)
    {
        if ( name.equals("START") )
        {
            return newGame;
        }
        else if ( name.equals("CREDITS") )
        {
            return credits;
        }
        else if ( name.equals("QUIT") )
        {
            return exit;
        }
        else if ( name.equals("SETTINGS") )
        {
            return settings;
        }
        else if ( name.equals("ACHIEVEMENTS") )
        {
            return achievements;
        }
        else if ( name.equals("HIGH SCORES") )
        {
            return highScores;
        }
        else if ( name.equals("HELP") )
        {
            return help;
        }
        return null;
    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage( background, 0, 0, getWidth(), getHeight(), this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }

	 /* public static void main( String[] args )
	  {
	    JFrame mainMenuFrame = new JFrame();
	    mainMenuFrame.add( new MenuPanel());
	    mainMenuFrame.setSize(1000, 700);
	    mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainMenuFrame.setResizable(false);
	    mainMenuFrame.setLocationRelativeTo(null);
	    mainMenuFrame.setVisible(true);
	    mainMenuFrame.pack();
	  }  */

}


