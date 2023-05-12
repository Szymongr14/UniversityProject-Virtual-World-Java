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
        int chance_to_move = (int) Math.floor(Math.random() *(4) +0);
        if(chance_to_move == 0){
            super.action();
        }
    }

    @Override
    public Organism clone() {
        return new Zolw(this.current_world, this.getX(), this.getY(), 1);
    }
}
