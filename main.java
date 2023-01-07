import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        int principal = 0;
        float annualInterestRate = 0;
        byte years = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Principal: ");
            principal = scanner.nextInt();
            if (principal >= 1000 && principal <= 1_000_000)
                break;
            System.out.println("Enter a value between 1,000 and 1,000,000 please.");
        }

        while (true) {
            System.out.print("Annual Interest Rate: ");
            annualInterestRate = scanner.nextFloat();
            if (annualInterestRate > 0 && annualInterestRate < 30)
                break;
            System.out.println("Enter a interest rate greater than 0 and less than 30.");
        }

        while (true) {
            System.out.print("Period (Years): ");
            years = scanner.nextByte();
            if (years >= 1 && years <= 30)
                break;
            System.out.println("Enter between 1 and 30 years.");
        }


        double mortgage = mortgageCalc(principal,annualInterestRate,years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + mortgageFormatted);

    }
    public static double mortgageCalc(int principal,
                                      float annualInterestRate,
                                      byte years){

        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;
        short numberOfPayments = (short)( years * MONTHS_IN_YEAR);
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;

        double mortgage = principal*
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                /(Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        return  mortgage;
    }
}
