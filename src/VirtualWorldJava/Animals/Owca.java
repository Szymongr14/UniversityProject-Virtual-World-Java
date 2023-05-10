package VirtualWorldJava.Animals;

import VirtualWorldJava.Organism;
import VirtualWorldJava.World;

import java.awt.*;

public class Owca extends Animal{
    public Owca(World current_world, int x, int y, int age) {
        super(current_world,4,4,x, y,age);
        this.color = Color.LIGHT_GRAY;
        this.name = "Owca";
        this.sign = "O";
    }

    @Override
    public void action(){
        System.out.println("Owca action");
    }

    @Override
    public Organism clone() {
        return null;
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println("Owca collision");
    }
}
