package eu.greyson.currency;

import static java.lang.Character.CURRENCY_SYMBOL;

public class CurrencyFactory {

    /**
     * Factory of different types of currency designators.
     * @param designator
     * @return
     */
    public static CurrencyDesignator getCurrency(String designator) {

        // check for symbol
        if (containsCurrencySymbol(designator))
            return CurrencySymbol.valueOf(designator);

        // check for unofficial code
        else if (UnofficialCurrencyCode.getList().contains(designator.trim()))
            return UnofficialCurrencyCode.valueOf(designator);

        // return regular currency code
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
