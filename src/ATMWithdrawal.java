import java.util.Scanner;
class InvalidPINException extends Exception {
    public InvalidPINException(String message) {
        super(message);
    }
}
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
public class ATMWithdrawalSystem {
    private static final int CORRECT_PIN = 1234;
    private static double balance = 3000.0; 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
            if (pin != CORRECT_PIN) {
                throw new InvalidPINException("Invalid PIN.");
            }
            System.out.print("Withdraw Amount: ");
            double withdrawalAmount = scanner.nextDouble();
            if (withdrawalAmount > balance) {
                throw new InsufficientBalanceException("Insufficient balance.");
            }
            balance -= withdrawalAmount;
            System.out.println("Withdrawal successful. Current Balance: " + balance);
        } catch (InvalidPINException | InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage() + " Current Balance: " + balance);
        } finally {
            scanner.close();
        }
    }
}
