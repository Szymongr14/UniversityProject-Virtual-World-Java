package VirtualWorldJava.Animals;

import VirtualWorldJava.Organism;
import VirtualWorldJava.World;

import java.awt.*;

public class Czlowiek extends Animal{
    public Czlowiek(World current_world,int x, int y,int age) {
        super(current_world,5,4,x, y,age);
        this.color = Color.RED;
        this.name = "Czlowiek";
        this.sign = "C";
    }

    public void action(int move){
        if(current_world.getCooldown() > 0){
            current_world.setCooldown(current_world.getCooldown() - 1);
        }
        if(current_world.getHumanAbilityTime() > 0){
            //std::cout<<std::endl<< "\033[1;35mSpecjalna umiejetnosc czlowieka aktywna. Pozostalo: "<<this->currentWorld->getHumanAbilityTime()<<" tur\033[0m";
            System.out.println("Specjalna umiejetnosc czlowieka aktywna. Pozostalo: "+current_world.getHumanAbilityTime()+" tur");
            current_world.setHumanAbilityTime(current_world.getHumanAbilityTime() - 1);
            strength--;
        }


        int temp_x = this.getX();
        int temp_y = this.getY();


        switch (move) {
            case 0 -> {
                //TODO
                if(current_world.isPositionValid(temp_x,temp_y-1)) {
                    temp_y--;
                    current_world.moveOrganism(this, temp_x, temp_y);
                }
                else return;
            }
            case 1 -> {
                //TODO
                if(current_world.isPositionValid(temp_x,temp_y+1)) {
                    temp_y++;
                    current_world.moveOrganism(this, temp_x, temp_y);
                }
                else return;
            }
            case 2 ->{
                //TODO
                if(current_world.isPositionValid(temp_x-1,temp_y)) {
                    temp_x--;
                    current_world.moveOrganism(this, temp_x, temp_y);
                }
                else return;
            }
            case 3 ->{
                //TODO
                if(current_world.isPositionValid(temp_x+1,temp_y)) {
                    temp_x++;
                    current_world.moveOrganism(this, temp_x, temp_y);
                }
                else return;
            }
            case 4 ->{
                if(current_world.getCooldown() == 0) {
                    current_world.setCooldown(10);
                    strength += 5;
                    current_world.setHumanAbilityTime(5);
                }
                else {
                    //std::cout<<"Przed aktywowaniem specjalnosci musisz odczekac jescze: "<<this->currentWorld->getCooldown()<<std::endl;
                    System.out.println("Przed aktywowaniem specjalnosci musisz odczekac jescze: "+current_world.getCooldown());
                    return;
                }
            }
        }
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println("Czlowiek collision");
    }

    @Override
    public Organism clone() {
        return null;
    }
}
