package VirtualWorldJava.Animals;

import VirtualWorldJava.AppGUI;
import VirtualWorldJava.Organism;
import VirtualWorldJava.World;

import java.awt.*;

public class Antylopa extends Animal{
    public Antylopa(World current_world, int x, int y, int age) {
        super(current_world,4,4,x, y,age);
        this.color = Color.YELLOW;
        this.name = "Antylopa";
        this.sign = "A";
    }


    public Antylopa(World current_world, int initiative, int strength, int positionX, int positionY, int age){
        super(current_world,strength,initiative,positionX,positionY,age);
        this.color = Color.YELLOW;
        this.name = "Antylopa";
        this.sign = "A";
    }

    @Override
    public void action(){
        int temp_x = this.getX();
        int temp_y = this.getY();

        int move = (int) Math.floor(Math.random() *(8) +0);
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
            case 4 -> {
                if (current_world.isPositionValid(temp_x + 1, temp_y + 1)) {
                    temp_x++;
                    temp_y++;
                } else return;
            }
            case 5 -> {
                if (current_world.isPositionValid(temp_x - 1, temp_y - 1)) {
                    temp_x--;
                    temp_y--;
                } else return;
            }
            case 6 -> {
                if (current_world.isPositionValid(temp_x + 1, temp_y - 1)) {
                    temp_x++;
                    temp_y--;
                } else return;
            }
            case 7 -> {
                if (current_world.isPositionValid(temp_x - 1, temp_y + 1)) {
                    temp_x--;
                    temp_y++;
                } else return;
            }
        }

        if(current_world.getOrganism(temp_x,temp_y) != null) {
            current_world.getOrganism(temp_x,temp_y).collision(this);
        }
        else {
            current_world.moveOrganism(this, temp_x, temp_y);
        }
    }

    @Override
    public Organism clone() {
        return new Antylopa(current_world, this.getX(), this.getY(), 1);
    }

    @Override
    public void collision(Organism attacker) {
        if(attacker instanceof Antylopa){
            Organism newAnimal = this.clone();
            this.CollisionWithTheSameSpecies(newAnimal);
            return;
        }
        else if(attacker.getStrength() < this.getStrength()) {
            String message = "Zwierze: "+this.name+" zjadlo: "+attacker.getName();
            current_world.getAppGUI().returnInformationContainer().addMessage(message);
            attacker.setAlive(false);
            current_world.removeOrganism(attacker);
            if(attacker instanceof Czlowiek){
                current_world.setGame_status(false);
            }
        }
        else {
            if((int) Math.floor(Math.random() *(1) +0) == 0) {
                String message = "Zwierze: "+this.name+" zostalo zjedzone przez: "+attacker.getName();
                current_world.getAppGUI().returnInformationContainer().addMessage(message);
                current_world.moveOrganism(attacker, this.getX(), this.getY());
                this.setAlive(false);
            }
            else{
                String message = "Zwierze: "+this.name+" ucieklo przed: "+attacker.getName();
                current_world.getAppGUI().returnInformationContainer().addMessage(message);
                current_world.moveOrganism(attacker, this.getX(), this.getY());
                int position = this.current_world.returnEmptyPositionAround(this.getX(), this.getY());
                switch (position) {
                    case 1 -> this.current_world.escapeToPosition(this, this.getX() + 1, this.getY());
                    case 2 -> this.current_world.escapeToPosition(this, this.getX(), this.getY() - 1);
                    case 3 -> this.current_world.escapeToPosition(this, this.getX(), this.getY() + 1);
                    case 4 -> this.current_world.escapeToPosition(this, this.getX()-1, this.getY());
                    case 5 -> {
                        message = "Zwierze: " + attacker.getName() + " dopadlo: " + this.name;
                        current_world.getAppGUI().returnInformationContainer().addMessage(message);
                        this.setAlive(false);
                    }
                }
            }
        }
    }


}
