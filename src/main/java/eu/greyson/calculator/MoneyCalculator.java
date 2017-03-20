package eu.greyson.calculator;

import eu.greyson.payment.Payable;
import eu.greyson.payment.Payment;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

/**
 * {@code MoneyCalculator} for mathematical operations with {@code Payable} objects.
 */
@Deprecated
public class MoneyCalculator {

    private MoneyCalculator() {}

    public static Payable sum(Payable p1, Payable... p2) {
        BigDecimal sum = p1.getAmount();
        for (Payable p : p2) {
            sum = sum.add(p.getAmount());
        }
        return new Payment(sum, p1.getCurrency());
    }

    @NotNull
    public static Payable plus(Payable p1, Payable p2) {
        if (p1.isSameCurrency(p2.getCurrency())) {
            BigDecimal sum = p1.getAmount().add(p2.getAmount());
            return new Payment(sum, p1.getCurrency());
        }
        throw new UnsupportedMoneyOperation("Can't aggregate payments with different currencies!");
    }

    @NotNull
    public static Payable minus(Payable p1, Payable p2) {
        if (p1.isSameCurrency(p2.getCurrency())) {
            BigDecimal min = p1.getAmount().subtract(p2.getAmount());
            return new Payment(min, p1.getCurrency());
        }
        throw new UnsupportedMoneyOperation("Can't subtract payments with different currencies!");
    }

    public static boolean isPositive(Payable p) {
        return p.getAmount().compareTo(ZERO) > 0;
    }

    public static boolean isNegative(Payable p) {
        return p.getAmount().compareTo(ZERO) < 0;
    }

    public static boolean isZero(Payable p) {
        return p.getAmount().compareTo(ZERO) == 0;
    }
}
