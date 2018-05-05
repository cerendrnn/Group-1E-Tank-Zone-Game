package com.group1e.tankzone.gui;

import com.group1e.tankzone.Components.PositionComponent;
import com.group1e.tankzone.Entities.Entity;
import com.group1e.tankzone.Entities.EntityFactory;
import com.group1e.tankzone.Managers.Engine;
import com.group1e.tankzone.Managers.GameType;
import com.group1e.tankzone.Managers.MapGenerator;
import com.group1e.tankzone.Managers.World;
import com.group1e.tankzone.Systems.*;
import com.group1e.tankzone.Systems.AI.MoveStraightStrategy;
import com.group1e.tankzone.Systems.AI.ShootStraightStategy;
import com.group1e.tankzone.Systems.AI.TargetClosestStrategy;
import com.group1e.tankzone.Managers.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.badlogic.gdx.math.MathUtils.random;


public class MainMenu {

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
    GameType.Climate gameClimate = null;
    GameType.Difficulty gameDifficulty = null;
    GameType.GameMode gameMode = null;
    //GameType.GameFaction gameFaction = null;

    public MainMenu() {
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
        cardpanel = new JPanel(cardLayout);
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
        pause.setBounds(1020, 20, 150, 60);
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
        cardpanel.add(settingsPanel, "7");
        //cardpanel.add(pause, "4");
        //cardpanel.setOpaque(false);

        // adds cardpanel to frame
        mainMenuFrame.add(cardpanel);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);

        // shows main menu screen first
        cardLayout.show(cardpanel, "1");

        /////////////////////////////////////////
        //checkGameOver = new Timer(50, new gameOverActionListener());
        /////////////////////////////////////////

