package com.group1e.tankzone.gui;

import com.group1e.tankzone.Managers.GameType;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;

//import GameControl.GameManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.border.*;

public class Start extends JPanel implements ActionListener {

    private String modeSelected = " ";
    private String factionSelected = " ";
    private String climateSelected = " ";
    private String difficultySelected = " ";

    BufferedImage background = null;
    JLabel prompt, mode, faction, climate, difficulty;
    JTextField userName;
    JPanel aboutUser;
    JPanel checkboxes1;
    JPanel checkboxes2;
    JPanel checkboxes3;
    JButton play;
    JButton back;
    JCheckBox ctf, ffa, temperate, winter, desert, easy, medium, hard, hasFaction;
    ImageIcon img = null;
    ImageIcon imgAnother = null;
    ImageIcon imgAnother2 = null;
    File file = null;

    public Start() {

        checkboxes1 = new JPanel();
        checkboxes2 = new JPanel();
        checkboxes3 = new JPanel();
        aboutUser = new JPanel();

        ctf = new JCheckBox("Capture the Flag");
        ctf.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        ctf.setBorder(new LineBorder(Color.BLACK, 3));
        ctf.setOpaque(true);
        ctf.setBackground(Color.LIGHT_GRAY);
        ffa = new JCheckBox("Free For All");
        ffa.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        ffa.setBorder(new LineBorder(Color.BLACK, 3));
        ffa.setOpaque(true);
        ffa.setBackground(Color.LIGHT_GRAY);

        ctf = new JCheckBox("Capture the Flag");
        ctf.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        ctf.setBorder(new LineBorder(Color.BLACK, 3));
        ctf.setOpaque(true);
        ctf.setBackground(Color.LIGHT_GRAY);

        temperate = new JCheckBox("Temperate");
        temperate.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        temperate.setBorder(new LineBorder(Color.BLACK, 3));
        temperate.setOpaque(true);
        temperate.setBackground(Color.LIGHT_GRAY);

        winter = new JCheckBox("Winter");
        winter.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        winter.setBorder(new LineBorder(Color.BLACK, 3));
        winter.setOpaque(true);
        winter.setBackground(Color.LIGHT_GRAY);

        desert = new JCheckBox("Desert");
        desert.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        desert.setBorder(new LineBorder(Color.BLACK, 3));
        desert.setOpaque(true);
        desert.setBackground(Color.LIGHT_GRAY);

        easy = new JCheckBox("Easy");
        easy.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        easy.setBorder(new LineBorder(Color.BLACK, 3));
        easy.setOpaque(true);
        easy.setBackground(Color.LIGHT_GRAY);

        medium = new JCheckBox("Medium");
        medium.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        medium.setBorder(new LineBorder(Color.BLACK, 3));
        medium.setOpaque(true);
        medium.setBackground(Color.LIGHT_GRAY);

        hard = new JCheckBox("Hard");
        hard.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        hard.setBorder(new LineBorder(Color.BLACK, 3));
        hard.setOpaque(true);
        hard.setBackground(Color.LIGHT_GRAY);

        Font f = new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30);
        prompt = new JLabel("Enter your name: ");
        prompt.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        mode = new JLabel("Mode: ");
        faction = new JLabel("Faction: ");
        faction.setFont(f);
        faction.setForeground(Color.BLACK);
        hasFaction = new JCheckBox("Team Mode");
        hasFaction.setFont(new Font("SNAP ITC", Font.BOLD | Font.ITALIC, 30));
        hasFaction.setBorder(new LineBorder(Color.BLACK, 3));
        hasFaction.setOpaque(true);
        hasFaction.setBackground(Color.LIGHT_GRAY);

        //faction.setBounds(60, 60, 30, 30);
        climate = new JLabel("Climate: ");
        climate.setFont(f);
        climate.setForeground(Color.BLACK);
        difficulty = new JLabel("Difficulty: ");
        difficulty.setFont(f);
        difficulty.setForeground(Color.BLACK);
        play = new JButton("Play");
        back = new JButton("Back");
        userName = new JTextField(15);
        userName.setFont(f);
        userName.setForeground(Color.BLACK);
        userName.addActionListener(new UserNameListener());

        mode = new JLabel("Mode: ");
        mode.setFont(f);
        mode.setForeground(Color.BLACK);


        aboutUser.setOpaque(false);

        aboutUser.setLayout(new BoxLayout(aboutUser, BoxLayout.X_AXIS));
        aboutUser.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        aboutUser.add(Box.createHorizontalGlue());
        aboutUser.setLayout(new FlowLayout());
        aboutUser.add(prompt);
        aboutUser.add(userName);
        aboutUser.add(mode);
        aboutUser.add(ctf);
        aboutUser.add(ffa);

