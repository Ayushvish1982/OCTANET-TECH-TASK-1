import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private double amount;
    private String type;

    public Transaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

}

public class ATM {
    private double balance;
    private List<Transaction> transactionHistory;

    public ATM(double balance) {
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction(amount, "Deposit"));
        System.out.println("Deposit Successful. New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance");
        } else {
            balance -= amount;
            transactionHistory.add(new Transaction(amount, "Withdrawal"));
            System.out.println("Withdrawal Successful. New balance: " + balance);
        }
    }

    public void transfer(double amount, String recipient) {
        if (amount > balance) {
            System.out.println("Insufficient balance");
        } else {
            balance -= amount;
            transactionHistory.add(new Transaction(amount, "Transfer to " + recipient));
            System.out.println("Transfer Successful. New balance: " + balance);
        }
    }

    public void showTransactionHistory() {
        for (Transaction transaction : transactionHistory) {
            System.out.println("Type: " + transaction.getType() + ", Amount: " + transaction.getAmount());
        }
    }

    public void quit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public static void main(String[] args) {
        ATM atm = new ATM(1000.0); // initial balance
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Enter recipient: ");
                    String recipient = scanner.next();
                    atm.transfer(transferAmount, recipient);
                    break;
                case 4:
                    atm.showTransactionHistory();
                    break;
                case 5:
                    atm.quit();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}