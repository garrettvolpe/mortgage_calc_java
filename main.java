import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    static final byte MONTHS_IN_YEAR = 12;
    static final byte PERCENT = 100;
    public static void main(String[] args) {
        int principal = (int) readNumber("Principal: ",1000, 1_000_000);
        float annualInterestRate = (float) readNumber("Annual Interest Rate: ", 0, 30);
        byte years = (byte) readNumber("Period (Years): ", 1, 30);

        double mortgage = mortgageCalc(principal,annualInterestRate,years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);

        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("--------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++){
            double balance = calcBalance(principal,annualInterestRate,years,month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));

        }

    }
    public static double readNumber(String prompt, double min, double max ) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter between " + min + " and " + max + ".");
        }
        return value;
    }
    public static double mortgageCalc(int principal,
                                      float annualInterestRate,
                                      byte years){
        short numberOfPayments = (short)( years * MONTHS_IN_YEAR);
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;

        double mortgage = principal*
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                /(Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        return  mortgage;
    }

    public static double calcBalance(int principal,
                                     float annualInterestRate,
                                     byte years,
                                     short numOfPaymentsMade){

        short numberOfPayments = (short)( years * MONTHS_IN_YEAR);
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;

        double balance = principal
                * (Math.pow(1+ monthlyInterestRate, numberOfPayments) - Math.pow(1+ monthlyInterestRate, numOfPaymentsMade))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        return balance;
    }
}
