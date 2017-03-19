package eu.greyson.payment;

import eu.greyson.currency.CurrencyDesignator;
import eu.greyson.exchange.ExchangeRate;
import eu.greyson.exchange.Exchangeable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;

import static java.math.BigDecimal.ZERO;

public class Payment implements Payable, Exchangeable {

    private BigDecimal amount;
    private CurrencyDesignator currency;

    public Payment(BigDecimal amount, CurrencyDesignator currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public CurrencyDesignator getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @NotNull
    public String getDisplayName() {
        return getCurrency().getCurrencyCode() + " " + getAmount().toString();
    }

    public ExchangeRate getExchangeRate() {
        return null;
    }
}
