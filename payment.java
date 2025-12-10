import java.util.*;
interface paym{
    void pay(double amount);
}

abstract class Base implements paym{
    protected String sender_id, receiver_id;

    public Base(String sender_id, String receiver_id){
        this.sender_id= sender_id;
        this.receiver_id= receiver_id;
    }

    public void validate(double amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }
}

class UPI extends Base{
    public UPI(String sender_id, String receiver_id){
        super(sender_id, receiver_id);
    }

    @Override
    public void pay(double amount) {
        validate(amount);
        if (amount > 10000){
            throw new IllegalArgumentException("Amount exceeds the UPI transaction limit.");
        }
        System.out.println("Paid " + amount + " using UPI.");
    }
}

class CreditCard extends Base{
    public CreditCard(String sender_no, String receiver_no){
        super(sender_no, receiver_no);
    }

    @Override
    public void pay(double amount) {
        validate(amount);
        if (amount > 100000){
            throw new IllegalArgumentException("Amount exceeds the Credit Card transaction limit.");
        }
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class Wallet extends Base{
    public Wallet(String sender_id, String receiver_id){
        super(sender_id, receiver_id);
    }

    @Override
    public void pay(double amount) {
        validate(amount);
        if (amount > 5000){
            throw new IllegalArgumentException("Amount exceeds the Wallet transaction limit.");
        }
        System.out.println("Paid " + amount + " using Wallet.");
    }
}

public class payment{
    public static void main(String[] args){
        Scanner s= new Scanner(System.in);
        System.out.print("Choose payment method (1: UPI, 2: Credit Card, 3: Wallet): ");
        int choice= s.nextInt();
        System.out.print("Enter amount to pay:");
        double amount= s.nextDouble();
        System.out.print("Enter sender ID/number: ");
        String s_id= s.next();
        System.out.print("Enter receiver ID/number: ");
        String r_id= s.next();
        paym p;
        switch(choice){
            case 1:
                p= new UPI(s_id, r_id);
                break;
            case 2:
                p= new CreditCard(s_id, r_id);
                break;
            case 3:
                p= new Wallet(s_id, r_id);
                break;
            default:
                System.out.println("Invalid choice.");
                s.close();
                return;
        }
        try {
            p.pay(amount);
        } catch (Exception e) {
            System.out.println("Payment failed due to "+ e.getMessage());
        }
        s.close();
    }
}