import java.util.*;
public class ATM{
    private int balance;
    public ATM(int initBalance){
	this.balance = initBalance;
    }
    public int getBalance(){
	return this.balance;
    }
    public boolean withdraw(int amount){
	if(amount > this.balance){
	    return false;
	}
	else{
	    this.balance -= amount;
	    return true;
	}
    }
    public void deposit(int amount){
	this.balance += amount;
    }

    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	final int PIN = 1234;
	System.out.print("Enter your PIN: ");
	int pin = in.nextInt();
	ATM atm = new ATM(5000);
	while(PIN == pin){
	    System.out.printf("1. View Balance%n2. Withdraw%n3. Deposit%n4. Exit%nEnter the action you want to perform : ");
	    int input = in.nextInt();
	    if(input == 1){
		System.out.println("Your Balance is : ₦" + atm.getBalance());
	    }
	    else if(input == 2){
		System.out.print("Enter the Amount you want to withdraw : ");
		int withAmount = in.nextInt();
		boolean success = atm.withdraw(withAmount);
		if(success){
		    System.out.println("Withdrawal Successful");
		}
		else{
		    System.out.println("Insufficient funds");
		}
	    }
	    else if(input == 3){
		System.out.print("Enter the amount to deposit : ₦");
		int depAmount = in.nextInt();
		atm.deposit(depAmount);
		System.out.println("Deposited Successfully");
	    }
	    else if(input == 4){
		System.out.println("Exiting...");
		System.exit(0);
	    }
	    else{
		System.out.println("Invalid Command");
	    }
	}
    }
}
