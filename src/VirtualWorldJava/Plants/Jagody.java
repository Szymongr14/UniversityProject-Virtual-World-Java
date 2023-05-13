package VirtualWorldJava.Plants;

import VirtualWorldJava.World;

import java.awt.*;

public class Jagody extends Plant{
    public Jagody(World current_world, int x, int y) {
        super(current_world,99,x, y);
        this.color = new Color(140,30,213);
        this.name = "Jagody";
        this.sign = "J";
    }
}
