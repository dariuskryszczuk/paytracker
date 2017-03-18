package eu.greyson.currency;

import java.util.Currency;
import java.util.Locale;

/**
 * <code>CurrencySymbol</code> represents currency signs like $ (U+0024), € (U+20AC) etc.
 */
class CurrencySymbol implements CurrencyDesignator {

    private CurrencySymbol() {}

    public static CurrencySymbol parse(String s) {
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
