package eu.greyson.currency.designator;

import java.util.Currency;
import java.util.Locale;

/**
 * <code>CurrencyCode</code> represents ISO 4217 standard of currency designators.
 * @see <a href="https://www.iso.org/iso-4217-currency-codes.html"></a>
 */
public class CurrencyCode implements CurrencyDesignator {

    private CurrencyCode() {}

    public static CurrencyCode parse(String s) {
        // todo
    }

    @Override
    public Currency getCurrency() {
        return null;
    }

    @Override
    public String getSymbol() {
        return this.getCurrency().getSymbol();
    }

    @Override
    public Locale getCountry() {
        return null;
    }

    @Override
    public boolean equals(CurrencyDesignator c) {
        return this.getCurrency().getNumericCode() == c.getCurrency().getNumericCode();
    }

}
