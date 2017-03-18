package eu.greyson.payment;

import eu.greyson.currency.designator.CurrencyDesignator;
import eu.greyson.currency.exchange.ExchangeRate;
import eu.greyson.currency.exchange.Exchangeable;

import java.math.BigDecimal;
import java.util.Locale;

public class Payment implements Payable, Exchangeable {

    private BigDecimal amount;
    private Locale locale;

    private Payment(BigDecimal amount, Locale locale) {
        this.amount = amount;
        this.locale = locale;
    }

    public Payment parse(String s) {
        // todo
        return null;
    }

    public CurrencyDesignator getDesignator() {
        return null;
    }

    public BigDecimal getAmount() {
        return null;
    }

    public String getDisplayName() {
        return null;
    }

    public ExchangeRate getExchangeRate() {
        return null;
    }
}
