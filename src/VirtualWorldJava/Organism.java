package VirtualWorldJava;
import java.awt.*;

public abstract class Organism {
    protected int strength, initiative, age, x, y;
    protected String name;
    protected Color color;
    protected World current_world;
    protected String sign;


    public Organism(World current_world, int strength, int initiative,int x, int y, int age) {
        this.strength = strength;
        this.initiative = initiative;
        this.age = age;
        this.x = x;
        this.y = y;
        this.current_world = current_world;
    }

    public abstract void action();

    public abstract void collision(Organism attacker);


    public String getSign() {return sign;}
    public void setSign(String sign) {this.sign = sign;}
    public int getStrength() {return strength;}
    public int getInitiative() {return initiative;}
    public int getAge() {return age;}
    public int getX() {return x;}
    public int getY() {return y;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Color getColor() {return color;}
    public void setStrength(int strength) {this.strength = strength;}
    public void setInitiative(int initiative) {this.initiative = initiative;}
    public void setAge(int age) {this.age = age;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setColor(Color color) {this.color = color;}
    public World getCurrent_world() {return current_world;}
    public void setCurrent_world(World current_world) {this.current_world = current_world;}
    public boolean isAlive() {return alive;}
    public void setAlive(boolean alive) {this.alive = alive;}
    private boolean alive = true;
    public AppGUI.boardField draw() {
        return new AppGUI.boardField(this);
    }

}
