package bank.classes;

class ATMException extends Exception {
    public ATMException(String message) {
        super(message);
    }
}

class InvalidDenominationException extends ATMException {
    public InvalidDenominationException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends ATMException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class ExceedMaxBanknotesException extends ATMException {
    public ExceedMaxBanknotesException(String message) {
        super(message);
    }
}

class InvalidAmountException extends ATMException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
