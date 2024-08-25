package bank.classes;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<ATM> atms;

    public Bank() {
        this.atms = new ArrayList<>();
    }

    public void addATM(ATM atm) {
        this.atms.add(atm);
    }

    public int getTotalATMs() {
        return atms.size();
    }

    public long getTotalMoney() {
        long total = 0;
        for (ATM atm : atms) {
            total += atm.getTotalMoney();
        }
        return total;
    }
}

