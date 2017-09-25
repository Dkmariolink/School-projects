/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woffortune;

import java.util.Random;

/**
 * This class defines one wedge of a wheel for the wheel of fortune game
 *
 * @author Justin Wright
 */
public class Wedge {

    private Wheel.WedgeType type;
    private int amount = 0;
    // Prize list
    private String[] Prize = {"Car", "PS4 Pro", "Computer", "iPad", "4k HDR TV", "Xbox One S", "Endless Supply of Cup Noodles", "Vacation to Hawaii"};
    private Random rand = new Random();
    String prize;

    /**
     * Constructor
     *
     * @param type Wheel.WedgeType
     */
    public Wedge(Wheel.WedgeType type) {
        this.type = type;
        if (type == Wheel.WedgeType.MONEY) {
            amount = (int) (Math.random() * 20 + 1) * 25;
        }
        if (type == Wheel.WedgeType.PRIZE) {
            prize = Prize[rand.nextInt(Prize.length)]; // Random prize picker from the pool of prizes in String array
        }
    }

    /**
     * Getter
     *
     * @return Wheel.WedgeType
     */
    public Wheel.WedgeType getType() {
        return type;
    }

    /**
     * Getter
     *
     * @return int amount (only for wedges of Wheel.WedgeType.MONEY)
     */
    public int getAmount() {
        return amount;
    }
    /**
     * Getter
     * @return String prize (only for wedges of Wheel.WedgeType.PRIZE)
     */

    public String getPrize() {
        return prize;
    }

}
