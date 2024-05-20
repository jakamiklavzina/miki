import java.util.Random;
import java.util.Scanner;

public class Roulette {
    private static final int BLACK = 0;
    private static final int RED = 1;

    private static void displayInstructions() {
        System.out.println("Welcome to Roulette!");
        System.out.println("You start with $1000.");
        System.out.println("Place your bet on:");
        System.out.println("1. A specific number (payout 35:1)");
        System.out.println("2. Red or Black (payout 1:1)");
        System.out.println("3. Odd or Even (payout 1:1)");
        System.out.println("4. High (19-36) or Low (1-18) (payout 1:1)\n");
    }

    private static int spinWheel() {
        Random random = new Random();
        return random.nextInt(37); // Roulette wheel numbers from 0 to 36
    }

    private static int determineColor(int number) {
        if (number == 0) {
            return -1; // Zero is neither black nor red
        } else if (number % 2 == 0) {
            return BLACK; // Even numbers are black
        } else {
            return RED; // Odd numbers are red
        }
    }

    private static void playRoulette(int[] money) {
        Scanner scanner = new Scanner(System.in);
        int betType, betAmount, betNumber, betColor, betOddEven, betHighLow;
        int spinResult, colorResult, win = 0;

        if (money[0] <= 0) {
            System.out.println("You have no more money left! Game over.");
            return;
        }

        displayInstructions();

        System.out.println("Your current balance is: $" + money[0]);
        System.out.print("Enter your bet amount: ");
        betAmount = scanner.nextInt();
        if (betAmount > money[0] || betAmount <= 0) {
            System.out.println("Invalid bet amount!");
            return;
        }

        System.out.print("Choose your bet type (1-4): ");
        betType = scanner.nextInt();

        switch (betType) {
            case 1:
                System.out.print("Enter your bet number (0-36): ");
                betNumber = scanner.nextInt();
                if (betNumber < 0 || betNumber > 36) {
                    System.out.println("Invalid bet number!");
                    return;
                }
                break;
            case 2:
                System.out.print("Enter your bet color (0 for Black, 1 for Red): ");
                betColor = scanner.nextInt();
                if (betColor != BLACK && betColor != RED) {
                    System.out.println("Invalid bet color!");
                    return;
                }
                break;
            case 3:
                System.out.print("Enter your bet (0 for Even, 1 for Odd): ");
                betOddEven = scanner.nextInt();
                if (betOddEven != 0 && betOddEven != 1) {
                    System.out.println("Invalid bet!");
                    return;
                }
                break;
            case 4:
                System.out.print("Enter your bet (0 for Low 1-18, 1 for High 19-36): ");
                betHighLow = scanner.nextInt();
                if (betHighLow != 0 && betHighLow != 1) {
                    System.out.println("Invalid bet!");
                    return;
                }
                break;
            default:
                System.out.println("Invalid bet type!");
                return;
        }

        spinResult = spinWheel();
        colorResult = determineColor(spinResult);

        System.out.println("\nThe wheel spun and the result is: " + spinResult);
        if (colorResult == BLACK) {
            System.out.println("The color is: Black");
        } else if (colorResult == RED) {
            System.out.println("The color is: Red");
        }

        switch (betType) {
            case 1:
                if (spinResult == betNumber) {
                    System.out.println("Congratulations! You won by number!");
                    money[0] += betAmount * 35;
                    win = 1;
                }
                break;
            case 2:
                if (colorResult == betColor) {
                    System.out.println("Congratulations! You won by color!");
                    money[0] += betAmount;
                    win = 1;
                }
                break;
            case 3:
                if ((spinResult != 0) && ((spinResult % 2) == betOddEven)) {
                    System.out.println("Congratulations! You won by odd/even!");
                    money[0] += betAmount;
                    win = 1;
                }
                break;
            case 4:
                if ((spinResult != 0) && (((spinResult >= 1 && spinResult <= 18) && betHighLow == 0) ||
                        ((spinResult >= 19 && spinResult <= 36) && betHighLow == 1))) {
                    System.out.println("Congratulations! You won by high/low!");
                    money[0] += betAmount;
                    win = 1;
                }
                break;
        }

        if (win == 0) {
            System.out.println("You lost.");
            money[0] -= betAmount;
        }

        System.out.println("Your current balance is: $" + money[0]);
    }

    public static void main(String[] args) {
        int[] money = {1000};
        Scanner scanner = new Scanner(System.in);
        char playAgain;

        do {
            playRoulette(money);
            if (money[0] <= 0) break;
            System.out.print("\nDo you want to play again? (y/n): ");
            playAgain = scanner.next().charAt(0);
        } while (playAgain == 'y' || playAgain == 'Y');

        System.out.println("Thank you for playing Roulette! Your final balance is: $" + money[0]);
    }
}
