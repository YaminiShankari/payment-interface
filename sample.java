import java.util.Scanner;

public class sample {
    interface Payable {
        void pay(double amount);
    }
    static class UpiPayment implements Payable {
        private String upiId;

        public UpiPayment(String upiId) {
            this.upiId = upiId;
        }

        public void pay(double amount) {
            System.out.println("Paying $" + amount + " using UPI ID: " + upiId);
        }
    }
    static class CardPayment implements Payable {
        private String cardNumber;

        public CardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public void pay(double amount) {
            System.out.println("Paying $" + amount + " using Card Number: " + cardNumber);
        }
    }
    static class WalletPayment implements Payable {
        private String walletId;

        public WalletPayment(String walletId) {
            this.walletId = walletId;
        }

        public void pay(double amount) {
            System.out.println("Paying $" + amount + " using Wallet ID: " + walletId);
        }
    }
    static class PaymentProcessor {
        public void processPayment(Payable paymentMethod, double amount) {
            paymentMethod.pay(amount);
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PaymentProcessor processor = new PaymentProcessor();

        System.out.print("Enter amount to pay: ");
        double amount = sc.nextDouble();
        sc.nextLine(); 

        System.out.println("\nChoose Payment Method:");
        System.out.println("1. UPI");
        System.out.println("2. Card");
        System.out.println("3. Wallet");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine(); 

        Payable payment = null;

        switch (choice) {
            case 1:
                System.out.print("Enter UPI ID: ");
                String upiId = sc.nextLine();
                payment = new UpiPayment(upiId);
                break;

            case 2:
                System.out.print("Enter Card Number: ");
                String cardNumber = sc.nextLine();
                payment = new CardPayment(cardNumber);
                break;

            case 3:
                System.out.print("Enter Wallet ID: ");
                String walletId = sc.nextLine();
                payment = new WalletPayment(walletId);
                break;

            default:
                System.out.println("Invalid choice!");
                System.exit(0);
        }
        processor.processPayment(payment, amount);

        sc.close();
    }
}