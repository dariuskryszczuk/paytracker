package eu.greyson.payment;

import eu.greyson.currency.CurrencyDesignator;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public interface Payable {

    /**
     * Currency designator can be in the form of the currency code ISO 4217 (USD, CZK etc.),
     * currency symbol ($, € etc.), unofficial currency code etc.
     *
     * @return the implementation of the specific currency designator
     */
    CurrencyDesignator getCurrency();

    BigDecimal getAmount();

    /**
     * @return Designator and amount (USD 800, CZK 300 etc.)
     */
    @NotNull
    String getDisplayName();

    /**
     *
     * @param currency
     * @return {@code true} if the currency is the same.
     */
    default boolean isSameCurrency(CurrencyDesignator currency) {
        return this.getCurrency().equals(currency);
    }

    Payable add(Payable payable);
}
