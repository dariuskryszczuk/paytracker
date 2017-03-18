package eu.greyson.currency;

import static java.lang.Character.CURRENCY_SYMBOL;

public class CurrencyFactory {

    public static CurrencyDesignator getCurrency(String designator) {
        if (containsCurrencySymbol(designator))
            return CurrencySymbol.valueOf(designator);
        else
            return CurrencyCode.valueOf(designator);
    }

    private static boolean containsCurrencySymbol(String s) {
        for (char c : s.toCharArray()) {
            if (Character.getType(c) == CURRENCY_SYMBOL)
                return true;
        }
        return false;
    }
}
