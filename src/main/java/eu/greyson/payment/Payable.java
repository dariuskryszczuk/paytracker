package eu.greyson.payment;

import eu.greyson.currency.designator.CurrencyDesignator;

import java.math.BigDecimal;

public interface Payable {
    /**
     * Designator can be currency code ISO 4217 (USD, CZK etc.) or currency symbol ($, € etc.)
     * @return the implementation of the specific currency designator
     */
    CurrencyDesignator getDesignator();

    BigDecimal getAmount();

    /**
     * @return Designator and amount (USD 800, CZK 300 etc.)
     */
    String getDisplayName();
}
