package app.model.enemy;

public abstract class Enemy {
    private final String name;
    private int health;
    private final int strength;

    public Enemy(String name, int health, int strength) {
        this.name = name;
        this.health = health;
        this.strength = strength;
    }
    public boolean isAlive() {
        return this.health > 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }
    public void takeDamage(int amount) {
        health -= amount;
    }
}
