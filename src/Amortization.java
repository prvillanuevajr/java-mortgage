import java.text.NumberFormat;
import java.util.Scanner;

public class Amortization {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Amortization Calculator!");
        System.out.print("How much do you want to borrow?: ");
        int principal = scanner.nextInt();
        System.out.print("Percentage of interest: ");
        double rateInput = scanner.nextDouble();
        System.out.print("How many years do you plan to pay?: ");
        int yearsToPay = scanner.nextByte();

        int yearsToMonths = yearsToPay * 12;

        double rate = (rateInput / 100) / 12;
        double temp1 = rate * Math.pow((rate + 1), yearsToMonths);
        double temp2 = Math.pow((rate + 1), yearsToMonths) - 1;
        double amortization = principal * (temp1 / temp2);

        NumberFormat currency = NumberFormat.getCurrencyInstance();

        double totalPayment = amortization * yearsToMonths;
        System.out.println(String.format("%-25s%20s", "Principal:", currency.format(principal)));
        System.out.println(String.format("%-25s%20s", "Monthly Amortization:", currency.format(amortization)));



    }
}
