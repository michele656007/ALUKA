/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import I_O_to_file.Readtofile;
import assistant.GuideElement;
import assistant.MusicPlayer;
import character.Pg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author miche
 */
public class Gamescreen {

    private Pg pg = null;
    private JFrame frame = null;
    private Container container = null;
    private Boolean cangetcommand = false;

    //line_start elementi
    private JTree tree = null;
    private DefaultMutableTreeNode root = null;
    private DefaultMutableTreeNode healf = null;
    private DefaultMutableTreeNode sanity = null;
    private DefaultMutableTreeNode rhand = null;
    private DefaultMutableTreeNode lhand = null;
    private DefaultMutableTreeNode armor = null;
    private DefaultMutableTreeNode money = null;
    private DefaultMutableTreeNode spelbook = null;
    private DefaultMutableTreeNode miraclebook = null;
    private DefaultTreeModel treemodel = null;
    private JPanel ptree = null;
    //center elementi
    private JPanel pcenter = null;
    private JScrollPane scroll = null;
    private JTextArea read = null;
    private JTextArea write = null;
    private PressedEventsKeyboard keyevent;
    private MusicPlayer music = null;

    //end_pag elementi
    private JPanel pendp = null;
    private JButton bsound = null;

    //start_pag elementi
    private JPanel pstartp = null;

    //comando del player
    private String command = null;

