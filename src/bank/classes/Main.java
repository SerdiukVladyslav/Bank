package bank.classes;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Bank bank = new Bank();

            ATM atm1 = new ATM(50, 40);
            atm1.loadMoney(500, 10);
            atm1.loadMoney(100, 50);
            atm1.loadMoney(20, 100);
            bank.addATM(atm1);

            ATM atm2 = new ATM(20, 50);
            atm2.loadMoney(100, 20);
            atm2.loadMoney(50, 50);
            atm2.loadMoney(10, 200);
            bank.addATM(atm2);

            System.out.println("Total ATMs: " + bank.getTotalATMs());
            System.out.println("Total money in bank: " + bank.getTotalMoney() + " UAH");

            atm1.depositMoney(100, 5);
            Map<Integer, Integer> withdrawn = atm1.withdrawMoney(1350);
            System.out.println("Withdrawn money: " + withdrawn);

            System.out.println("Total money in bank after withdrawal: " + bank.getTotalMoney() + " UAH");

        } catch (ATMException e) {
            System.err.println(e.getMessage());
        }
    }
}