        // adds ActionListeners to all buttons
        mainMenuPanel.getButton("START").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        mainMenuPanel.getButton("SETTINGS").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        mainMenuPanel.getButton("HIGH SCORES").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        mainMenuPanel.getButton("ACHIEVEMENTS").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        mainMenuPanel.getButton("HELP").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        mainMenuPanel.getButton("CREDITS").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        mainMenuPanel.getButton("QUIT").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());

        playPanel.getButton("back").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
       /* playPanel.getButton("Capture the Flag").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        playPanel.getButton("Free for All").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        playPanel.getButton("temperate").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        playPanel.getButton("Winter").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        playPanel.getButton("Desert").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        playPanel.getButton("Easy").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        playPanel.getButton("Medium").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        playPanel.getButton("Hard").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());*/
        playPanel.getButton("play").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());

        howToPlay.getButton("back").addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        creditsPanel.getButton().addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        //playPanel.getButton().addActionListener( new MyActionListener() );
        achPanel.getButton().addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        scorePanel.getButton().addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());
        settingsPanel.getButton().addActionListener(new com.group1e.tankzone.gui.MainMenu.MyActionListener());

        //pause.addActionListener(new MyActionListener());

        //checkGameOver.start();
        mainMenuFrame.setVisible(true);
    }


    /////////////////////////////////////////////////
  private class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout = (CardLayout) (cardpanel.getLayout());
            JComponent pressedButton = (JComponent) e.getSource();


            if (pressedButton == playPanel.getButton("back")) {
                cardLayout.show(cardpanel, "1");
            } else if (pressedButton == scorePanel.getButton()) {
                cardLayout.show(cardpanel, "1");
            } else if (pressedButton == achPanel.getButton()) {
                cardLayout.show(cardpanel, "1");
            } else if (pressedButton == settingsPanel.getButton()) {
                cardLayout.show(cardpanel, "1");
            } else if (pressedButton == howToPlay.getButton("back") || pressedButton == creditsPanel.getButton()) {
                cardLayout.show(cardpanel, "1");
            } else if (pressedButton == mainMenuPanel.getButton("START")) {
                //levelsPanel.reEvaluateLevels();
                cardLayout.show(cardpanel, "2");
            } else if (pressedButton == mainMenuPanel.getButton("ACHIEVEMENTS")) {
                //levelsPanel.reEvaluateLevels();
                cardLayout.show(cardpanel, "3");
            } else if (pressedButton == mainMenuPanel.getButton("CREDITS")) {
                cardLayout.show(cardpanel, "5");
            } else if (pressedButton == mainMenuPanel.getButton("HELP")) {
                cardLayout.show(cardpanel, "4");
            } else if (pressedButton == mainMenuPanel.getButton("HIGH SCORES")) {
                cardLayout.show(cardpanel, "6");
            } else if (pressedButton == mainMenuPanel.getButton("SETTINGS")) {
                cardLayout.show(cardpanel, "7");
            } else if (pressedButton == mainMenuPanel.getButton("QUIT")) {
                mainMenuFrame.dispose();
            } else if (pressedButton == playPanel.getButton("play")) {
                if (playPanel.getClimateSelected() == "temperate")
                    gameClimate = GameType.Climate.TEMPERATE;
                if (playPanel.getClimateSelected() == "winter")
                    gameClimate = GameType.Climate.WINTER;
                if (playPanel.getClimateSelected() == "desert")
                    gameClimate = GameType.Climate.DESERT;
                if (playPanel.getDifficultySelected() == "easy")
                    gameDifficulty = GameType.Difficulty.EASY;
                if (playPanel.getDifficultySelected() == "medium")
                    gameDifficulty = GameType.Difficulty.MEDIUM;
                if (playPanel.getDifficultySelected() == "hard")
                    gameDifficulty = GameType.Difficulty.HARD;
                if (playPanel.getModeSelected() == "ctf")
                    gameMode = GameType.GameMode.CTF;
                if (playPanel.getModeSelected() == "ffa")
                    gameMode = GameType.GameMode.FFA;
                //if (playPanel.getFactionSelected() == "team")
                    //gameFaction = GameType.GameFaction.TEAM;
                //if (playPanel.getFactionSelected() == " ")
                    //gameFaction = GameType.GameFaction.NOTEAM;

                //new GameManager();

                /*Engine engine = new Engine();
                World.getInstance().setEngine(engine);
                engine.addSystem(new GraphicsSystem());
                engine.addSystem(new MovementSystem());
                engine.addSystem(new InputSystem());
                engine.addSystem(new GravitationalSystem());
                engine.addSystem(new CollisionSystem());
                engine.addSystem(new DeathSystem());
                String[] factions = new String[] {"red", "blue"};
                //engine.addSystem(new AISystem(factions, new TargetClosestStrategy(), new ShootStraightStategy(), new MoveStraightStrategy()));


                EntityFactory.createPlayer(
                        "blue",
                        600,
                        400,
                        0
                );



                World world = World.getInstance();
                world.setCameraTarget(engine.getEntity(0).getComponent(PositionComponent.class));
                MapGenerator mapGenerator = new MapGenerator();
                mapGenerator.setClimate(gameClimate);
                world.setMap(mapGenerator.getMap(), mapGenerator.getClimate());

                for (Entity en : mapGenerator.getGeneratedObstacles()) {
                    engine.addEntity(en);
                }

                if(gameDifficulty==GameType.Difficulty.easy ) {
                    if(gameMode == GameType.GameMode.FFA) {
                        for (int i = 0; i < 5; ++i) {
                            EntityFactory.createTank(
                                    "red",
                                    random(0, 20) * 20,
                                    random(0, 20) * 20,
                                    random(-20, 20)
                            );
                        }
                    }
                    else{
                        //if(gameClimate==GameType.Climate.DESERT)
                        //EntityFactory.createCastle("desert",?,?);
                        //else if(gameClimate == GameType.Climate.WINTER)
                        //EntityFactory.createCastle("winter",?,?);
                        //else if(gameClimate == GameType.Climate.TEMPERATE)
                        //EntityFactory.createCastle("temperate",?,?);
                        engine.addSystem(new AISystem(factions, new TargetClosestStrategy(), new ShootStraightStategy(), new MoveStraightStrategy()));
                        //engine.addSystem(new SpawnSystem())???
                    }


                }
                if(gameDifficulty==GameType.Difficulty.medium) {
                    if(gameMode == GameType.GameMode.FFA) {
                        for (int i = 0; i < 10; ++i) {
                            EntityFactory.createTank(
                                    "red",
                                    random(0, 20) * 20,
                                    random(0, 20) * 20,
                                    random(-20, 20)
                            );
                        }
                    }
                    else{
                        //if(gameClimate==GameType.Climate.DESERT)
                        //EntityFactory.createCastle("desert",?,?);
                        //else if(gameClimate == GameType.Climate.WINTER)
                        //EntityFactory.createCastle("winter",?,?);
                        //else if(gameClimate == GameType.Climate.TEMPERATE)
                        //EntityFactory.createCastle("temperate",?,?);
                        engine.addSystem(new AISystem(factions, new TargetClosestStrategy(), new ShootStraightStategy(), new MoveStraightStrategy()));
                    }
                }

                if(gameDifficulty==GameType.Difficulty.hard) {
                    if(gameMode == GameType.GameMode.FFA) {
                        for (int i = 0; i < 15; ++i) {
                            EntityFactory.createTank(
                                    "red",
                                    random(0, 20) * 20,
                                    random(0, 20) * 20,
                                    random(-20, 20)
                            );
                        }
                    }
                    else{
                        //if(gameClimate==GameType.Climate.DESERT)
                        //EntityFactory.createCastle("desert",?,?);
                        //else if(gameClimate == GameType.Climate.WINTER)
                        //EntityFactory.createCastle("winter",?,?);
                        //else if(gameClimate == GameType.Climate.TEMPERATE)
                        //EntityFactory.createCastle("temperate",?,?);
                        engine.addSystem(new AISystem(factions, new TargetClosestStrategy(), new ShootStraightStategy(), new MoveStraightStrategy()));
                    }
                }

                //engine.addEntity(new Blackhole(300, 300, 10000));
*/
            }
        }
    }

}
