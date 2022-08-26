import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Amortization {
    static final Scanner scanner = new Scanner(System.in);
    static int principal = 0;
    static double rateInput = 0D;
    static int yearsToPay = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to Amortization Calculator!");

        askForPrincipal();
        askForInterestRate();
        askForYearsToPay();


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

    private static void askForYearsToPay() {
        boolean enteredYearsToPay = false;

        do {
            if ((yearsToPay <= 0 || yearsToPay > 30) && enteredYearsToPay) {
                System.out.println("Cannot be less than a year and more than 30");
            }
            System.out.print("How many years do you plan to pay?: ");
            handleError(() -> yearsToPay = scanner.nextByte(), Amortization::askForYearsToPay);
            enteredYearsToPay = true;

        } while (yearsToPay <= 0 || yearsToPay > 30);
    }

    private static void askForInterestRate() {
        boolean enteredRate = false;
        do {
            if ((rateInput <= 0 || rateInput > 30) && enteredRate) {
                System.out.println("Interest percentage should be greater than 0 and less than or equal to 30");
            }
            System.out.print("Percentage of interest: ");

            handleError(() -> rateInput = scanner.nextDouble(), Amortization::askForInterestRate);

            enteredRate = true;
        } while (rateInput <= 0 || rateInput > 30);
    }

    private static void askForPrincipal() {
        boolean enteredPrincipal = false;
        principal = 0;
        do {
            if ((principal < 1000 || principal > 1_000_000) && enteredPrincipal) {
                System.out.println("Principal should be between 1k - 1M");
            }
            System.out.print("How much do you want to borrow? (1k - 1M): ");
            handleError(() -> principal = scanner.nextInt(), Amortization::askForPrincipal);
            enteredPrincipal = true;
        } while (principal < 1000 || principal > 1_000_000);
    }

    public static void handleError(Runnable f1, Runnable f2) {
        try {
            f1.run();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid Input!");
            f2.run();
        }
    }
}
