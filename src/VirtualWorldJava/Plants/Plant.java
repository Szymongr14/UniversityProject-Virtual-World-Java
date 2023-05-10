package VirtualWorldJava.Plants;
import VirtualWorldJava.Organism;
import VirtualWorldJava.World;

public abstract class Plant extends Organism{

    Plant(World current_world,int strength,int x, int y) {
        super(current_world,strength, 0, x,y,1);
    }

    @Override
    public void action() {
    }

    @Override
    public void collision(Organism attacker) {
    }
}
