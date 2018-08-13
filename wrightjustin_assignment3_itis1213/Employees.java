/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrightjustin_assignment3_itis1213;

import java.util.Scanner;

/**
 *
 * @author Justin
 */
public class Employees extends UserAccount {

    private double payment;

    public double getWage(double x) { //psuedocode

        payment = getWorkHours();
        return payment;

    }

    public double getEarnings(double x) { //psuedocode

        payment = getNumberDistributed();
        return payment;

    }
}
