package bank.classes;

import java.util.HashMap;
import java.util.Map;

public class ATM {
    private Map<Integer, Integer> banknotes;
    private int minAmount;
    private int maxBanknotesToWithdraw;

    public ATM(int minAmount, int maxBanknotesToWithdraw) {
        this.banknotes = new HashMap<>();
        this.minAmount = minAmount;
        this.maxBanknotesToWithdraw = maxBanknotesToWithdraw;
    }

    public void loadMoney(int denomination, int count) {
        banknotes.put(denomination, banknotes.getOrDefault(denomination, 0) + count);
    }

    public long getTotalMoney() {
        long total = 0;
        for (Map.Entry<Integer, Integer> entry : banknotes.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }
        return total;
    }

    public void depositMoney(int denomination, int count) throws InvalidDenominationException {
        if (!banknotes.containsKey(denomination)) {
            throw new InvalidDenominationException("Invalid denomination: " + denomination);
        }
        banknotes.put(denomination, banknotes.get(denomination) + count);
    }

    public Map<Integer, Integer> withdrawMoney(int amount) throws InsufficientFundsException, ExceedMaxBanknotesException, InvalidAmountException {
        if (amount < minAmount) {
            throw new InvalidAmountException("Amount is less than the minimum allowed.");
        }

        long total = getTotalMoney();
        if (amount > total) {
            throw new InsufficientFundsException("Insufficient funds in ATM.");
        }

        Map<Integer, Integer> withdrawn = new HashMap<>();
        int remainingAmount = amount;
        int banknotesCount = 0;

        for (int denomination : new int[]{500, 200, 100, 50, 20, 10, 5, 2, 1}) {
            if (banknotes.containsKey(denomination)) {
                int availableBanknotes = banknotes.get(denomination);
                int neededBanknotes = Math.min(remainingAmount / denomination, availableBanknotes);
                withdrawn.put(denomination, neededBanknotes);
                banknotesCount += neededBanknotes;
                remainingAmount -= neededBanknotes * denomination;

                if (banknotesCount > maxBanknotesToWithdraw) {
                    throw new ExceedMaxBanknotesException("Exceeded maximum number of banknotes to withdraw.");
                }

                if (remainingAmount == 0) break;
            }
        }

        if (remainingAmount > 0) {
            throw new InsufficientFundsException("Cannot dispense the exact amount with available denominations.");
        }

        // Update ATM banknotes after withdrawal
        for (Map.Entry<Integer, Integer> entry : withdrawn.entrySet()) {
            banknotes.put(entry.getKey(), banknotes.get(entry.getKey()) - entry.getValue());
        }

        return withdrawn;
    }
}
