package VirtualWorldJava;


public abstract class Organism {
    private int strength, initiative, age, x, y;
    private String name;
    private World current_world;
    private boolean alive = true;

    public Organism(World current_world, int strength, int initiative, int age, int x, int y) {
        this.strength = strength;
        this.initiative = initiative;
        this.age = age;
        this.x = x;
        this.y = y;
        this.current_world = current_world;
    }

    public abstract void action();

    public abstract void collision(Organism attacker);

    public AppGUI.boardField draw() {
        return null;
    }

    public void initParams() {
    }

    public void move() {
    }

    public void position() {
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
