package eu.greyson.exchange;

import eu.greyson.currency.CurrencyDesignator;

import java.math.BigDecimal;

public interface ExchangeRate {

    /**
     * The notation 25 USD/CZK means that one dollar is bought for 25 CZK
     *
     * @return USD
     */
    CurrencyDesignator getFixedCurrency();

    /**
     * @return CZK
     */
    CurrencyDesignator getVariableCurrency();

    /**
     * @return 25
     */
    BigDecimal getRate();
}
