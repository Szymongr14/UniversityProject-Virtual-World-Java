package VirtualWorldJava.Plants;

import VirtualWorldJava.Organism;
import VirtualWorldJava.World;

import java.awt.*;

public class Guarana extends Plant{
    public Guarana(World current_world, int x, int y) {
        super(current_world,0,x, y);
        this.color = new Color(255,204,255);
        this.name = "Guarana";
        this.sign = "G";
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
