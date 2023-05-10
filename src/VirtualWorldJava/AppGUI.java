package VirtualWorldJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppGUI extends JFrame implements ActionListener, KeyListener, MouseListener {
    private JMenuItem newGame, load, save, exit;
    private Toolkit toolkit;
    private Dimension dimension;
    private int ODSTEP;
    private JMenu menu;
    private JFrame jFrame;
    private JPanel mainContainer;


    private LegendContainer legendContainer;

    private BoardContainer boardContainer;

    private InformationContainer informationContainer;
    private static World current_world = null;
    AppGUI() {
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        ODSTEP = dimension.height / 100;


        jFrame = new JFrame("Virtual World Java");
        //super("Virtual VirtualWorldJava.World Game");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 800);
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
            current_world = new World(sizeY, sizeX);
            this.CreateLayout();
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



    public static class boardField extends JLabel {
        private Organism organism;

        public boardField(Organism organism) {
            super();
            this.organism = organism;
            this.setOpaque(true);
            if(organism != null){
                this.setBackground(organism.getColor());
                this.setText(organism.getSign());

            }
            else {
                this.setBackground(Color.WHITE);
            }
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        public Organism getOrganism() {
            return organism;
        }

        public void setOrganism(Organism organism) {
            this.organism = organism;
        }
    }


    public  class BoardContainer extends JPanel{
        private int sizeX, sizeY;
        public BoardContainer(int sizeX, int sizeY){
            super();
            this.setBounds(mainContainer.getX() + ODSTEP, mainContainer.getY() + ODSTEP, mainContainer.getHeight() * 5 / 6 - ODSTEP, mainContainer.getHeight() * 5 / 6 - ODSTEP);
            this.setBackground(Color.PINK);
            this.sizeX = sizeX;
            this.sizeY = sizeY;

            //adding fields to layout
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    final int row = i;
                    final int col = j;
                    this.add(current_world.getBoard().get(i).get(j));
                    current_world.getBoard().get(i).get(j).addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(current_world.getOrganisms().get(row).get(col)==null){
                                System.out.println("Puste pole zostało kliknięte!");
                            }
                        }
                    });
                }
            }
            this.setLayout(new GridLayout(sizeY, sizeX));
        }
    }

    public  class InformationContainer extends JPanel{

    }

    public  class LegendContainer extends JPanel{

    }



    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(current_world.isGame_status()){
            switch (keyCode) {
                case KeyEvent.VK_UP -> current_world.makeTurn(0);
                case KeyEvent.VK_DOWN -> current_world.makeTurn(1);
                case KeyEvent.VK_LEFT -> current_world.makeTurn(2);
                case KeyEvent.VK_RIGHT -> current_world.makeTurn(3);
                case KeyEvent.VK_SPACE -> current_world.makeTurn(4);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    void CreateLayout(){
        boardContainer = new BoardContainer(current_world.getWidth(), current_world.getHeight());
        mainContainer.add(boardContainer);
        SwingUtilities.updateComponentTreeUI(jFrame);
        jFrame.requestFocusInWindow();
    }
}
