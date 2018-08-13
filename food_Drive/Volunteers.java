/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrightjustin_assignment3_itis1213;



/**
 *
 * @author Justin
 */
public class Volunteers extends UserAccount {

    public void certificate(int workHours) {
        if (workHours >= 50) {
            System.out.println("Bronze Certificate earned! Congratulations! Please report to the office.");
        } else if (workHours >= 75) {
            System.out.println("Silver Certificate earned! Congratulations! Please report to the office.");
        } else if (workHours >= 100) {
            System.out.println("Gold Certificate earned! Congratulations! Please report to the office.");
        } else if (workHours >= 150) {
            System.out.println("Platinum Cerficate earned! Congratulations! Please report to the office.");
        } else {
            System.out.println("Not enough volunteer hours to earn a certificate. Please keep it up!");
        }
    }
}
