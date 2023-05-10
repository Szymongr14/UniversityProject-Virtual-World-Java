package VirtualWorldJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AppGUI extends JFrame implements ActionListener, KeyListener {
    private JMenuItem newGame, load, save, exit;
    private JMenu menu;
    private JFrame jFrame;
    private JPanel mainContainer;
    private LegendContainer legendContainer;

    private BoardContainer boardContainer;

    private InformationContainer informationContainer;
    AppGUI() {
        jFrame = new JFrame("Virtual World Java");
        //super("Virtual VirtualWorldJava.World Game");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 600);
        jFrame.setResizable(false);

        //menu
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        newGame = new JMenuItem("New game");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        menu.add(newGame);
        menu.add(load);
        menu.add(save);
        menu.add(exit);
        newGame.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        menuBar.add(menu);
        jFrame.setJMenuBar(menuBar);

        //main container
        mainContainer = new JPanel();
        mainContainer.setBackground(Color.orange);
        mainContainer.setLayout(null);
        jFrame.setLayout(new CardLayout());

        jFrame.addKeyListener(this);
        jFrame.add(mainContainer);
        jFrame.setVisible(true);
    }


    //main menu
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            int sizeX = Integer.parseInt(JOptionPane.showInputDialog(jFrame, "Podaj szerokosc swiata", "20"));
            int sizeY = Integer.parseInt(JOptionPane.showInputDialog(jFrame, "Podaj wysokosc swiata", "20"));
            System.out.println("sizeX: "+sizeX+" sizeY:"+ sizeY);
            new World(sizeY, sizeX);
        }

        else if (e.getSource() == load) {
            System.out.println("load");
        }

        else if (e.getSource() == save) {
            System.out.println("save");
        }

        else if (e.getSource() == exit) {
            System.exit(0);
        }
    }


    public static class boardField extends JButton {
        private Organism organism;

        public boardField(Organism organism) {

            this.organism = organism;
        }

        public Organism getOrganism() {
            return organism;
        }

        public void setOrganism(Organism organism) {
            this.organism = organism;
        }
    }


    public static class BoardContainer extends JPanel{
//        super();
//        setBounds(mainPanel.getX() + ODSTEP, mainPanel.getY() + ODSTEP,mainPanel.getHeight() * 5 / 6 - ODSTEP, mainPanel.getHeight() * 5 / 6 - ODSTEP);
//
//
//
//
//
//
//
//        for (int i = 0; i < sizeY; i++) {
//            for (int j = 0; j < sizeX; j++) {
//                this.add(polaPlanszy[i][j]);
//            }
//        }
//            this.setLayout(new GridLayout(sizeY, sizeX));
    }

    public static class InformationContainer extends JPanel{

    }

    public static class LegendContainer extends JPanel{

    }


    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode==KeyEvent.VK_UP){
            System.out.println("elo");
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode==KeyEvent.VK_UP){
            System.out.println("elo");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
