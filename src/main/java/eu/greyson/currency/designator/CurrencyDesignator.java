package eu.greyson.currency.designator;

import java.util.Currency;
import java.util.Locale;

public interface CurrencyDesignator {

    /**
     * @return <code>Currency</code> identified by ISO 4217 currency code
     */
    Currency getCurrency();

    /**
     * @return symbol of this currency
     */
    String getSymbol();

    /**
     * @return object representing a specific geographical, political, or cultural region
     */
    Locale getCountry();

    /**
     * override of the objects method is needed
     * @return
     */
    boolean equals(CurrencyDesignator currencyDesignator);
}
