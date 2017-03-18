package eu.greyson.currency;

import java.util.Currency;
import java.util.Locale;

/**
 * <code>CurrencyCode</code> represents ISO 4217 standard of currency designators.
 * @see <a href="https://www.iso.org/iso-4217-currency-codes.html"></a>
 */
class CurrencyCode implements CurrencyDesignator {

    private CurrencyCode() {}

    public static CurrencyCode parse(String s) {
        return null;
    }

    @Override
    public Currency getCurrency() {
        return null;
    }

    @Override
    public Locale getCountry() {
        return null;
    }

}
