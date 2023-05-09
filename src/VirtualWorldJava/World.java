package VirtualWorldJava;
import java.util.Random;

import java.util.Vector;

public class World {
    private int turn=1,height,width,cooldown=0,humanAbilityTime=0;
    private boolean game_status = true;
    private Vector<Vector<Organism>> Organisms;
    private Vector<Vector<AppGUI.boardField>> Board;

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

        //Board.get(y).set(x,new AppGUI.boardField(organism));
    }

    void addOrganism(Organism organism, int x, int y){
        Organisms.get(y).set(x,organism);
        Board.get(y).set(x,organism.draw());
        organism.setX(x);
        organism.setY(y);

    }

}
