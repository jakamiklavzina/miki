import java.util.Random;
import java.util.Scanner;

public class RouletteGame {
    private static final int BLACK = 0;
    private static final int RED = 1;

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

            int spinResult = random.nextInt(37); // Simulate spinning the wheel
            int colorResult = (spinResult == 0) ? -1 : (spinResult % 2 == 0) ? BLACK : RED;

            switch (choice) {
                case 1:
                    System.out.print("Enter your bet number (0-36): ");
                    int betNumber = scanner.nextInt();
                    if (betNumber == spinResult) {
                        money += betAmount * 35;
                        System.out.println("Congratulations! You won by number!");
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
                        System.out.println("Congratulations! You won by color!");
                    } else {
                        System.out.println("You lost.");
                        money -= betAmount;
                    }
                    break;
                case 3:
                    System.out.print("Enter your bet (0 for Even, 1 for Odd): ");
                    int betOddEven = scanner.nextInt();
                    if (betOddEven == (spinResult % 2)) {
                        money += betAmount;
                        System.out.println("Congratulations! You won by odd/even!");
                    } else {
                        System.out.println("You lost.");
                        money -= betAmount;
                    }
                    break;
                case 4:
                    System.out.print("Enter your bet (0 for Low 1-18, 1 for High 19-36): ");
                    int betHighLow = scanner.nextInt();
                    if ((spinResult != 0) && (((spinResult >= 1 && spinResult <= 18) && betHighLow == 0) ||
                            ((spinResult >= 19 && spinResult <= 36) && betHighLow == 1))) {
                        money += betAmount;
                        System.out.println("Congratulations! You won by high/low!");
                    } else {
                        System.out.println("You lost.");
                        money -= betAmount;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("Your current balance is: $" + money);
        }

        System.out.println("Thank you for playing Roulette! Your final balance is: $" + money);
    }
}
