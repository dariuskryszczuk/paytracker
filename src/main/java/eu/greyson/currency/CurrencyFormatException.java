package eu.greyson.currency;

public class CurrencyFormatException extends IllegalArgumentException {
    static final long serialVersionUID = 42L;

    public CurrencyFormatException() {}

    public CurrencyFormatException(String msg) {
        super(msg);
    }

    static CurrencyFormatException forInputString(String s) {
        return new CurrencyFormatException("Can't get Currency from input string: \"" + s + "\"");
    }
}
