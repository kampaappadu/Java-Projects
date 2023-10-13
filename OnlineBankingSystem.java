import java.util.*;
class BankAccount{
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    public BankAccount(int num,String name,double bal){
        this.accountNumber = num;
        this.accountHolderName = name;
        this.balance = bal;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public String getAccountHolderName(){
        return accountHolderName;
    }
    public double getBalance(){
        return balance;
    }

    public boolean withdraw(double amount){
        if (balance>=amount){
            balance-=amount;
            return true;
        }else{
            return false;
        }
    }
    public double deposit(double amount){
        return balance+=amount;
    }
}
class Bank{
    private Map<Integer,BankAccount> accounts;
    private int nextAccountNumber = 372577628;
    public Bank(){
        this.accounts = new HashMap<>();
    }
    public int createAccount(String name,double balance){
        int accountNumber = nextAccountNumber++;
        BankAccount account = new BankAccount(accountNumber,name,balance);
        accounts.put(accountNumber,account);
        return accountNumber;
    }
    public double getAccountBalance(int accountNumber){
        BankAccount account = accounts.get(accountNumber);
        if (account != null){
            return account.getBalance();
        }else{
            return -1;
        }
    }
    public boolean transferFunds(int fromAccountNumber,int toAccountNumber,double amount){
        BankAccount fromAccount = accounts.get(fromAccountNumber);
        BankAccount toAccount = accounts.get(toAccountNumber);
        if (fromAccount != null && toAccount != null && fromAccount.withdraw(amount)){
            toAccount.deposit(amount);
            return true;
        }else{
            return false;
        }
    }
}
class OnlineBankingSystem{
    public static void main(String[] args){
        Scanner user = new Scanner(System.in);
        Bank bank = new Bank();
        while(true){
            System.out.println("\nWelcome to the \"Online Banking System\" ");
            System.out.println("1. Create Account");
            System.out.println("2. Check Account Balance");
            System.out.println("3. Transfer Funds");
            System.out.println("4. Exit");
            System.out.print("Select any option to proceed: ");
            int choice = user.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter your name: ");
                    String name = user.nextLine();
                    user.nextLine();
                    System.out.print("Enter initial Balance: ");
                    double initialAmount = user.nextDouble();
                    int accountNumber = bank.createAccount(name,initialAmount);
                    System.out.println("Your account is created with account number : "+accountNumber);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    int accountToCheck = user.nextInt();
                    double amount = bank.getAccountBalance(accountToCheck);
                    if (amount>=0){
                        System.out.println("Your current balance is : "+amount);
                    }else{
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter source account number: ");
                    int sourceAccountNumber = user.nextInt();
                    System.out.println("Enter target account numbera: ");
                    int targetAccountNumber = user.nextInt();
                    System.out.println("Enter transferAmount: ");
                    double transferAmount = user.nextDouble();
                    if (bank.transferFunds(sourceAccountNumber,targetAccountNumber,transferAmount)){
                        System.out.println("Amount transferred succcessfully...");
                    }else{
                        System.out.println("Amount transfer failed.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting fron Online Banking Service..");
                    user.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("You have entered invalid option, choose right one please...");
                    break;
            }
        }
    }
}

