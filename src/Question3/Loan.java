package Question3;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Loan implements Serializable {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private Date loanDate;

    // default constructor that set the default data
    public Loan(){
        this.annualInterestRate = 0.025;
        this.numberOfYears = 1;
        this.loanAmount = 1000;
        this.loanDate = new Date();
    }

    // constructor with some input
    public Loan(double annualInterestRate, int numberOfYears, double loanAmount){
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        this.loanDate = new Date();
    }

    // show the rate
    public double getAnnualInterestRate(){ return annualInterestRate; }

    // show the number of years
    public int getNumberOfYears(){ return numberOfYears; }

    // show the loan amount
    public double getLoanAmount(){ return loanAmount; }

    // show the loan date
    public Date getLoanDate(){ return loanDate; }

    // set the new rate
    public void setAnnualInterestRate(double annualInterestRate){
        this.annualInterestRate = annualInterestRate;
    }

    // change the new number of years
    public void setNumberOfYears(int numberOfYears){
        this.numberOfYears = numberOfYears;
    }

    // change the loan amount
    public void setLoanAmount(double loanAmount){
        this.loanAmount = loanAmount;
    }

    // calculate the monthly payment
    public double getMonthlyPayment(){
        return loanAmount * (1 + annualInterestRate) / 12;
    }

    // calculate the total amount
    public double getTotalPayment(){
        return numberOfYears * (1+annualInterestRate) * loanAmount;
    }

    @Override
    public String toString(){
        String s = "annualInterestRate = " + annualInterestRate + "\n"
                + "numberOfYears = " + numberOfYears + "\n"
                + "loanAmount = " +loanAmount + "\n"
                + "loanDate = " + loanDate + "\n";
        return s;
    }

    // static method that receive the List of Loan and save it to Loan.dat
    public static void putAll(List<Loan> ls){
        String fileName = "Loan.dat";
        File file = new File(fileName);
        // if the file is not exit, create a new one
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // write the object
        // the new file information will cover the original stored information
        try (FileOutputStream fos = new FileOutputStream(file, false);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(ls);
//            out.defaultWriteObject();
        } catch (IOException i) {
            System.out.println("Exception writing out Loan: " + i);
        }
    }

    // show the object restored in the file
    public static void show(){
        int total_amount = 0;
        String fileName = "Loan.dat";
        File file = new File(fileName);
        // extract the list
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            List<Loan> ls = (List<Loan>)in.readObject();
            // print the object line by line
            for(Loan l : ls){
                total_amount += l.loanAmount;
                System.out.println(l);
            }
            System.out.println();
        } catch (EOFException ignored){

        } catch (ClassNotFoundException | IOException i) {
            System.out.println("Exception reading in Loan: " + i);
        }
        System.out.println();
        System.out.println("Total amount = " + total_amount);
    }

//    public void store(){
//        String fileName = "Loan.dat";
//        File file = new File(fileName);
//        if(!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try (FileOutputStream fos = new FileOutputStream(file, true);
//             ObjectOutputStream out = new ObjectOutputStream(fos)) {
//            out.writeObject(this);
////            out.defaultWriteObject();
//        } catch (IOException i) {
//            System.out.println("Exception writing out Portfolio: " + i);
//        }
//    }

//    public static void display(){
//        int total_amount = 0;
//        String fileName = "Loan.dat";
//        File file = new File(fileName);
//
//        try (FileInputStream fis = new FileInputStream(file);
//             ObjectInputStream in = new ObjectInputStream(fis)) {
//            Loan l = null;
//            while(true){
//                if((l = (Loan)in.readObject()) != null){
//                    total_amount += l.loanAmount;
//                    System.out.println(l);
//                }
//                else break;
//            }
//        } catch (EOFException ignored){
//
//        } catch (ClassNotFoundException | IOException i) {
//            System.out.println("Exception reading in Loan: " + i);
//        }
//        System.out.println();
//        System.out.println("Total amount = " + total_amount);
//    }
}
