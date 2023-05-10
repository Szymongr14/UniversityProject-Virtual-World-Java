package VirtualWorldJava.Animals;

import VirtualWorldJava.World;
import VirtualWorldJava.Organism;

import java.awt.*;

public class Wilk extends Animal{
    public Wilk(World current_world, int x, int y, int age) {
        super(current_world,9,5,x, y,age);
        this.color = Color.GRAY;
        this.name = "Wilk";
        this.sign = "W";
    }

    @Override
    public void action(){
        System.out.println("Wilk action");
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println("Wilk collision");
    }

    @Override
    public Organism clone() {
        return null;
    }
}
