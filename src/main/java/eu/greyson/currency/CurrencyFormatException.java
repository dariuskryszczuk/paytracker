package eu.greyson.currency;

class CurrencyFormatException extends IllegalArgumentException {
    static final long serialVersionUID = 42L;

    private CurrencyFormatException(String msg) {
        super(msg);
    }

    static CurrencyFormatException forInputString(String s) {
        return new CurrencyFormatException("Incorrect input: wrong currency code \"" + s + "\"");
    }
}
