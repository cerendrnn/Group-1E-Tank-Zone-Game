package com.group1e.tankzone.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Pause extends JPanel {

    private JButton button;
    private JPanel panelButton;
    private Image buttonImage1;
    private Image buttonImage2;
    private Image img;
    private Image img2;
    private CardLayout cardLayout;

    public Pause(){
        panelButton = new JPanel();

        setPreferredSize(new Dimension(1200, 800));
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        button = new JButton();

        try {
            img = ImageIO.read(getClass().getResource("/images.jpg"));
            buttonImage1 = img.getScaledInstance(150, 60, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(buttonImage1));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            img2 = ImageIO.read(getClass().getResource("/images.jpg"));
            buttonImage2 = img2.getScaledInstance(150, 60, Image.SCALE_SMOOTH);
            //button.setIcon(new ImageIcon(buttonImage2));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        //panelButton.setPreferredSize(new Dimension(1200, 800));
        panelButton.setBounds(1020, 20, 150, 60);
        //panelButton.setLayout(null);
        panelButton.setLayout(null);

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBorder(null);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setIcon(new ImageIcon(buttonImage2));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setIcon(new ImageIcon(buttonImage1));
            }
        });
        panelButton.add(button);
        button.setBounds(0, 0, 150, 60);
        panelButton.setBackground(Color.BLACK);
        //panelButton.setBounds(1020, 20, 150, 60);
        add(panelButton, "1");
        cardLayout.show( this, "1");
        setOpaque(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(new Pause());
        frame.setVisible(true);
    }

    class MyActionListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e)
        {
            //CardLayout cardLayout = (CardLayout)(cardpanel.getLayout());
            JComponent pressedButton = (JComponent) e.getSource();
        }
    }

}
