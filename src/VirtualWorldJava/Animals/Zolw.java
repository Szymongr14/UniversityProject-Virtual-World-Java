package VirtualWorldJava.Animals;

import VirtualWorldJava.Organism;
import VirtualWorldJava.World;

import java.awt.*;

public class Zolw  extends Animal{
    public Zolw(World current_world, int x, int y, int age) {
        super(current_world,9,5,x, y,age);
        this.color = new Color(95,125,39);
        this.name = "Zolw";
        this.sign = "Z";
    }

    @Override
    public void action(){
        System.out.println("Zolw action");
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println("Zolw collision");
    }

    @Override
    public Organism clone() {
        return null;
    }
}
