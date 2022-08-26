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
        System.out.printf("%-25s%20s%n", "Principal:", currency.format(principal));
        System.out.printf("%-25s%20s%n", "Monthly Amortization:", currency.format(amortization));


        System.out.println(String.format("%-15s", "Breakdown").replace(' ', '.'));
        System.out.printf("%3s%15s%15s%15s%15s%n", " ", "Balance", "Principal", "Interest", "End Balance");

        double beginningBal = principal;
        for (int i = 0; i < yearsToPay; i++) {

            System.out.println(String.format("%53s%10s", "", "Year " + (i + 1)).replace(' ', '_'));
            for (int j = 0; j < 12; j++) {
                double monthlyRate = amortization - (beginningBal * rate);
                double endingBalance = beginningBal - monthlyRate;
                String beginningBalDisplay = currency.format(beginningBal);
                String amortizationDisplay = currency.format(monthlyRate);
                String monthlyInterestDisplay = currency.format(beginningBal * rate);
                String endingBalanceDisplay = currency.format(endingBalance);
                System.out.println(String.format("%3s%15s%15s%15s%15s", j + 1, beginningBalDisplay, amortizationDisplay, monthlyInterestDisplay, endingBalanceDisplay));
                beginningBal = endingBalance;
            }
        }

    }
}
