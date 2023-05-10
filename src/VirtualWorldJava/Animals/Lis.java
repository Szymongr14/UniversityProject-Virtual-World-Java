package VirtualWorldJava.Animals;

import VirtualWorldJava.World;
import VirtualWorldJava.Organism;
import java.awt.*;

public class Lis extends Animal{
    public Lis(World current_world, int x, int y, int age) {
        super(current_world,3,7,x, y,age);
        this.color = Color.ORANGE;
        this.name = "Lis";
        this.sign = "L";
    }

    @Override
    public void action(){
        System.out.println("Lis action");
    }

    @Override
    public Organism clone() {
        return null;
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println("Lis collision");
    }
}
