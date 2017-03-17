package eu.greyson.payment;

import eu.greyson.currency.designator.CurrencyDesignator;
import eu.greyson.currency.exchange.Exchangeable;

import java.math.BigDecimal;
import java.util.Locale;

public class Payment implements Payable, Exchangeable {

    private BigDecimal amount;
    private Locale locale;

    Payment(BigDecimal amount, Locale locale) {
        this.amount = amount;
        this.locale = locale;
    }

    @Override
    public CurrencyDesignator getFixedCurrency() {
        return null;
    }

    @Override
    public CurrencyDesignator getVariableCurrency() {
        return null;
    }

    @Override
    public BigDecimal getRate() {
        return null;
    }

    @Override
    public CurrencyDesignator getDesignator() {
        return null;
    }

    @Override
    public BigDecimal getAmount() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }
}
