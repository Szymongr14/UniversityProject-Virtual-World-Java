package VirtualWorldJava;

import VirtualWorldJava.Animals.*;
import VirtualWorldJava.Plants.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class AppGUI extends JFrame implements ActionListener, KeyListener, MouseListener {
    private JMenuItem newGame, load, save, exit;
    private Toolkit toolkit;
    private Dimension dimension;
    private int ODSTEP;
    private JList list;
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


        jFrame = new JFrame("Virtual World Java - Szymon Groszkowski 193141");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1000, 900);
        jFrame.setResizable(false);
        jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("nature_icon.png")));

        //menu
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        newGame = new JMenuItem("New game");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        menu.add(newGame);
        menu.add(load);
        menu.add(exit);
        newGame.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        menuBar.add(menu);
        jFrame.setJMenuBar(menuBar);

        //main container
        mainContainer = new JPanel();
        mainContainer.setBackground(new Color(46, 143, 47));
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
            int sizeX = Integer.parseInt(JOptionPane.showInputDialog(jFrame, "Podaj szerokosc swiata [min 4]", "20"));
            int sizeY = Integer.parseInt(JOptionPane.showInputDialog(jFrame, "Podaj wysokosc swiata [min 4]", "20"));
            while (sizeX < 4 && sizeY < 4){
                JOptionPane.showMessageDialog(jFrame, "Rozmiar swiata musi byc wiekszy niz 4x4");
                sizeX = Integer.parseInt(JOptionPane.showInputDialog(jFrame, "Podaj szerokosc swiata [min 4]", "20"));
                sizeY = Integer.parseInt(JOptionPane.showInputDialog(jFrame, "Podaj wysokosc swiata [min 4]", "20"));
            }
            current_world = new World(sizeY, sizeX,this);
            this.CreateLayout();
            menu.add(save,2);

        }

        else if (e.getSource() == load) {
            String file_name = JOptionPane.showInputDialog(null, "Enter a filename:", "Input", JOptionPane.PLAIN_MESSAGE);
            if (file_name != null && !file_name.isEmpty()) {
                try {
                    current_world = new World(this, file_name);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(jFrame, "Podany plik nie istnieje!");
                    throw new RuntimeException(ex);
                }
                this.CreateLayout();
                boardContainer.refreshBoard();
                String message = "Loading world from file: " + file_name;
                informationContainer.addMessage(message);
            }
        }

        else if (e.getSource() == save) {
            String file_name = JOptionPane.showInputDialog(null, "Enter a filename:", "Input", JOptionPane.PLAIN_MESSAGE);
            if (file_name != null && !file_name.isEmpty()) {
                System.out.println("User entered: " + file_name);
                current_world.saveWorld(file_name);
            }
        }

        else if (e.getSource() == exit) {
            System.exit(0);
        }
    }


    public class OrganismAddList extends JFrame implements ActionListener, ListSelectionListener {
        private final JList<String> jList;
        private final JFrame frame;

        public OrganismAddList(int x, int y) {
            frame = new JFrame("Dodaj organizm");
            frame.setBounds(100,200, 270, 250);
            String[] listaOrganizmow = new String[]{"Barszcz Sosnowskiego", "Guarana", "Mlecz", "Trawa",
                    "Wilcze Jagody", "Antylopa", "Lis", "Owca", "Wilk", "Zolw"
            };
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String item : listaOrganizmow) {
                listModel.addElement(item);
            }
            jList = new JList<>(listModel);
            jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        String selectedItem = jList.getSelectedValue();
                        String message="";
                        switch (selectedItem) {
                            case "Barszcz Sosnowskiego" -> {
                                current_world.addOrganism(new Barszcz(current_world, x, y), x, y);
                                message = "Dodano Barszcz na pozycje: " + x + ", " + y;
                            }
                            case "Guarana" -> {
                                current_world.addOrganism(new Guarana(current_world, x, y), x, y);
                                message = "Dodano Gurane na pozycje: " + x + ", " + y;
                            }
                            case "Mlecz" -> {
                                current_world.addOrganism(new Mlecz(current_world, x, y), x, y);
                                message = "Dodano Mlecz na pozycje: " + x + ", " + y;
                            }
                            case "Trawa" -> {
                                current_world.addOrganism(new Trawa(current_world, x, y), x, y);
                                message = "Dodano Trawe na pozycje: " + x + ", " + y;
                            }
                            case "Wilcze Jagody" -> {
                                current_world.addOrganism(new Jagody(current_world, x, y), x, y);
                                message = "Dodano Jagody na pozycje: " + x + ", " + y;
                            }
                            case "Antylopa" -> {
                                current_world.addOrganism(new Antylopa(current_world, x, y, 1), x, y);
                                message = "Dodano Antylope na pozycje: " + x + ", " + y;
                            }
                            case "Lis" -> {
                                current_world.addOrganism(new Lis(current_world, x, y, 1), x, y);
                                message = "Dodano Lisa na pozycje: " + x + ", " + y;
                            }
                            case "Owca" -> {
                                current_world.addOrganism(new Owca(current_world, x, y, 1), x, y);
                                message = "Dodano Owce na pozycje: " + x + ", " + y;
                            }
                            case "Wilk" -> {
                                current_world.addOrganism(new Wilk(current_world, x, y, 1), x, y);
                                message = "Dodano Wilka na pozycje: " + x + ", " + y;
                            }
                            case "Zolw" -> {
                                current_world.addOrganism(new Zolw(current_world, x, y, 1), x, y);
                                message = "Dodano Zolwia na pozycje: " + x + ", " + y;
                            }
                        }
                        current_world.getAppGUI().informationContainer.addMessage(message);
                        boardContainer.refreshBoard();
                        frame.setVisible(false);
                    }
                }
            });
            JScrollPane scrollPane = new JScrollPane(jList);
            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
            frame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {}
        @Override
        public void valueChanged(ListSelectionEvent e) {}
    }

    public InformationContainer returnInformationContainer(){return informationContainer;}
    public BoardContainer getBoardContainer() {return boardContainer;}


    public static class boardField extends JLabel {
        private Organism organism;

        public boardField(Organism organism) {
            super();
            this.organism = organism;
            this.setOpaque(true);
            if(organism != null){
                this.setBackground(organism.getColor());
                this.setText(organism.getSign());
                this.setVerticalAlignment(SwingConstants.CENTER);
                this.setHorizontalAlignment(SwingConstants.CENTER);
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
            this.setBackground(Color. BLACK);
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
                                OrganismAddList listaOrganizmow = new OrganismAddList(col,row);
                            }
                        }
                    });
                }
            }
            this.setLayout(new GridLayout(sizeY, sizeX));
        }

        public void refreshBoard(){
            for(int i=0; i<sizeY; i++){
                for(int j=0; j<sizeX; j++){
                    if(current_world.getOrganisms().get(i).get(j)!=null){
                        current_world.getBoard().get(i).get(j).setText(current_world.getOrganisms().get(i).get(j).getSign());
                        current_world.getBoard().get(i).get(j).setBackground(current_world.getOrganisms().get(i).get(j).getColor());
                        current_world.getBoard().get(i).get(j).setVerticalAlignment(SwingConstants.CENTER);
                        current_world.getBoard().get(i).get(j).setHorizontalAlignment(SwingConstants.CENTER);
                    }
                    else{
                        current_world.getBoard().get(i).get(j).setText("");
                        current_world.getBoard().get(i).get(j).setBackground(Color.WHITE);
                    }
                }
            }
            SwingUtilities.updateComponentTreeUI(jFrame);
        }
    }

    public class InformationContainer extends JPanel{
        private final String header = "     Strzalki - kierowanie czlowiekiem\n           r- aktywacja specjalnosci     \n\n\n     KOMUNIKATY O ZDARZENIACH: \n\n";
        private String text = header;
        private final JTextArea textArea;
        public InformationContainer(){
            super();
            this.setBounds(700+ODSTEP, ODSTEP, 260, mainContainer.getHeight()-ODSTEP*2);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
            textArea = new JTextArea(text);
            textArea.setEditable(false);
            textArea.setFont(new Font("Arial", Font.BOLD, 13));
            setLayout(new CardLayout());

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setMargin(new Insets(5, 5, 5, 5));
            JScrollPane sp = new JScrollPane(textArea);
            add(sp);
        }

        public void refreshMessages() {
            text = header+ "TURA ["+current_world.getTurn()+"] \n" + text;
            textArea.setText(text);
        }

        public void clearMessages() {
            text = "";
        }

        public void addMessage(String message){
            text += message+ "\n\n";
            textArea.setText(text);
        }
    }

    public class LegendContainer extends JPanel {
        private JLabel[] labels;

        LegendContainer() {
            super();
            setBackground(new Color(46, 143, 47));
            this.setBounds(ODSTEP, boardContainer.getHeight() + ODSTEP * 2, boardContainer.getWidth(), 120);
            this.setLayout(new GridLayout(2, 6, 10, 10));
            int species_number = 11;
            labels = new JLabel[species_number];
            Font font = new Font("Arial", Font.BOLD, 14);
            Color fontColor = Color.BLACK;

            labels[0] = new JLabel("Barszcz");
            labels[0].setBackground(new Color(118,250,197));

            labels[1] = new JLabel("Guarana");
            labels[1].setBackground(new Color(255,204,255));

            labels[2] = new JLabel("Mlecz");
            labels[2].setBackground(new Color(247,247,150));

            labels[3] = new JLabel("Trawa");
            labels[3].setBackground( new Color(91,253,66));

            labels[4] = new JLabel("Jagody");
            labels[4].setBackground(new Color(140,30,213));

            labels[5] = new JLabel("Antylopa");
            labels[5].setBackground(Color.YELLOW);

            labels[6] = new JLabel("Czlowiek");
            labels[6].setBackground(Color.RED);

            labels[7] = new JLabel("Lis");
            labels[7].setBackground(Color.ORANGE);

            labels[8] = new JLabel("Owca");
            labels[8].setBackground(Color.LIGHT_GRAY);

            labels[9] = new JLabel("Wilk");
            labels[9].setBackground(Color.GRAY);

            labels[10] = new JLabel("Zolw");
            labels[10].setBackground(new Color(95,125,39));


            for(int i = 0; i< species_number; i++){
                labels[i].setOpaque(true);
                labels[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                labels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                labels[i].setForeground(fontColor);
                labels[i].setFont(font);
                labels[i].setHorizontalAlignment(SwingConstants.CENTER);
                this.add(labels[i]);
            }
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
        informationContainer.clearMessages();
        int keyCode = e.getKeyCode();
        if(current_world.isGame_status() && !current_world.isWait_for_turn()){
            switch (keyCode) {
                case KeyEvent.VK_UP -> {
                    current_world.makeTurn(0);
                    current_world.setWait_for_turn(true);
                }
                case KeyEvent.VK_DOWN -> {
                    current_world.makeTurn(1);
                    current_world.setWait_for_turn(true);
                }
                case KeyEvent.VK_LEFT -> {
                    current_world.makeTurn(2);
                    current_world.setWait_for_turn(true);
                }
                case KeyEvent.VK_RIGHT -> {
                    current_world.makeTurn(3);
                    current_world.setWait_for_turn(true);
                }
                case KeyEvent.VK_R -> {
                    current_world.makeTurn(4);
                    current_world.setWait_for_turn(true);
                }
            }
            informationContainer.refreshMessages();
        }
        current_world.setWait_for_turn(false);
        boardContainer.refreshBoard();
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
        mainContainer.removeAll();
        boardContainer = new BoardContainer(current_world.getWidth(), current_world.getHeight());
        mainContainer.add(boardContainer);
        informationContainer = new InformationContainer();
        mainContainer.add(new LegendContainer());
        mainContainer.add(informationContainer);
        SwingUtilities.updateComponentTreeUI(jFrame);
        jFrame.requestFocusInWindow();
    }
}
