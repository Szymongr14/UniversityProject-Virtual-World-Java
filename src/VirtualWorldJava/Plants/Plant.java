package VirtualWorldJava.Plants;
import VirtualWorldJava.Animals.Czlowiek;
import VirtualWorldJava.Organism;
import VirtualWorldJava.World;
import java.util.Random;

public abstract class Plant extends Organism{

    Plant(World current_world,int strength,int x, int y) {
        super(current_world,strength, 0, x,y,1);
    }

    @Override
    public void action() {
        int chance = (int) Math.floor(Math.random() *(100 - 1 + 1) + 1);
        if(chance<10){
            int new_x = this.x;
            int new_y = this.y;
            int direction = (int) Math.floor(Math.random() *(4) + 0);
            switch (direction) {
                case 0 -> new_x--;
                case 1 -> new_x++;
                case 2 -> new_y--;
                case 3 -> new_y++;
            }
            if(current_world.isPositionEmptyAndValid(new_x,new_y)){
                String message = "Zasiano rosline: "+this.name;
                //current_world.getAppGUI().returnInformationContainer().addMessage(message);
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
        if(attacker.getStrength() < this.getStrength()) {
            String message = "Roslina: "+this.name+" zabila: "+attacker.getName();
            current_world.getAppGUI().returnInformationContainer().addMessage(message);
            attacker.setAlive(false);
            current_world.removeOrganism(attacker);
            if(attacker instanceof Czlowiek){
               current_world.setGame_status(false);
            }
        }
    else {
            String message = "Roslina: "+this.name+" zostala zjedzone przez: "+attacker.getName();
            current_world.getAppGUI().returnInformationContainer().addMessage(message);
            current_world.moveOrganism(attacker, this.getX(), this.getY());
            this.setAlive(false);
        }
    }
}
