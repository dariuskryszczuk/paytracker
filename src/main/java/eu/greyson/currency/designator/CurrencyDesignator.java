package eu.greyson.currency.designator;

import org.jetbrains.annotations.NotNull;

import java.util.Currency;
import java.util.Locale;

public interface CurrencyDesignator {

    /**
     * @return <code>Currency</code> identified by ISO 4217 currency code
     */
    Currency getCurrency();

    /**
     * @return Object representing a specific geographical, political, or cultural region
     */
     Locale getCountry();

    /**
     * @return If no symbol can be determined, the ISO 4217 currency code is returned
     */
    @NotNull
    default String getSymbol() {
        return this.getCurrency().getSymbol();
    }

    @NotNull
    default boolean equals(CurrencyDesignator currencyDesignator) {
        return this.getCurrency().getNumericCode() == currencyDesignator.getCurrency().getNumericCode();
    }
}
