/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrightjustin_assignment3_itis1213;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Justin
 */
public class Donors extends UserAccount {

    private double rewardPoints;
    private String[] GiftCard = {"1. Best Buy Giftcard", "2. Target Giftcard", "3. WalMart Giftcard", "4. Gamestop Giftcard", "5. Old Navy Giftcard", "6. Belk Giftcard"};

    public void addGiftCard() {
        // take reward points to purchase giftcard

        System.out.println(Arrays.toString(GiftCard));
        System.out.println("Please enter the number you would like to redeem!");
        Scanner gift = new Scanner(System.in);
        int accountNumber = gift.nextInt();

        switch (accountNumber) {
            case 1:
                System.out.println("Enjoy your $25 dollar Best Buy Giftcard! ");
                break;
            case 2:
                System.out.println("Enjoy your $25 dollar Target Giftcard! ");
                break;
            case 3:
                System.out.println("Enjoy your $25 dollar WalMart Giftcard! ");
                break;
            case 4:
                System.out.println("Enjoy your $25 dollar Gamestop Giftcard! ");
                break;
            case 5:
                System.out.println("Enjoy your $25 dollar Oldnavy Giftcard! ");
                break;
            case 6:
                System.out.println("Enjoy your $25 dollar Belk Giftcard! ");
                break;
            default:
                System.out.println("Sorry, no more options at the moment!");
                break;
        }
    }

    public double rewardpoints() { ////psuedocode
        // if rewardPoints >= 25, get giftcard
        // Takes how many food items distrubuted (in donors case, donated),
        // and returns the amount of reward points to the user.
        // User can redeem giftcards with reward poins.

        rewardPoints = getNumberDistributed();
        return rewardPoints;
    }

}
