package VirtualWorldJava.Plants;
import VirtualWorldJava.Organism;
import VirtualWorldJava.World;
import java.util.Random;

public abstract class Plant extends Organism{

    Plant(World current_world,int strength,int x, int y) {
        super(current_world,strength, 0, x,y,1);
    }

    @Override
    public void action() {
        Random rand = new Random(System.currentTimeMillis());
        int chance = rand.nextInt(100);
        if(chance<10){
            int new_x = this.x;
            int new_y = this.y;
            int direction = rand.nextInt(4);
            switch (direction) {
                case 0 -> new_x--;
                case 1 -> new_x++;
                case 2 -> new_y--;
                case 3 -> new_y++;
            }
            if(current_world.isPositionValid(new_x,new_y)){
                String message = "Zasiano rosline: "+this.name;
                current_world.getAppGUI().returnInformationContainer().addMessage(message);
                if(this instanceof Trawa){
                    current_world.addOrganism(new Trawa(current_world,new_x,new_y),new_x,new_y);
                }
                else if(this instanceof Guarana){
                    current_world.addOrganism(new Guarana(current_world,new_x,new_y) ,new_x,new_y);
                }
                else if(this instanceof Mlecz){
                    current_world.addOrganism(new Mlecz(current_world,new_x,new_y),new_x,new_y);
                }
                else if(this instanceof Jagody){
                    current_world.addOrganism(new Jagody(current_world,new_x,new_y),new_x,new_y);
                }
                else if(this instanceof Barszcz){
                    current_world.addOrganism(new Barszcz(current_world,new_x,new_y),new_x,new_y);
                }
            }
        }
    }

    @Override
    public void collision(Organism attacker) {
    }
}
