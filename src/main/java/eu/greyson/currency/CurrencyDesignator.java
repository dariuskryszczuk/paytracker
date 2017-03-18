package eu.greyson.currency;

import org.jetbrains.annotations.NotNull;

import java.util.Currency;
import java.util.Locale;

public interface CurrencyDesignator {

    /**
     * @return <code>Currency</code> identified by ISO 4217 currency code
     */
    @NotNull
    Currency getCurrency();

    /**
     * @return If no symbol can be determined, the ISO 4217 currency code is returned
     */
    @NotNull
    default String getSymbol() {
        return this.getCurrency().getSymbol();
    }

    /**
     * Gets the name that is suitable for displaying this currency.
     * @return
     */
    // todo: add Locale.US to the configuration file
    @NotNull
    default String getDisplayName() { return this.getCurrency().getDisplayName(Locale.US); }

    @NotNull
    default boolean equals(CurrencyDesignator currencyDesignator) {
        return this.getCurrency().getNumericCode() == currencyDesignator.getCurrency().getNumericCode();
    }
}
