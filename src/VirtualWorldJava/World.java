package VirtualWorldJava;
import java.util.Random;

import java.util.Vector;

public class World {
    private int turn=1,height,width,cooldown=0,humanAbilityTime=0;
    private boolean game_status = true;
    private Vector<Vector<Organism>> Organisms;
    private Vector<Vector<AppGUI.boardField>> Board;


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

//        placeOnRandomPosition(new Czlowiek(this, -1, -1, 20));
//        cooldown = 0;
//
//        //TODO placing 2 of each species
//        for(int i = 0; i < 2; i++) {
//            placeOnRandomPosition(new Antylopa(this, -1, -1, 7));
//            placeOnRandomPosition(new Lis(this, -1, -1, 7));
//            placeOnRandomPosition(new Wilk(this, -1, -1, 9));
//            placeOnRandomPosition(new Zolw(this, -1, -1, 40));
//            placeOnRandomPosition(new Owca(this, -1, -1, 4));
//        }
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

    void makeTurn(){

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
