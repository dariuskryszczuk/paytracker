package eu.greyson.payment;

import eu.greyson.currency.CurrencyDesignator;
import eu.greyson.exchange.ExchangeRate;
import eu.greyson.exchange.Exchangeable;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Locale;

public class Payment implements Payable, Exchangeable {

    private BigDecimal amount;
    private Locale locale;

    private Payment(BigDecimal amount, Locale locale) {
        this.amount = amount;
        this.locale = locale;
    }

    public static Payment parse(String s) {
        // todo
        return null;
    }

    public CurrencyDesignator getDesignator() {
        return null;
    }

    public BigDecimal getAmount() {
        return null;
    }

    @NotNull
    public String getDisplayName() {
        return "foo";
    }

    public ExchangeRate getExchangeRate() {
        return null;
    }
}
