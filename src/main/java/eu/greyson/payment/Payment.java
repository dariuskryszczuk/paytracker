package eu.greyson.payment;

import eu.greyson.currency.CurrencyDesignator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class Payment implements Payable {

    private BigDecimal amount;
    private CurrencyDesignator currency;

    public Payment(BigDecimal amount, CurrencyDesignator currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Payment zero(CurrencyDesignator currency) {
        return new Payment(new BigDecimal(0), currency);
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

    @Override
    public Payable add(Payable payable) {
        if (!this.isSameCurrency(payable.getCurrency())) {
            throw new IllegalArgumentException("Trying to add two different currencies");
        }
        BigDecimal total = this.getAmount().add(payable.getAmount());
        return new Payment(total, this.currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        return new EqualsBuilder()
                .append(amount, payment.amount)
                .append(currency, payment.currency)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(amount)
                .append(currency)
                .toHashCode();
    }

    @Override
    public String toString() {
        return currency.toString() + " " + getAmount().toString();
    }
    
}
