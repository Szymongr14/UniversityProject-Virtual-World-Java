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
    public Organism clone() {
        return new Owca(this.current_world, this.getX(), this.getY(), 1);
    }
}
