package VirtualWorldJava.Plants;

import VirtualWorldJava.World;
import java.awt.*;

public class Trawa extends Plant{

    public Trawa(World current_world, int x, int y) {
        super(current_world,0,x, y);
        this.color = new Color(91,253,66);
        this.name = "Trawa";
        this.sign = "T";
    }
}
