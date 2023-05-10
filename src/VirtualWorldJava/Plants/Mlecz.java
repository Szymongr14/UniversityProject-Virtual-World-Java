package VirtualWorldJava.Plants;

import VirtualWorldJava.Organism;
import VirtualWorldJava.World;

import java.awt.*;

public class Mlecz extends Plant{
    public Mlecz(World current_world, int x, int y) {
        super(current_world,0,x, y);
        this.color = new Color(247,247,150);
        this.name = "Mlecz";
        this.sign = "M";
    }

    @Override
    public void action(){
        System.out.println("Barszcz action");
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println("Barszcz collision");
    }
}