    public Gamescreen(Pg pg) {
        this.pg = pg;
        this.frame = new JFrame("Pulsa Danura");
        container = this.frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(Color.WHITE);
        frame.setBounds(300, 100, 1000, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buildLinestart();
        buildCenter();
        buildEndPage();
        buildPageStart();
        this.frameshowon();
        music = new MusicPlayer();
        music.playintro();
    }

    private void buildLinestart() {

        root = new DefaultMutableTreeNode(this.pg.getName());
        tree = new JTree(root);
        healf = new DefaultMutableTreeNode("Salute:" + pg.getHpCurr() + "/" + pg.getHptot());
        root.add(healf);
        sanity = new DefaultMutableTreeNode("Salute Mentale:" + pg.getSanity() + "/" + pg.getSanityTot());
        root.add(sanity);
        if (pg.getWeapon() != null) {
            rhand = new DefaultMutableTreeNode("Mano destra:" + pg.getWeapon().getName());
            rhand.add(new DefaultMutableTreeNode("Danno:" + this.pg.getWeapon().getDamage()));
        } else {
            rhand = new DefaultMutableTreeNode("Mano destra:/");
        }
        root.add(rhand);
        if (pg.getShield() != null) {
            lhand = new DefaultMutableTreeNode("Mano sinistra:" + pg.getShield().getName());
            lhand.add(new DefaultMutableTreeNode("Difesa:" + this.pg.getShield().getDefence()));
        } else {
            lhand = new DefaultMutableTreeNode("Mano sinistra:/");
        }
        root.add(lhand);
        if (pg.getArmor() != null) {
            armor = new DefaultMutableTreeNode("Armatura:" + pg.getArmor().getName());
        } else {
            armor = new DefaultMutableTreeNode("Armatura:/");
        }
        root.add(armor);

        this.money = new DefaultMutableTreeNode("Monete:" + pg.getMo());
        root.add(money);

        this.spelbook = new DefaultMutableTreeNode("Incantesimi");
        root.add(spelbook);
        for (int i = 0; i < this.pg.getBookofSpel().size(); i++) {
            spelbook.add(new DefaultMutableTreeNode(pg.getBookofSpel().get(i)));
        }
        this.miraclebook = new DefaultMutableTreeNode("Miracoli");
        root.add(miraclebook);
        for (int i = 0; i < this.pg.getBookofMiracle().size(); i++) {
            miraclebook.add(new DefaultMutableTreeNode(pg.getBookofMiracle().get(i)));
        }
        treemodel = (DefaultTreeModel) this.tree.getModel();
        treemodel.reload();
        this.ptree = new JPanel();
        this.ptree.add(this.tree);
        this.ptree.setBackground(Color.white);
        container.add(ptree, BorderLayout.LINE_START);
    }

    public void reloadTree() {
        this.healf.setUserObject("Salute:" + pg.getHpCurr() + "/" + pg.getHptot());
        this.sanity.setUserObject("Salute Mentale:" + pg.getSanity() + "/" + pg.getSanityTot());
        this.armor.setUserObject(root);
        if (pg.getArmor() != null) {
            armor.setUserObject("Armatura:" + pg.getArmor().getName());
        } else {
            armor.setUserObject("Armatura:/");
        }
        if (pg.getWeapon() != null) {
            rhand.setUserObject("Mano destra:" + pg.getWeapon().getName());
            rhand.removeAllChildren();
            rhand.add(new DefaultMutableTreeNode("Danno:" + this.pg.getWeapon().getDamage()));
        } else {
            rhand.setUserObject("Mano destra:/");
            rhand.removeAllChildren();
        }
        if (pg.getShield() != null) {
            lhand.setUserObject("Mano sinistra:" + pg.getShield().getName());
            lhand.removeAllChildren();
            lhand.add(new DefaultMutableTreeNode("Difesa:" + this.pg.getShield().getDefence()));
        } else {
            lhand.setUserObject("Mano sinistra:/");
            lhand.removeAllChildren();
        }
        this.money.setUserObject("Monete:" + pg.getMo());
        this.spelbook.removeAllChildren();
        for (int i = 0; i < this.pg.getBookofSpel().size(); i++) {
            spelbook.add(new DefaultMutableTreeNode(pg.getBookofSpel().get(i)));
        }
        this.miraclebook.removeAllChildren();
        for (int i = 0; i < this.pg.getBookofMiracle().size(); i++) {
            miraclebook.add(new DefaultMutableTreeNode(pg.getBookofMiracle().get(i)));
        }
        this.treemodel.reload();
    }

    private void buildCenter() {

        JPanel plabel = new JPanel();
        JLabel label = new JLabel("Inserisci comando");
        plabel.add(label);
        this.pcenter = new JPanel();
        pcenter.setLayout(new BoxLayout(pcenter, BoxLayout.Y_AXIS));
        this.read = new JTextArea();
        read.setEditable(false);
        read.setFont(new Font("Serif", Font.ROMAN_BASELINE, 13));
        read.setLineWrap(true);
        read.setWrapStyleWord(true);
        this.scroll = new JScrollPane(read);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scroll.setPreferredSize(new Dimension(700, 300));
        this.write = new JTextArea();
        this.keyevent = new PressedEventsKeyboard();
        write.addKeyListener(keyevent);
        write.setEditable(true);
        write.setFont(new Font("Serif", Font.ROMAN_BASELINE, 13));

        pcenter.add(scroll);
        pcenter.add(plabel);
        pcenter.add(write);
        pcenter.setBackground(Color.white);
        this.container.add(pcenter, BorderLayout.CENTER);

    }

    private void buildEndPage() {

        this.bsound = new JButton("Disattiva audio");
        this.bsound.addActionListener(new OnoffSound());
        this.pendp = new JPanel();
        pendp.setLayout(new FlowLayout(FlowLayout.LEFT));
        pendp.add(bsound);
        this.container.add(pendp, BorderLayout.PAGE_END);
    }

    private void buildPageStart() {
        JMenuBar menuBar = new JMenuBar();
        JMenu subm;
        JMenu subGuide;
        JMenu menu = null;
        JMenuItem mItem = null;

        menu = new JMenu("Menu");
        subm = new JMenu("Salva & Esci");
        menuBar.add(menu);
        subGuide = new JMenu("Guida");
        mItem = new JMenuItem("GamePlay");
        mItem.addActionListener(new GuidaGamePlay());
        subGuide.add(mItem);
        mItem = new JMenuItem("Comandi");
        mItem.addActionListener(new GuidaCommand());
        subGuide.add(mItem);
        mItem = new JMenuItem("Azioni");
        mItem.addActionListener(new GuidaVerb());
        subGuide.add(mItem);
        menu.add(subGuide);
        menu.addSeparator();
        mItem = new JMenuItem("Salva");
        mItem.addActionListener(new SaveEvent());
        subm.add(mItem);
        mItem = new JMenuItem("Esci");
        mItem.addActionListener(new ExitEvent());
        subm.add(mItem);
        mItem = new JMenuItem("Carica");
        mItem.addActionListener(new LoadEvent());
        subm.add(mItem);
        menu.add(subm);
        pstartp = new JPanel();
        pstartp.setLayout(new FlowLayout(FlowLayout.LEFT));
        pstartp.add(menuBar);
        this.container.add(menuBar, BorderLayout.PAGE_START);

    }

    private class PressedEventsKeyboard implements KeyListener {

        @Override
        public void keyTyped(java.awt.event.KeyEvent e) {
        }

        @Override
        public void keyPressed(java.awt.event.KeyEvent e) {
        }

        @Override
        public void keyReleased(java.awt.event.KeyEvent e) {
            if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                command = write.getText();
                read.append("Tu: " + command);
                write.setText("");
                cangetcommand = true;
            }
        }
    }

