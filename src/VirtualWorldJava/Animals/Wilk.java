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

    public Wilk(World current_world, int initiative, int strength, int positionX, int positionY, int age){
        super(current_world,strength,initiative,positionX,positionY,age);
        this.color = Color.GRAY;
        this.name = "Wilk";
        this.sign = "W";
    }

    @Override
    public Organism clone() {
        return new Wilk(this.current_world, this.getX(), this.getY(), 1);
    }
}
