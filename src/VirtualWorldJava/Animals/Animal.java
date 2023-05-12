package VirtualWorldJava.Animals;


import VirtualWorldJava.Organism;
import VirtualWorldJava.World;

public abstract class Animal extends Organism {

    public Animal(World current_world,int strength, int initiative,int x, int y, int age) {
        super(current_world,strength, initiative,x, y,age);
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
            current_world.getOrganism(temp_x,temp_y).collision(this);
        }
        else {
            current_world.moveOrganism(this, temp_x, temp_y);
        }
    }

    @Override
    public void collision(Organism attacker) {
        if (this.getClass() == attacker.getClass()) {
            Organism newAnimal = this.clone();
            this.CollisionWithTheSameSpecies(newAnimal);
            return;
        }
        else if(attacker.getStrength() < this.getStrength()) {
            String message = "Zwierze: " + this.getName() + " zjadlo: " + attacker.getName();
            this.current_world.getAppGUI().returnInformationContainer().addMessage(message);
            attacker.setAlive(false);
            current_world.removeOrganism(attacker);
            if(attacker instanceof Czlowiek){
                current_world.setGame_status(false);
            }
        }
        else {
            String message = "Zwierze: " + this.getName() + " zostalo zjedzone przez: " + attacker.getName();
            this.current_world.getAppGUI().returnInformationContainer().addMessage(message);
            this.current_world.moveOrganism(attacker, this.getX(), this.getY());
            this.setAlive(false);
            if(this instanceof Czlowiek){
                current_world.setGame_status(false);
            }
        }
    }

    public abstract Organism clone();

    public void CollisionWithTheSameSpecies(Organism organismToCreate){
        String message = "Powstal nowy/a: " + this.getName();
        this.current_world.getAppGUI().returnInformationContainer().addMessage(message);

        int position = current_world.returnEmptyPositionAround(this.getX(), this.getY());
        switch (position) {
            case 1 -> {
                organismToCreate.setX(this.getX() + 1);
                organismToCreate.setY(this.getY());
                this.current_world.addOrganism(organismToCreate, this.getX() + 1, this.getY());
            }
            case 2 -> {
                organismToCreate.setX(this.getX() - 1);
                organismToCreate.setY(this.getY());
                this.current_world.addOrganism(organismToCreate, this.getX() - 1, this.getY());
            }
            case 3 -> {
                organismToCreate.setX(this.getX());
                organismToCreate.setY(this.getY() + 1);
                this.current_world.addOrganism(organismToCreate, this.getX(), this.getY() + 1);
            }
            case 4 -> {
                organismToCreate.setX(this.getX());
                organismToCreate.setY(this.getY() - 1);
                this.current_world.addOrganism(organismToCreate, this.getX(), this.getY() - 1);
            }
            case 5 -> {
                message = "Mlody/a: " + this.getName() + " nie przetrwal/a :(";
                this.current_world.getAppGUI().returnInformationContainer().addMessage(message);
            }
        }
    }
}
