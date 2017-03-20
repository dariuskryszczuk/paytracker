package eu.greyson.currency;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

/**
 * {@code UnofficialCurrencyCode} represents currency codes that are widely used but
 * are *NOT* part of the ISO 4217 standard of currency designators.
 *
 * These are for example Chinese yuan offshore (CNH), Jersey pound (JEP) and more.
 * @see <a href="https://en.wikipedia.org/wiki/ISO_4217#Unofficial_currency_codes"></a>
 */
class UnofficialCurrencyCode implements CurrencyDesignator {

    private Currency currency;

    private UnofficialCurrencyCode(Currency currency) {
        this.currency = currency;
    }

    static UnofficialCurrencyCode valueOf(String s) throws CurrencyFormatException {
        switch (s.trim().toUpperCase()) {
            case "CNH":
            case "CTN":
            case "RMB":
                return new UnofficialCurrencyCode(Currency.getInstance("CNY"));
            case "GGP":
            case "IMP":
            case "JEP":
                return new UnofficialCurrencyCode(Currency.getInstance("GBP"));
            case "KID":
                return new UnofficialCurrencyCode(Currency.getInstance("AUD"));
            case "NIS":
                return new UnofficialCurrencyCode(Currency.getInstance("ILS"));
            default:
                throw CurrencyFormatException.forInputString(s);
        }
    }

    @Override
    @NotNull
    public Currency getCurrency() {
        return currency;
    }

    /**
     * @return List of all unofficial currency codes.
     */
    static List<String> getList() {
        return Arrays.asList("CNH", "CTN", "RMB", "GGP", "IMP", "JEP", "KID", "NIS", "PRB", "TVD");
    }
}
