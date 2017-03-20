package eu.greyson.currency;

import org.jetbrains.annotations.NotNull;

import java.util.Currency;
import java.util.Locale;

public interface CurrencyDesignator {

    /**
     * @return {@code java.util.Currency} object
     */
    @NotNull
    Currency getCurrency();

    /**
     * @return currency code ISO 4217
     */
    @NotNull
    default String getCurrencyCode() {
        return this.getCurrency().getCurrencyCode();
    }

    /**
     * @return If no symbol can be determined then the ISO 4217 currency code is returned.
     * todo: make locale configurable
     */
    @NotNull
    default String getSymbol() {
        return this.getCurrency().getSymbol(Locale.US);
    }

    /**
     * Gets the name that is suitable for displaying this currency.
     * @return
     */
    // todo: add Locale.US to the configuration file
    @NotNull
    default String getDisplayName() { return this.getCurrency().getDisplayName(Locale.US); }

    default boolean equals(CurrencyDesignator currencyDesignator) {
        return this.getCurrency().getNumericCode() == currencyDesignator.getCurrency().getNumericCode();
    }
}