    private class OnoffSound implements ActionListener {

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
            pendp.repaint();
        }
    }

    private class GuidaGamePlay implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            read.append("Muoversi: per muoversi c'e' una solo condizione: "
                    + "intorno a te non ci devono essere nemici."
                    + " Per muoversi effettivamente basta inserire "
                    + "i comandi nord,sud,est,ovest.\n"
                    + "Combattimenti: per combattere con la sapda "
                    + " basta usare il comando attacca e specificare"
                    + " il bersaglio se ci sono piu' nemici. Per usare "
                    + "magie o miracoli, basta utilizzare il loro nome"
                    + "come comando e nel caso speficifare l'obiettivo." + "\n");
            read.setCaretPosition(read.getDocument().getLength());

        }
    }

    private class GuidaCommand implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            read.append("bag: permette di controllare il contenuto della borsa.\n"
                    + "levelup: comando da utilizzare quando si sale di livello.\n"
                    + "scheda pg: permette di vedere le caratteristiche del personaggio.\n"
                    + "esamina: comando da utilizzare per leggere la descrizione di qualcosa, es esamina spada/magia/miracolo.\n"
                    + "salva: salva la partita, se la stanza non e' libera l'energia dei nemici vera' riprestinata.\n"
                    + "esci: chiude il gioco senza salvare.\n"
                    + "carica: permette di tornare al menu." + "\n");
            read.setCaretPosition(read.getDocument().getLength());
        }
    }

    private class GuidaVerb implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            read.append("prendi: permette di prendere un oggetto.\n"
                    + "lascia: permette di lasciare un oggetto\n"
                    + "guarda: permette di guardare la stanza\n"
                    + "leggi: permette di leggere un libro.\n"
                    + "compra: permette di comprare qualcosa.\n"
                    + "vendi:permette di vendere oggetti.\n"
                    + "attacca: permette di attaccare un nemico specificato se ci sono piu' nemici, o attaccare un nemico solo.\n"
                    + "parla: permette di parlare con le persone.\n"
                    + "chiedi: permette di chiedere particolari argomenti ai png.\n"
                    + "usa: permette di usare particolari oggetti come pozioni e lanciare incantesimi e miracoli.\n"
                    + "equipaggia: permette di equipaggiare armi,scudi e armature.\n"
                    + "migliora: permette da chi di dovere di migliorare le armi.\n");
            read.setCaretPosition(read.getDocument().getLength());

        }
    }

    private class LoadEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            command = "carica";
            cangetcommand = true;
        }
    }

    private class SaveEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            command = "salva";
            cangetcommand = true;
        }
    }

    private class ExitEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            frame.setVisible(false);
            frame.dispose();
            command = "esci";
            cangetcommand = true;
        }
    }

    public String getCommand() {
        this.cangetcommand = false;
        return this.command;
    }

    public Boolean canGetCommand() {
        return this.cangetcommand;
    }

    private void frameshowon() {
        frame.setVisible(true);
    }

    public void printMessage(String message) {
        this.read.append(message + "\n");
        read.setCaretPosition(read.getDocument().getLength());
    }

    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }
}
