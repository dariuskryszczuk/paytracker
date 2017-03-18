package eu.greyson.payment;

import com.google.gwt.thirdparty.guava.common.base.CharMatcher;
import eu.greyson.currency.CurrencyDesignator;
import eu.greyson.currency.CurrencyFactory;
import eu.greyson.exchange.ExchangeRate;
import eu.greyson.exchange.Exchangeable;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Locale;

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
        return currency.getDisplayName();
    }

    public ExchangeRate getExchangeRate() {
        return null;
    }
}
