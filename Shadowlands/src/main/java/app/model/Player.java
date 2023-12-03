package app.model;

import javafx.scene.control.Alert;
import app.model.item.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Player implements Serializable {
    private String name;
    private int strength;
    private int health;
    private int durability;
    private int agility;
    private int fortune;
    private String race;
    private String playerClass;
    private int level;
    private int experience;
    private Map<String, Item> equippedItems;
    private List<Item> inventory;
    private int goldBalance;
    private int healthPotions;

    public Player(String name, int strength, int durability, int agility, int fortune, String race, String playerClass) {
        this.name = name;
        this.strength = strength;
        health = durability;
        this.durability = durability;
        this.agility = agility;
        this.fortune = fortune;
        this.race = race;
        this.playerClass = playerClass;
        level = 1;
        experience = 0;
        equippedItems = new HashMap<>(5);
        equippedItems.put("Broń", null);
        equippedItems.put("Hełm", null);
        equippedItems.put("Zbroja", null);
        equippedItems.put("Spodnie", null);
        equippedItems.put("Buty", null);
        inventory = new LinkedList<>();
        goldBalance = 0;
        healthPotions = 0;
    }
    public void equipItem(String slot, Item item) {
        if (item != null) {
            strength += item.getStrength();
            durability += item.getDurability();
            agility += item.getAgility();
            fortune += item.getFortune();
        }
        equippedItems.put(slot, item);
    }
    public void unequipItem(String slot) {
        Item item = equippedItems.get(slot);
        strength -= item.getStrength();
        durability -= item.getDurability();
        agility -= item.getAgility();
        fortune -= item.getFortune();
        equippedItems.put(slot, null);
    }
    public Item getEquippedItem(String slot) {
        return equippedItems.get(slot);
    }
    public void levelUp() {
        level++;
        strength += 10;
        durability += 10;
        agility += 10;
        fortune += 10;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Awans");
        alert.setHeaderText("Awansowałeś!\nAktualny poziom bohatera: " + level);
        alert.setContentText("Statystyki:" + "\nSiła: " + strength + " (+10)\nWytrzymałość: " + durability + " (+10)\nZręczność: " + agility + " (+10)\nSzczęście: " + fortune + " (+10)");
        alert.showAndWait();
    }
    public void gainExperience(int amount) {
        experience += amount;
        if (experience >= 20) {
            levelUp();
            experience = 0;
        }
    }
    public void takeDamage(int amount) {
        health -= amount;
    }
    public void heal(int amount) {
        health += amount;
    }
    public boolean isAlive() {
        if (this.health > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                ", health=" + health +
                ", durability=" + durability +
                ", agility=" + agility +
                ", fortune=" + fortune +
                ", race='" + race + '\'' +
                ", playerClass='" + playerClass + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", inventory=" + equippedItems +
                ", goldBalance=" + goldBalance +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getFortune() {
        return fortune;
    }

    public String getRace() {
        return race;
    }

    public String getPlayerClass() {
        return playerClass;
    }
    public int getGoldBalance() {
        return goldBalance;
    }

    public void earnGold(int amount) {
        goldBalance += amount;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Złoto");
        alert.setHeaderText("Otrzymałeś złoto!\nAktualna ilość złota: " + goldBalance);
        alert.showAndWait();
    }
    public void spendGold(int amount) {
        goldBalance -= amount;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setFortune(int fortune) {
        this.fortune = fortune;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Map<String, Item> getEquippedItems() {
        return equippedItems;
    }
    public void addHealthPotion() {
        if (goldBalance < 20) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Za mało złota!");
            alert.setContentText("Nie stać cię na ten przedmiot");
            alert.showAndWait();
        } else {
            spendGold(20);
            healthPotions++;
        }
    }

    public int getHealthPotions() {
        return healthPotions;
    }

    public int getDurability() {
        return durability;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHealthPotions(int healthPotions) {
        this.healthPotions = healthPotions;
    }
}
