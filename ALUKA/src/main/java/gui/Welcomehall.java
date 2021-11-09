/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import DBMS.DBConnection;
import DBMS.PgLoad;
import Equipe.Armor;
import Equipe.Weapon;
import I_O_to_file.Readtofile;
import assistant.Guide;
import assistant.MusicPlayer;
import character.Pg;
import interfaceclass.InterfaceDBMS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import systemmanager.GameManager;

/**
 *
 * @author miche
 */
public class Welcomehall {

    private JFrame frame = null;
    private Container container = null;
    private JPanel window = new JPanel();
    private JPanel pcenter = null;
    private String[] difficulty = {"facile", "normale", "difficile"};
    private JComboBox cbdifficulty;
    private JTextField tnamepg;
    private JTextField selectpg;
    private Pg pg = null;
    private BufferedImage myPicture1 = null;
    private JLabel limg = null;
    private JPanel pimg = null;
    private MusicPlayer music = null;
    private JButton bsound = null;
    private JPanel psound = null;
    

    public Welcomehall() {
        this.frame = new JFrame("Pulsa Danura");
        container = this.frame.getContentPane();
        container.setLayout(new BorderLayout());
        frame.setBounds(300, 100, 600, 400);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setOpaque(false);
        window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));
        try {
            myPicture1 = ImageIO.read(new File("src/ALUKA.png"));
        } catch (IOException ex) {
            Logger.getLogger(Welcomehall.class.getName()).log(Level.SEVERE, null, ex);
        }
        pimg = new JPanel();
        limg = new JLabel(new ImageIcon(myPicture1));
        pimg.add(limg);
        pimg.setBackground(Color.white);
        Welcompanel wel = new Welcompanel();
        this.pcenter = new JPanel();
        pcenter.setOpaque(false);
        pcenter.setLayout(new BoxLayout(pcenter, BoxLayout.PAGE_AXIS));
        container.add(this.window, BorderLayout.LINE_START);
        container.add(pcenter, BorderLayout.CENTER);
        container.add(pimg, BorderLayout.PAGE_START);
        container.setBackground(Color.white);
        music = new MusicPlayer();
        music.playintro();
        this.frameshowon();

    }

    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }

    private void frameshowon() {
        frame.setVisible(true);
    }

    private class Welcompanel {

        public Welcompanel() {
            JPanel pnewadv = new JPanel();
            JPanel ploadadv = new JPanel();
            JPanel pexitgame = new JPanel();
            JPanel pguide = new JPanel();
            psound = new JPanel();

            JButton bnewadv = new JButton("Nuova Avventura");
            JButton bloadadv = new JButton("Carica Partita");
            JButton bexitgame = new JButton("Esci");
            JButton bguide = new JButton("Guida");
            bsound = new JButton("Disativa audio");

            pnewadv.setOpaque(false);
            ploadadv.setOpaque(false);
            pexitgame.setOpaque(false);
            pguide.setOpaque(false);
            psound.setOpaque(false);

            bnewadv.addActionListener(new Welcomehall.Newadventure());
            bguide.addActionListener(new Welcomehall.Showguide());
            bexitgame.addActionListener(new Welcomehall.Exitgame());
            bloadadv.addActionListener(new Welcomehall.Loadadventure());
            bsound.addActionListener(new Welcomehall.OnOffSound());

            pnewadv.add(bnewadv, BorderLayout.CENTER);
            ploadadv.add(bloadadv, BorderLayout.CENTER);
            pexitgame.add(bexitgame, BorderLayout.CENTER);
            pguide.add(bguide, BorderLayout.CENTER);
            psound.add(bsound,BorderLayout.CENTER);

            window.add(pnewadv);
            window.add(ploadadv);
            window.add(pexitgame);
            window.add(pguide);
            window.add(psound);
        }
    }

    private class Newadventure implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Newadventurepanel newadv = new Newadventurepanel();
        }
    }

    private class Loadadventure implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Loadadventurepanel loadadv = new Loadadventurepanel();
        }
    }

    private class Exitgame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e4) {

            close();
        }
    }

    private class Showguide implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Showguidepanel show = new Showguidepanel();
        }
    }
    
     private class OnOffSound implements ActionListener {

         private Boolean onoff = true;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (onoff) {
                bsound.setText("Attiva audio");
                onoff = false;
                music.stopmusic();
            } else {
                bsound.setText("Disattiva audio");
                onoff = true;
                music.playmusic();
            }
            psound.repaint();
        }
    }

    private class Showguidepanel {

        JScrollPane scroll = null;
        JTextArea text = null;
        Guide guide = null;

        public Showguidepanel() {
            pcenter.removeAll();
            pcenter.repaint();

            guide = new Guide();
            text = new JTextArea(guide.getGuide());
            text.setEditable(false);
            text.setLineWrap(true);
            text.setWrapStyleWord(true);

            pcenter.add(text, BorderLayout.CENTER);
            frameshowon();

        }

    }

    private class Newadventurepanel {

        public Newadventurepanel() {
            pcenter.removeAll();
            pcenter.repaint();

            JPanel pnamepg = new JPanel();
            JLabel lnamepg = new JLabel("Nome personaggio :");
            tnamepg = new JTextField(20);

            JPanel pdifficulty = new JPanel();
            JLabel ldifficulty = new JLabel("Scegli la dificolta' :");
            cbdifficulty = new JComboBox(difficulty);
            cbdifficulty.setSelectedIndex(0);

            JPanel pbutton = new JPanel();
            JButton start = new JButton("Inizia Partita");
            start.addActionListener(new Welcomehall.StartNewadventure());

            pnamepg.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            pdifficulty.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            pbutton.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

            pnamepg.setOpaque(false);
            pdifficulty.setOpaque(false);
            pbutton.setOpaque(false);

            pnamepg.add(lnamepg);
            pnamepg.add(tnamepg);
            pdifficulty.add(ldifficulty);
            pdifficulty.add(cbdifficulty);
            pbutton.add(start);

            pcenter.add(pnamepg);
            pcenter.add(pdifficulty);
            pcenter.add(pbutton);

            frameshowon();
        }
    }

    private class Loadadventurepanel {

        public Loadadventurepanel() {
            InterfaceDBMS db = new DBConnection();

            Vector<String> namepg = db.LoadName();

            pcenter.removeAll();
            pcenter.repaint();

            JPanel loadpg = new JPanel();
            JLabel lnamepg = new JLabel("I tuoi personaggi :");

            JScrollPane scroll = null;
            JTextArea read = new JTextArea();
            read.setEditable(false);
            read.setFont(new Font("Serif", Font.ROMAN_BASELINE, 13));
            read.setLineWrap(true);
            read.setWrapStyleWord(true);
            scroll = new JScrollPane(read);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scroll.setPreferredSize(new Dimension(300, 200));

            if (!namepg.isEmpty()) {
                for (int i = 0; i < namepg.size(); i++) {
                    read.setText(namepg.get(i));
                }
                db.close();
            } else {
                read.setText("Non ci sono partite salvate.");
                db.close();
            }
            JPanel pbutton = new JPanel();
            JButton load = new JButton("Carica");
            JLabel labelload = new JLabel("Scegli pg:");
            selectpg = new JTextField("", 20);

            load.addActionListener(new Welcomehall.LoadOldadventure());

            loadpg.setLayout(new FlowLayout());
            loadpg.add(lnamepg);
            loadpg.add(scroll);
            loadpg.setOpaque(false);

            pbutton.setLayout(new FlowLayout());
            pbutton.add(labelload);
            pbutton.add(selectpg);
            pbutton.add(load);
            pbutton.setOpaque(false);

            pcenter.add(loadpg);
            pcenter.add(pbutton);

            frameshowon();
        }
    }

    private class LoadOldadventure implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!selectpg.getText().isEmpty()) {
                InterfaceDBMS db = new DBConnection();

                PgLoad pgrs = db.LoadPg(selectpg.getText());

                if (pgrs != null) {
                    close();
                    music.stopmusic();
                    GameManager gamemanager = new GameManager(pgrs);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Personaggio non trovato tra i salvataggi",
                            "Nome sbagliato",
                            JOptionPane.ERROR_MESSAGE);
                }
                db.close();
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Devi prima scegliere un personaggio",
                        "Campo vuoto",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private class StartNewadventure implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e2) {

            if (tnamepg.getText().length() > 3 && !cbdifficulty.getSelectedItem().equals("")) {
                //Pg(String name, int str, int des, int cos, int sma, int fed, int lv, Armor armor, Weapon weapon, int hpcurr, int hptot, Vector<String> bab)

                Readtofile<Armor> readar = new Readtofile<>("armor.dat");
                Readtofile<Weapon> readwe = new Readtofile<>("weapon.dat");

                Armor armor = null;
                Weapon weapon = null;

                int checknameState = 0;
                int idpg;
                Random rand = new Random();
                InterfaceDBMS db = new DBConnection();
                
                do {
                    idpg = rand.nextInt(99) + 1;
                    checknameState = db.CheckName(tnamepg.getText().toLowerCase(),idpg);
                } while (checknameState == 2);
                    db.close();
                if (checknameState == 0) {
                    
                    do {
                        armor = readar.readtofile();
                        if (armor.getName().equals("armatura di pelle")) {
                            break;
                        }
                    } while (armor != null);

                    do {
                        weapon = readwe.readtofile();
                        if (weapon.getName().equals("spada corta")) {
                            break;
                        }
                    } while (weapon != null);

                    if (cbdifficulty.getSelectedItem().equals("facile")) {
                        pg = new Pg(tnamepg.getText().toLowerCase(), 18, 18, 18, 18, 18, 1, armor, weapon, 12, 12, null);
                    }
                    if (cbdifficulty.getSelectedItem().equals("normale")) {
                        pg = new Pg(tnamepg.getText().toLowerCase(), 15, 15, 15, 15, 15, 1, armor, weapon, 10, 10, null);
                    }
                    if (cbdifficulty.getSelectedItem().equals("difficile")) {
                        pg = new Pg(tnamepg.getText().toLowerCase(), 12, 12, 12, 12, 12, 1, armor, weapon, 9, 9, null);
                    }
                    pg.addMO(200);
                    pg.addMiracle("calore");
                    pg.setIdpg(idpg);
                    music.stopmusic();
                    close();
                    GameManager gamemanager = new GameManager(pg);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Questo nome e' gia' utilizzato",
                            "Nome occupato",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Devi prima compilare tutti i campi",
                        "Campo vuoto",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

}
