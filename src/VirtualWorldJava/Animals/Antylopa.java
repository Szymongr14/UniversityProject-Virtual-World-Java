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

    @Override
    public void action(){
        current_world.getAppGUI().returnInformationContainer().addMessage("Antylopa action");
        //System.out.println("Antylopa action");
    }

    @Override
    public Organism clone() {
        return null;
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println("Antylopa collision");
    }


}
