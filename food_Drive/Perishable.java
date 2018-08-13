/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrightjustin_assignment3_itis1213;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Justin
 */
public class Perishable extends Food {

    private int date;

    public void alert(int date) { // Alert for employees
        if (date < 49) {
            System.out.println(date + " Items are not fresh!");
            System.out.println("Would you like to remove the items that are not fresh?");
            Scanner remove = new Scanner(System.in);
            char removefood = remove.next().charAt(0);
            if ((removefood == 'y') || (removefood == 'Y')) {
                System.out.println(date + " Items removed from stock.");
                double earnings = date * 0.20;
                System.out.print("You've earned $" + earnings);
                System.out.println(" from removing expired food");
            }
        } else {
            System.out.println("Good news! All items are fresh"); // Very rare (1 in 50), but can happen!
        }
    }

    public void alert2(int date) { // Alert for volunteers
        if (date < 49) {
            System.out.println(date + " Items are not fresh!");
            System.out.println("Would you like to remove the items that are not fresh? Y/N");
            Scanner remove = new Scanner(System.in);
            char removefood = remove.next().charAt(0);
            if ((removefood == 'y') || (removefood == 'Y')) {
                System.out.println(date + " Items removed from stock. Thank you! ");

            }
        } else {
            System.out.println("Good news! All items are fresh"); // Very rare (1 in 50), but can happen!
        }
    }

    public int getDate() { //psuedocode
        return date;
    }
}
