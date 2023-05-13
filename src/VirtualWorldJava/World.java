package VirtualWorldJava;
import VirtualWorldJava.Animals.*;
import VirtualWorldJava.Plants.*;

import java.io.*;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class World {
    private int turn=0,height,width,cooldown,humanAbilityTime=0;
    private boolean game_status = true;
    private Vector<Vector<Organism>> Organisms;
    private Vector<Vector<AppGUI.boardField>> Board;
    private AppGUI appGUI;
    private Organism human;
    private boolean wait_for_turn_execution = false;


    public World(int height, int width, AppGUI appGUI) {
        this.height = height;
        this.width = width;
        this.appGUI = appGUI;

        //create and fill Organisms vector
        Organisms = new Vector<Vector<Organism>>(height);
        for (int i = 0; i < height; i++) {
            Vector<Organism> row = new Vector<Organism>(width);
            for (int j = 0; j < width; j++) {
                row.add(null);
            }
            Organisms.add(row);
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
        placeOnRandomPosition(new Mlecz(this, -1, -1));
        placeOnRandomPosition(new Guarana(this, -1, -1));
        placeOnRandomPosition(new Barszcz(this, -1, -1));
        placeOnRandomPosition(new Jagody(this, -1, -1));
    }


    public World(AppGUI appGUI,String fileName) throws FileNotFoundException {
        Scanner worldSave = new Scanner(new File(fileName));
//        System.out.println("Loading world from file: " + fileName);
//        String message = "Loading world from file: " + fileName;
//        getAppGUI().returnInformationContainer().addMessage(message);

        this.appGUI = appGUI;
        this.height = worldSave.nextInt();
        this.width = worldSave.nextInt();
        this.turn = worldSave.nextInt();
        this.cooldown = worldSave.nextInt();
        this.humanAbilityTime = worldSave.nextInt();

        Organisms = new Vector<Vector<Organism>>(height);
        for (int i = 0; i < height; i++) {
            Vector<Organism> row = new Vector<Organism>(width);
            for (int j = 0; j < width; j++) {
                row.add(null);
            }
            Organisms.add(row);
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

        while (worldSave.hasNext()) {
            String name = worldSave.next();
            int strength_val = worldSave.nextInt();
            int initiative_val = worldSave.nextInt();
            int positionX_val = worldSave.nextInt();
            int positionY_val = worldSave.nextInt();
            int age_val = worldSave.nextInt();

            switch (name) {
                case "Czlowiek" ->
                        placeOnPosition(new Czlowiek(this, strength_val, initiative_val, positionX_val, positionY_val, age_val), positionX_val, positionY_val);
                case "Antylopa" ->
                        placeOnPosition(new Antylopa(this, strength_val, initiative_val, positionX_val, positionY_val, age_val), positionX_val, positionY_val);
                case "Lis" ->
                        placeOnPosition(new Lis(this, strength_val, initiative_val, positionX_val, positionY_val, age_val), positionX_val, positionY_val);
                case "Wilk" ->
                        placeOnPosition(new Wilk(this, strength_val, initiative_val, positionX_val, positionY_val, age_val), positionX_val, positionY_val);
                case "Zolw" ->
                        placeOnPosition(new Zolw(this, strength_val, initiative_val, positionX_val, positionY_val, age_val), positionX_val, positionY_val);
                case "Owca" ->
                        placeOnPosition(new Owca(this, strength_val, initiative_val, positionX_val, positionY_val, age_val), positionX_val, positionY_val);
                case "Trawa" ->
                        placeOnPosition(new Trawa(this, positionX_val, positionY_val), positionX_val, positionY_val);
                case "Mlecz" ->
                        placeOnPosition(new Mlecz(this, positionX_val, positionY_val), positionX_val, positionY_val);
                case "Guarana" ->
                        placeOnPosition(new Guarana(this, positionX_val, positionY_val), positionX_val, positionY_val);
                case "Barszcz" ->
                        placeOnPosition(new Barszcz(this, positionX_val, positionY_val), positionX_val, positionY_val);
                case "Jagody" ->
                        placeOnPosition(new Jagody(this, positionX_val, positionY_val), positionX_val, positionY_val);
            }
        }
        worldSave.close();
    }


    public void saveWorld(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            String message = "Saving world to file: " + fileName;
            getAppGUI().returnInformationContainer().addMessage(message);

            writer.println(height);
            writer.println(width);
            writer.println(turn);
            writer.println(cooldown);
            writer.println(humanAbilityTime);

            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    if(Organisms.get(i).get(j) != null) {
                        Organism org = Organisms.get(i).get(j);
                        writer.println(org.getName() + " " + org.getStrength() + " " + org.getInitiative() + " " + org.getX() + " " + org.getY() + " " + org.getAge());
                    }
                }
            }

            message = "Game has been successfully saved to file: " + fileName;
            getAppGUI().returnInformationContainer().addMessage(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void placeOnRandomPosition(Organism organism){
        Random rand = new Random(System.currentTimeMillis());
        int x,y;
        do{
            x=rand.nextInt(width);
            y=rand.nextInt(height);
        }while(Organisms.get(y).get(x)!=null);
        Organisms.get(y).set(x,organism);
        Board.get(y).set(x,organism.draw());
        organism.setX(x);
        organism.setY(y);
    }

    public void makeTurn(int move){
        Vector <Organism> active_organisms = new Vector<Organism>();

        //updating active organisms
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(Organisms.get(i).get(j)!=null) {
                    active_organisms.add(Organisms.get(i).get(j));
                }
            }
        }

        //updating organisms' age
        for(Organism organism : active_organisms) {
            organism.setAge(organism.getAge() + 1);
        }

        // Sort organisms by initiative
        active_organisms.sort(new Comparator<Organism>() {
            @Override
            public int compare(Organism o1, Organism o2) {
                if (o2.getInitiative() != o1.getInitiative()) {
                    return o2.getInitiative() - o1.getInitiative();
                } else {
                    return o2.getAge() - o1.getAge();
                }
            }
        });

        // Making a turns
        for(Organism organism : active_organisms) {
            if(organism.isAlive())
                if(organism instanceof Czlowiek)
                    ((Czlowiek) organism).action(move);
                else
                    organism.action();
        }

        this.turn++;
    }

    public void addOrganism(Organism organism, int x, int y){
        Organisms.get(y).set(x,organism);
        organism.setX(x);
        organism.setY(y);
    }

    public boolean isPositionValid(int x, int y){
        return x>=0 && x<width && y>=0 && y<height;
    }

    public boolean isPositionEmptyAndValid(int x, int y){
        return isPositionValid(x,y) && Organisms.get(y).get(x)==null;
    }

    public void moveOrganism(Organism organism, int x, int y) {
        Organisms.get(organism.getY()).set(organism.getX(),null);
        Organisms.get(y).set(x,organism);
        organism.setX(x);
        organism.setY(y);
    }

    public void escapeToPosition(Organism organism, int x, int y) {
        Organisms.get(y).set(x,organism);
        organism.setX(x);
        organism.setY(y);
    }

    public void removeOrganism(Organism organism) {
        Organisms.get(organism.getY()).set(organism.getX(),null);
    }

    public Organism getOrganism(int x, int y) {
        return Organisms.get(y).get(x);
    }


    public int returnEmptyPositionAround(int x, int y){
        if(isPositionEmptyAndValid(x+1,y) && this.getOrganism(x+1,y) == null)
            return 1;
        if(isPositionEmptyAndValid(x-1,y) && this.getOrganism(x-1,y) == null)
            return 2;
        if(isPositionEmptyAndValid(x,y+1) && this.getOrganism(x,y+1)== null)
            return 3;
        if(isPositionEmptyAndValid(x,y-1) && this.getOrganism(x,y-1) == null)
            return 4;
        return 5;
    }

    void placeOnPosition(Organism organism, int x, int y){
        Organisms.get(y).set(x,organism);
        organism.setX(x);
        organism.setY(y);
    }


    public boolean isWait_for_turn() {return wait_for_turn_execution;}
    public void setWait_for_turn(boolean wait_for_turn_execution) {this.wait_for_turn_execution = wait_for_turn_execution;}
    public AppGUI getAppGUI() {
        return appGUI;
    }
    public void setAppGUI(AppGUI appGUI) {
        this.appGUI = appGUI;
    }
    public int getTurn() {
        return turn;
    }
    public void setTurn(int turn) {this.turn = turn;}
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {this.height = height;}
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {this.width = width;}
    public int getCooldown() {
        return cooldown;
    }
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
    public int getHumanAbilityTime() {return humanAbilityTime;}
    public void setHumanAbilityTime(int humanAbilityTime) {this.humanAbilityTime = humanAbilityTime;}
    public boolean isGame_status() {return game_status;}
    public void setGame_status(boolean game_status) {this.game_status = game_status;}
    public Vector<Vector<Organism>> getOrganisms() {return Organisms;}
    public Vector<Vector<AppGUI.boardField>> getBoard() {return Board;}

}
