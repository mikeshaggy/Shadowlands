package app.utility;

import app.model.Player;
import app.model.enemy.Enemy;
import javafx.scene.control.Label;

public class CombatSystem {
    private static boolean battleWon;
    private static boolean battleOver;
    public static void attack(Enemy enemy, Label outputLabel, Label playerHP, Label enemyHP) {
        Player player = GameStateManager.getPlayer();
        int playerDamage = player.getStrength();
        int enemyDamage = enemy.getStrength();

        boolean playerDodge = Math.random() < player.getAgility() / 100;
        boolean playerCritical = Math.random() < player.getFortune() / 100;

        if (playerDodge) {
            enemyDamage = 0;
        }
        if (playerCritical) {
            playerDamage *= 2;
        }

        player.takeDamage(enemyDamage);
        enemy.takeDamage(playerDamage);

        if (!playerCritical && !playerDodge) {
            outputLabel.setText("Zadane obrażenia: " + playerDamage + "\nOtrzymane obrażenia: " + enemyDamage);
        } if (playerCritical && !playerDodge) {
            outputLabel.setText("Uderzenie krytyczne!\nZadane obrażenia: " + playerDamage + "\nOtrzymane obrażenia: " + enemyDamage);
        } if (playerCritical && playerDodge) {
            outputLabel.setText("Uderzenie krytyczne!\nZadane obrażenia: " + playerDamage + "\nUdało ci się uniknąć ataku przeciwnika!");
        } if (!playerCritical && playerDodge) {
            outputLabel.setText("Zadane obrażenia: " + playerDamage + "\nUdało ci się uniknąć ataku przeciwnika!");
        }
        playerHP.setText("HP: " + player.getHealth());
        enemyHP.setText("HP: " + enemy.getHealth());

        if (!player.isAlive()) {
            battleWon = false;
            battleOver = true;

        } else if (!enemy.isAlive()) {
            battleWon = true;
            battleOver = true;
        }
    }
    public static boolean isBattleWon() {
        return battleWon;
    }

    public static boolean isBattleOver() {
        return battleOver;
    }

    public static void setBattleState(boolean battleOver) {
        CombatSystem.battleOver = battleOver;
    }
}
