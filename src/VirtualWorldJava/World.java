package VirtualWorldJava;
import VirtualWorldJava.Animals.*;
import VirtualWorldJava.Plants.*;

import java.awt.*;
import java.util.Random;

import java.util.Vector;

public class World {
    private int turn=1,height=0,width=0,cooldown=0,humanAbilityTime=0;
    private boolean game_status = true;
    private Vector<Vector<Organism>> Organisms;
    private Vector<Vector<AppGUI.boardField>> Board;
    private AppGUI appGUI;
    private Organism human;
    public AppGUI getAppGUI() {
        return appGUI;
    }

    public void setAppGUI(AppGUI appGUI) {
        this.appGUI = appGUI;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getHumanAbilityTime() {
        return humanAbilityTime;
    }

    public void setHumanAbilityTime(int humanAbilityTime) {
        this.humanAbilityTime = humanAbilityTime;
    }

    public boolean isGame_status() {
        return game_status;
    }

    public void setGame_status(boolean game_status) {
        this.game_status = game_status;
    }

    public Vector<Vector<Organism>> getOrganisms() {
        return Organisms;
    }

    public void setOrganisms(Vector<Vector<Organism>> organisms) {
        Organisms = organisms;
    }

    public Vector<Vector<AppGUI.boardField>> getBoard() {
        return Board;
    }

    public void setBoard(Vector<Vector<AppGUI.boardField>> board) {
        Board = board;
    }


    public World(int height, int width) {
        this.height = height;
        this.width = width;

        //create and fill Organisms vector
        Organisms = new Vector<Vector<Organism>>(height);
        for (int i = 0; i < height; i++) {
            Vector<Organism> row = new Vector<Organism>(width);
            for (int j = 0; j < width; j++) {
                row.add(null);
            }
            Organisms.add(row);
        }

        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(Organisms.get(i).get(j)==null) System.out.println("null");
                else System.out.println(Organisms.get(i).get(j));
            }
        }

        //create and fill Board vector
        Board = new Vector<Vector<AppGUI.boardField>>(height);
        for (int i = 0; i < height; i++) {
            Vector<AppGUI.boardField> row = new Vector<AppGUI.boardField>(width);
            for (int j = 0; j < width; j++) {
                row.add(new AppGUI.boardField(null));


            }
            Board.add(row);
        }
        human = new Czlowiek(this , -1, -1,20);
        placeOnRandomPosition(human);
        cooldown = 0;

        //placing first animals
        for(int i = 0; i < 2; i++) {
            placeOnRandomPosition(new Antylopa(this, -1, -1, 7));
            placeOnRandomPosition(new Lis(this, -1, -1, 7));
            placeOnRandomPosition(new Wilk(this, -1, -1, 9));
            placeOnRandomPosition(new Zolw(this, -1, -1, 40));
            placeOnRandomPosition(new Owca(this, -1, -1, 4));
        }

        //placing first plants
        placeOnRandomPosition(new Trawa(this, -1, -1));
        placeOnRandomPosition(new Trawa(this, -1, -1));
        placeOnRandomPosition(new Mlecz(this, -1, -1));
        placeOnRandomPosition(new Guarana(this, -1, -1));
        placeOnRandomPosition(new Barszcz(this, -1, -1));
        placeOnRandomPosition(new Jagody(this, -1, -1));
    }


    void placeOnRandomPosition(Organism organism){
        Random rand = new Random(System.currentTimeMillis());
        int x,y;
        do{
            x=rand.nextInt(width);
            y=rand.nextInt(height);
        }while(Organisms.get(y).get(x)!=null);
        addOrganism(organism,x,y);

        //Board.get(y).set(x,new AppGUI.boardField(organism));
    }

    public void makeTurn(int move){
//        Organism [][]active_organisms = new Organism[height][width];
//        Organism [][]dead_organism = new Organism[height][width];
        Vector <Organism> active_organisms = new Vector<Organism>();
        Vector <Organism> dead_organisms = new Vector<Organism>();


        //updating acctive organisms
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                active_organisms.add(Organisms.get(i).get(j));
            }
        }


        switch(move){
            case 0:
                //TODO
                System.out.println("makeTurn0");
                break;
            case 1:
                //TODO
                System.out.println("makeTurn1");
                break;
            case 2:
                //TODO
                System.out.println("makeTurn2");
                break;
            case 3:
                //TODO
                System.out.println("makeTurn3");
                break;
            case 4:
                //TODO
                System.out.println("makeTurn4");
                break;
        }


    }

    void addOrganism(Organism organism, int x, int y){
        Organisms.get(y).set(x,organism);
        Board.get(y).set(x,organism.draw());
        organism.setX(x);
        organism.setY(y);
    }

    void MainLoopOfTheGame(){
        while(this.game_status){

        }
    }



}
