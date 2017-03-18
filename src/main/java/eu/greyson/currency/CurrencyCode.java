package eu.greyson.currency;

import org.jetbrains.annotations.NotNull;

import java.util.Currency;

/**
 * <code>CurrencyCode</code> represents ISO 4217 standard of currency designators.
 * @see <a href="https://www.iso.org/iso-4217-currency-codes.html"></a>
 */
class CurrencyCode implements CurrencyDesignator {

    private Currency currency;

    private CurrencyCode(Currency currency) {
        this.currency = currency;
    }

    public static CurrencyCode valueOf(String s) throws CurrencyFormatException {
        try {
            Currency c = Currency.getInstance(s.toUpperCase());
            return new CurrencyCode(c);
        } catch (IllegalArgumentException e) {
            throw CurrencyFormatException.forInputString(s);
        }
    }

    @Override
    @NotNull
    public Currency getCurrency() {
        return currency;
    }
}
