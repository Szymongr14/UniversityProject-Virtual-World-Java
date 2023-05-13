package VirtualWorldJava.Plants;
import VirtualWorldJava.World;

import java.awt.*;
public class Barszcz extends Plant{
    public Barszcz(World current_world, int x, int y) {
        super(current_world,7,x, y);
        this.color = new Color(118,250,197);
        this.name = "Barszcz";
        this.sign = "B";
    }
}
