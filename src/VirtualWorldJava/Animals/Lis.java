package VirtualWorldJava.Animals;

import VirtualWorldJava.World;
import VirtualWorldJava.Organism;
import java.awt.*;

public class Lis extends Animal{
    public Lis(World current_world, int x, int y, int age) {
        super(current_world,3,7,x, y,age);
        this.color = Color.ORANGE;
        this.name = "Lis";
        this.sign = "L";
    }

    @Override
    public void action(){
        int temp_x = this.getX();
        int temp_y = this.getY();

        int move = (int) Math.floor(Math.random() *(4) +0);
        switch (move) {
            case 0 -> {
                if (current_world.isPositionValid(temp_x, temp_y - 1)) {
                    temp_y--;
                } else return;
            }
            case 1 -> {
                if (current_world.isPositionValid(temp_x, temp_y + 1)) {
                    temp_y++;
                } else return;
            }
            case 2 -> {
                if (current_world.isPositionValid(temp_x - 1, temp_y)) {
                    temp_x--;
                } else return;
            }
            case 3 -> {
                if (current_world.isPositionValid(temp_x + 1, temp_y)) {
                    temp_x++;
                } else return;
            }
        }

        if(current_world.getOrganism(temp_x,temp_y) != null) {
            if(current_world.getOrganism(temp_x,temp_y).getStrength() < this.getStrength()){
                current_world.moveOrganism(this, temp_x, temp_y);
            }
        }
        else {
            current_world.moveOrganism(this, temp_x, temp_y);
        }
    }

    @Override
    public Organism clone() {
        return new Lis(this.current_world, this.getX(), this.getY(), 1);
    }
}
