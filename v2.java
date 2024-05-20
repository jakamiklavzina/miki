import java.util.Random;
import java.util.Scanner;

public class RouletteGame {
    private static final int BLACK = 0;
    private static final int RED = 1;
    private static final String[] WHEEL_NUMBERS = {"0", "28", "9", "26", "30", "11", "7", "20", "32", "17", "5", "22", "34", "15", "3", "24", "36", "13", "1", "00", "27", "10", "25", "29", "12", "8", "19", "31", "18", "6", "21", "33", "16", "4", "23", "35", "14", "2"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int money = 1000;

        System.out.println("Welcome to Roulette!");
        System.out.println("You start with $1000.");

        while (money > 0) {
            System.out.println("\nPlace your bet:");
            System.out.println("1. A specific number (payout 35:1)");
            System.out.println("2. Red or Black (payout 1:1)");
            System.out.println("3. Odd or Even (payout 1:1)");
            System.out.println("4. High (19-36) or Low (1-18) (payout 1:1)");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 5) {
                break;
            }

            System.out.print("Enter your bet amount: ");
            int betAmount = scanner.nextInt();

            // Spin the wheel
            int spinResult = random.nextInt(38);
            String spinNumber = WHEEL_NUMBERS[spinResult];

            int colorResult = (spinResult == 0 || spinResult == 19) ? -1 : (spinResult <= 10 || (spinResult >= 20 && spinResult <= 28) || (spinResult >= 30 && spinResult <= 36)) ? BLACK : RED;

            switch (choice) {
                case 1:
                    System.out.print("Enter your bet number (0-36): ");
                    int betNumber = scanner.nextInt();
                    if (Integer.parseInt(spinNumber) == betNumber) {
                        money += betAmount * 35;
                        System.out.println("Congratulations! You won by number " + spinNumber + "!");
                    } else {
                        System.out.println("You lost.");
                        money -= betAmount;
                    }
                    break;
                case 2:
                    System.out.print("Enter your bet color (0 for Black, 1 for Red): ");
                    int betColor = scanner.nextInt();
                    if (betColor == colorResult) {
                        money += betAmount;
                        System.out.println("Congratulations! You won by color " + (colorResult == BLACK ? "Black" : "Red") + "!");
                    } else {
                        System.out.println("You lost.");
                        money -= betAmount;
                    }
                    break;
                case 3:
                    System.out.print("Enter your bet (0 for Even, 1 for Odd): ");
                    int betOddEven = scanner.nextInt();
                    if (betOddEven == (spinResult == 0 || spinResult == 19) ? 0 : (spinResult % 2)) {
                        money += betAmount;
                        System.out.println("Congratulations! You won by " + (betOddEven == 0 ? "Even" : "Odd") + " number!");
                    } else {
                        System.out.println("You lost.");
                        money -= betAmount;
                    }
                    break;
                case 4:
                    System.out.print("Enter your bet (0 for Low 1-18, 1 for High 19-36): ");
                    int betHighLow = scanner.nextInt();
                    if ((spinResult == 0 || spinResult == 19) ? false : (spinResult >= 1 && spinResult <= 18) == (betHighLow == 0)) {
                        money += betAmount;
                        System.out.println("Congratulations! You won by " + (betHighLow == 0 ? "Low" : "High") + " number!");
                    } else {
                        System.out.println("You lost.");
                        money -= betAmount;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("The wheel landed on: " + spinNumber);
            System.out.println("Your current balance is: $" + money);
        }

        System.out.println("Thank you for playing Roulette! Your final balance is: $" + money);
    }
}
