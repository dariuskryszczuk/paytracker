package eu.greyson.currency;

import org.jetbrains.annotations.NotNull;

import java.util.Currency;
import java.util.Locale;

/**
 * <code>CurrencySymbol</code> represents currency signs like $ (U+0024), € (U+20AC) etc.
 */
class CurrencySymbol implements CurrencyDesignator {

    private Currency currency;

    private CurrencySymbol(Currency currency) {
        this.currency = currency;
    }

    @NotNull
    static CurrencySymbol valueOf(String s) throws CurrencyFormatException {
        if (s.trim().length() == 0 || s.trim().length() > 1)
            throw CurrencyFormatException.forInputString(s);
        if (s.contains("$"))
            return new CurrencySymbol(Currency.getInstance("USD"));
        else if (s.contains("€"))
            return new CurrencySymbol(Currency.getInstance("EUR"));
        else if (s.contains("£"))
            return new CurrencySymbol(Currency.getInstance("GBP"));
        else
            throw CurrencyFormatException.forInputString(s);
    }

    @Override
    @NotNull
    public Currency getCurrency() {
        return currency;
    }
}
