package VirtualWorldJava.Animals;

import VirtualWorldJava.Organism;
import VirtualWorldJava.World;

import java.awt.*;

public class Czlowiek extends Animal{
    public Czlowiek(World current_world,int x, int y,int age) {
        super(current_world,5,4,x, y,age);
        this.color = Color.BLUE;
        this.name = "Czlowiek";
        this.sign = "C";
    }

    @Override
    public void action(){
        System.out.println("Czlowiek action");
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println("Czlowiek collision");
    }

    @Override
    public Organism clone() {
        return null;
    }
}
