package eu.greyson.currency.exchange;

import eu.greyson.currency.designator.CurrencyDesignator;

import java.math.BigDecimal;

public interface Exchangeable {

    ExchangeRate getExchangeRate();
}
