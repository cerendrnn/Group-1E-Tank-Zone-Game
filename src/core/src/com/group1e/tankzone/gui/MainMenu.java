package com.group1e.tankzone.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;

//import GameControl.GameManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu{

    private JFrame mainMenuFrame;
    private MenuPanel mainMenuPanel;
    private Credits creditsPanel;
    private Help howToPlay;
    private Start playPanel;
    private Achievements achPanel;
    private HighScores scorePanel;
    private Settings settingsPanel;
    //private GameManager game;

    private CardLayout cardLayout;
    private JPanel cardpanel;
    private Timer checkGameOver;
    private JButton pause;
    private Image buttonImage1;
    private Image buttonImage2;
    private Image img;
    private Image img2;
    //private JPanel mainPanel;

    public MainMenu(){
        //mainPanel = new JPanel();

        // initializes the frame's properties
        mainMenuFrame = new JFrame();
        mainMenuFrame.setSize(1200, 800);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLayout(new FlowLayout());
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setLocationRelativeTo(null);

        // creates instances of GUI stuff and layout for transition
        cardLayout = new CardLayout();
        cardpanel = new JPanel( cardLayout);
        mainMenuPanel = new MenuPanel();
        creditsPanel = new Credits();
        howToPlay = new Help();
        playPanel = new Start();
        achPanel = new Achievements();
        scorePanel = new HighScores();
        settingsPanel = new Settings();
        //game = new GameManager();
        JLayeredPane lp = new JLayeredPane();
        //PausePanel pause = new PausePanel();
        pause = new JButton("PAUSE");
        try {
            img = ImageIO.read(getClass().getResource("/pauseButton.png"));
            buttonImage1 = img.getScaledInstance(150, 60, Image.SCALE_SMOOTH);
            pause.setIcon(new ImageIcon(buttonImage1));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            img2 = ImageIO.read(getClass().getResource("/pauseButton.png"));
            buttonImage2 = img2.getScaledInstance(150, 60, Image.SCALE_SMOOTH);
            //button.setIcon(new ImageIcon(buttonImage2));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        pause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pause.setIcon(new ImageIcon(buttonImage2));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                pause.setIcon(new ImageIcon(buttonImage1));
            }
        });

        //djsfkljsdklfjsdklj
        lp.setPreferredSize(new Dimension(1200, 800));
        //lp.add(game, Integer.valueOf(1));
        lp.add(pause, Integer.valueOf(2));
        //game.setBounds( 0, 0,
        //1200, 800);
        pause.setBounds( 1020, 20,  150, 60);
        pause.setOpaque(false);
        //lp.setOpaque(false);

        // adds instances to cardpanel
        //mainMenuPanel.setBounds(200, 300, 300, 300);
        cardpanel.add(mainMenuPanel, "1");
        cardpanel.add(playPanel, "2");
        //cardpanel.add(game, "3");
        cardpanel.add(achPanel, "3");
        cardpanel.add(howToPlay, "4");
        cardpanel.add(creditsPanel, "5");
        cardpanel.add(scorePanel, "6");
        cardpanel.add(settingsPanel,"7");
        //cardpanel.add(pause, "4");
        //cardpanel.setOpaque(false);

        // adds cardpanel to frame
        mainMenuFrame.add(cardpanel);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);

        // shows main menu screen first
        cardLayout.show( cardpanel, "1");

        /////////////////////////////////////////
        //checkGameOver = new Timer(50, new gameOverActionListener());
        /////////////////////////////////////////

        // adds ActionListeners to all buttons
        mainMenuPanel.getButton("START").addActionListener( new MyActionListener() );
        mainMenuPanel.getButton("SETTINGS").addActionListener( new MyActionListener() );
        mainMenuPanel.getButton("HIGH SCORES").addActionListener( new MyActionListener() );
        mainMenuPanel.getButton("ACHIEVEMENTS").addActionListener( new MyActionListener() );
        mainMenuPanel.getButton("HELP").addActionListener( new MyActionListener() );
        mainMenuPanel.getButton("CREDITS").addActionListener( new MyActionListener() );
        mainMenuPanel.getButton("QUIT").addActionListener( new MyActionListener() );

        playPanel.getButton("back").addActionListener( new MyActionListener() );
        playPanel.getButton("Capture the Flag").addActionListener( new MyActionListener() );
        playPanel.getButton("Free for All").addActionListener( new MyActionListener() );
        playPanel.getButton("Temperate").addActionListener( new MyActionListener() );
        playPanel.getButton("Winter").addActionListener( new MyActionListener() );
        playPanel.getButton("Desert").addActionListener( new MyActionListener() );
        playPanel.getButton("Easy").addActionListener( new MyActionListener() );
        playPanel.getButton("Medium").addActionListener( new MyActionListener() );
        playPanel.getButton("Hard").addActionListener( new MyActionListener() );

        howToPlay.getButton().addActionListener( new MyActionListener() );
        creditsPanel.getButton().addActionListener( new MyActionListener() );
        //playPanel.getButton().addActionListener( new MyActionListener() );
        achPanel.getButton().addActionListener( new MyActionListener() );
        scorePanel.getButton().addActionListener( new MyActionListener() );
        settingsPanel.getButton().addActionListener( new MyActionListener() );

        //pause.addActionListener(new MyActionListener());

        //checkGameOver.start();
        mainMenuFrame.setVisible(true);
    }


    /////////////////////////////////////////////////
    class MyActionListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e)
        {
            CardLayout cardLayout = (CardLayout)(cardpanel.getLayout());
            JComponent pressedButton = (JComponent) e.getSource();



            if( pressedButton ==playPanel.getButton("back"))
            {
                cardLayout.show( cardpanel, "1");
            }
            else if( pressedButton ==scorePanel.getButton())
            {
                cardLayout.show( cardpanel, "1");
            }

            else if( pressedButton ==achPanel.getButton())
            {
                cardLayout.show( cardpanel, "1");
            }
            else if( pressedButton ==settingsPanel.getButton())
            {
                cardLayout.show( cardpanel, "1");
            }
            else if( pressedButton == howToPlay.getButton() || pressedButton == creditsPanel.getButton())
            {
                cardLayout.show( cardpanel, "1");
            }
            else if ( pressedButton == mainMenuPanel.getButton("START"))
            {
                //levelsPanel.reEvaluateLevels();
                cardLayout.show( cardpanel, "2");
            }
            else if ( pressedButton == mainMenuPanel.getButton("ACHIEVEMENTS"))
            {
                //levelsPanel.reEvaluateLevels();
                cardLayout.show( cardpanel, "3");
            }
            else if ( pressedButton == mainMenuPanel.getButton("CREDITS"))
            {
                cardLayout.show( cardpanel, "5");
            }
            else if ( pressedButton == mainMenuPanel.getButton("HELP"))
            {
                cardLayout.show( cardpanel, "4");
            }
            else if ( pressedButton == mainMenuPanel.getButton("HIGH SCORES"))
            {
                cardLayout.show( cardpanel, "6");
            }
            else if ( pressedButton == mainMenuPanel.getButton("SETTINGS"))
            {
                cardLayout.show( cardpanel, "7");
            }
            else if( pressedButton == mainMenuPanel.getButton("QUIT")){
                mainMenuFrame.dispose();
            }
      /*else if( pressedButton == pause){
    	  /*game.setPause(!(game.isPause()));
    	  System.out.println(game.isPause());*/
            //if(game.isPause()) {
            //game.setPause(false);
            //}
            //else {
            //game.setPause(true);
            //String[] buttons = { "Resume", "Exit To MainMenu"};

            //int rc = JOptionPane.showOptionDialog(null, "Pause Panel", "Paused",
            //JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
            //if(rc == 0)
            //game.setPause(false);
            //else if(rc == 1)
            //{
            //game.setGameOver(true);
            //}
            //else {
            //game.setPause(false);
            //}
            //}
            //}

        }
    }


}