        aboutUser.add(faction);
        aboutUser.add(hasFaction);
        aboutUser.add(climate);
        aboutUser.add(temperate);
        aboutUser.add(winter);
        aboutUser.add(desert);
        aboutUser.add(difficulty);
        aboutUser.add(easy);
        aboutUser.add(medium);
        aboutUser.add(hard);

        //help.setPreferredSize(new Dimension(1000, 100));
        //empty.setPreferredSize(new Dimension(1000, 200));
        aboutUser.setPreferredSize(new Dimension(100, 100));
        //aboutUser.setBounds(20, 20, 30, 30);
        prompt.setPreferredSize(new Dimension(400, 40));
        userName.setPreferredSize(new Dimension(400, 40));
        mode.setPreferredSize(new Dimension(400, 40));
        ctf.setPreferredSize(new Dimension(400, 40));
        ffa.setPreferredSize(new Dimension(400, 40));
        faction.setPreferredSize(new Dimension(400, 50));
        climate.setPreferredSize(new Dimension(400, 50));
        temperate.setPreferredSize(new Dimension(400, 40));
        winter.setPreferredSize(new Dimension(400, 40));
        desert.setPreferredSize(new Dimension(400, 40));
        difficulty.setPreferredSize(new Dimension(400, 50));
        easy.setPreferredSize(new Dimension(400, 50));
        medium.setPreferredSize(new Dimension(400, 50));
        hard.setPreferredSize(new Dimension(400, 50));

        ctf.addItemListener(new CheckBoxListener());
        ffa.addItemListener(new CheckBoxListener());
        temperate.addItemListener(new CheckBoxListener());
        winter.addItemListener(new CheckBoxListener());
        desert.addItemListener(new CheckBoxListener());
        easy.addItemListener(new CheckBoxListener());
        medium.addItemListener(new CheckBoxListener());
        hard.addItemListener(new CheckBoxListener());

        img = new ImageIcon("C:\\Users\\Ceren\\IdeaProjects\\Group-1E-Tank-Zone-Game\\src\\desktop\\assets\\back.png");
        Image temp = img.getImage();
        temp = temp.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(temp);
        back = new JButton(img);
        back.setBorderPainted(false);
        back.setBounds(30, 30, 75, 75);


        imgAnother = new ImageIcon("C:\\Users\\Ceren\\IdeaProjects\\Group-1E-Tank-Zone-Game\\src\\desktop\\assets\\start.png");
        Image temp1 = img.getImage();
        temp1 = temp1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(temp1);
        play = new JButton(imgAnother);
        play.setBorderPainted(false);
        play.setBounds(850, 250, 125, 125);


        this.setPreferredSize(new Dimension(1200, 800));
        this.setLayout(null);

        this.add(aboutUser);
        this.add(back);
        this.add(play);
        //this.add(img);
        aboutUser.setBounds(90, 30, 800, 800);


        try {
            file = new File("C:\\Users\\Ceren\\IdeaProjects\\Group-1E-Tank-Zone-Game\\src\\desktop\\assets\\maxresdefault.png");
            background = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
            background = ImageIO.read(file);
            //background = ImageIO.read(getClass().getResourceAsStream("maxresdefault.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

    }

    private class UserNameListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String text = userName.getText();
        }

    }

    //@Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    public JButton getButton(String name) {
        if (name.equals("back")) {

            return back;
        }
        if (name.equals("play")) {

            return play;
        }

        return null;
    }

    public String getModeSelected() {
        return modeSelected;
    }

    public String getClimateSelected() {
        return climateSelected;
    }

    public String getDifficultySelected() {
        return difficultySelected;
    }

    public String getFactionSelected() {
        return factionSelected;
    }

    private class CheckBoxListener implements ItemListener {

        public void itemStateChanged(ItemEvent event) {

            if (ctf.isSelected())
                modeSelected = "ctf";
            if (ffa.isSelected())
                modeSelected = "ffa";
            if (temperate.isSelected())
                climateSelected = "temperate";
            if (winter.isSelected())
                climateSelected = "winter";
            if (desert.isSelected())
                climateSelected = "desert";
            if (easy.isSelected())
                difficultySelected = "easy";
            if (medium.isSelected())
                difficultySelected = "medium";
            if (hard.isSelected())
                difficultySelected = "hard";
            if (hasFaction.isSelected())
                factionSelected = "team";


        }


    }
}





