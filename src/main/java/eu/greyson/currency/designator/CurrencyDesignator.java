package eu.greyson.currency.designator;

import java.util.Currency;
import java.util.Locale;

public interface CurrencyDesignator {

    /**
     * @return <code>Currency</code> identified by ISO 4217 currency code
     */
    Currency getCurrency();

    /**
     * @return object representing a specific geographical, political, or cultural region
     */
    Locale getCountry();
}
