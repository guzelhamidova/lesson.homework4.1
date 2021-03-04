package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 40;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 250,200,400};
    public static int[] heroesDamage = {20, 15, 10,0,5};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic","Golem"};
    public static int roundNumber = 0;
    public static int indexOfHealedHero = -1;
    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        System.out.println("ROUND №" + roundNumber);
        changeDefence();
        if (bossHealth > 0) {
            bossHits();
        }
        heroesHit();
        medicHelps();
        printStatistics();
    }

    public static void changeDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("_______________________");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }

    }

    public static void medicHelps() {
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println("heroes index:" + i + " heroes health: " + heroesHealth[i]);
            if (heroesHealth[i]< 100 && heroesHealth[i] > 0 && indexOfHealedHero != i && heroesHealth[3] > 0){
                heroesHealth[i] += 100;
                indexOfHealedHero = i;
                System.out.println("heroes index:" + i + "heroes health: " + heroesHealth[i]);
                return;
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(10) + 2; //2,3,4,5,6,7,8,9,10,11
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }
}
