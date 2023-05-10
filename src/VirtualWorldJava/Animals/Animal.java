package VirtualWorldJava.Animals;


import VirtualWorldJava.Organism;
import VirtualWorldJava.World;

public abstract class Animal extends Organism {

    public Animal(World current_world,int strength, int initiative,int x, int y, int age) {
        super(current_world,strength, initiative,x, y,age);
    }

    @Override
    public void action() {

    }

    @Override
    public void collision(Organism attacker) {
    }

    public abstract Organism clone();

    public void CollisionWithTheSameSpecies(Organism otherOrganism){

    }
}
