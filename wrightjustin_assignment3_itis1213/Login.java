/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrightjustin_assignment3_itis1213;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Justin
 */
public class Login {

    public static void main(String[] args) {

        System.out.println("Welcome! What type of user are you?");
        System.out.println("Enter 1 for Employee, 2 for Donor, or 3 for Volunteer. Press 0 to exit!");
        Scanner account = new Scanner(System.in);
        int accountNumber = account.nextInt();

        switch (accountNumber) {
            case 1:
                // Employee

                Employees employee = new Employees();
                Perishable Alert = new Perishable();
                Random random = new Random();
                int food = random.nextInt(500) + 250;
                System.out.println("Welcome to the employee database...");
                System.out.println("Would you like to check how much food is in stock? (Before updating) Y/N");
                // amount of food in stock to be randomized each time for employee/volunteer (cant add food)
                Scanner employee1 = new Scanner(System.in);
                char employeechoice1 = employee1.next().charAt(0);
                if ((employeechoice1 == 'y') || (employeechoice1 == 'Y')) {
                    System.out.println(food + " food items are in stock.");
                }
                System.out.println("Our system will now do an automatic check for expired food...");
                Random random2 = new Random();
                int date = random2.nextInt(50) + 1;
                Alert.alert(date);
                System.out.println("Would you like to check how much you've earned this week? Y/N");
                Scanner employee2 = new Scanner(System.in);
                char employeechoice2 = employee2.next().charAt(0);
                if ((employeechoice2 == 'y') || (employeechoice2 == 'Y')) {
                    System.out.println("How many hours have you worked this week?");
                    Scanner employee3 = new Scanner(System.in);
                    double employeechoice3 = employee3.nextDouble();
                    employee.getEarnings(employeechoice3);
                    if (employeechoice3 > 48) {
                        System.out.println("You can't work that long!");
                        break;
                    }
                    System.out.println("And how much food have you distributed this week?");
                    Scanner employee4 = new Scanner(System.in);
                    double employeechoice4 = employee4.nextDouble();
                    employee.getWage(employeechoice4);
                    double total = employeechoice3 * 7.50 + employeechoice4 * 0.10 + date * 0.20;
                    System.out.println("The total earned for this week is:$" + total);
                    System.out.println("The new food stock is...");
                    double newStock = (food + date) - employeechoice4;
                    System.out.println(newStock);

                } else {
                    break;
                }
            case 2:
                // Donor
                Donors donator = new Donors();
                System.out.println("Welcome to the donator database...");
                System.out.println("Please enter how much food you would like to donate");
                System.out.println("If you donate at least 50 items, you will be eligible for a giftcard!");
                System.out.println("Our employees will check for expired food, so don't worry about checking dates!");
                Scanner donate = new Scanner(System.in);
                int donateFood = donate.nextInt();
                double rewardPoints = donateFood * 0.50;
                System.out.print("Because you have donated " + donateFood);
                System.out.print(" items, you've earned " + rewardPoints);
                System.out.println(" reward points!");
                if (rewardPoints >= 25) {
                    System.out.print("Congratulations! Because you have at least 25 reward points");
                    System.out.println(" you are eligible for a giftcard of your choice!");
                    donator.addGiftCard();
                    System.out.print("You now have " + (rewardPoints - 25));
                    System.out.println(" reward points!");
                    double total = (rewardPoints - 25);
                    if (total >= 25) {
                        System.out.print("Wow, look at you!! Because you still have at least 25 reward points");
                    }
                    System.out.println(" you are eligible for another giftcard of your choice!");
                    donator.addGiftCard();
                    System.out.print("You now have " + (total - 25));
                    System.out.println(" reward points!");
                    System.out.println("Thank you for your patronage!");
                    break;
                }
            case 3:
                // Volunteer (does not view food stock)
                Perishable Alert2 = new Perishable();
                Volunteers volunteerHours = new Volunteers();
                System.out.println("Welcome to the volunteer database...");
                System.out.println("Our system will now do an automatic check for expired food...");
                Random random4 = new Random();
                int date2 = random4.nextInt(50) + 1;
                Alert2.alert2(date2);
                System.out.println("Would you like to check if you've earned a certificate? Y/N");
                Scanner volunteer2 = new Scanner(System.in);
                char volunteerchoice2 = volunteer2.next().charAt(0);
                if ((volunteerchoice2 == 'y') || (volunteerchoice2 == 'Y')) {
                    System.out.println("Please enter the number of volunteer hours you've served!");
                    Scanner hours = new Scanner(System.in);
                    int volunteerhours = hours.nextInt();
                    volunteerHours.certificate(volunteerhours);
                } else {
                    break;
                }
            // Exit
            default:
                System.out.println("Have a nice day!");

        }

    }

}
