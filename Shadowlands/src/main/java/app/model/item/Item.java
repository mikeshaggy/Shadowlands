package app.model.item;

import java.io.Serializable;

public abstract class Item implements Serializable {
    private String name;
    private int strength;
    private int durability;
    private int agility;
    private int fortune;
    private int price;

    public Item(String name, int strength, int durability, int agility, int fortune, int price) {
        this.name = name;
        this.strength = strength;
        this.durability = durability;
        this.agility = agility;
        this.fortune = fortune;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getDurability() {
        return durability;
    }

    public int getAgility() {
        return agility;
    }

    public int getFortune() {
        return fortune;
    }

    public int getPrice() {
        return price;
    }
}
