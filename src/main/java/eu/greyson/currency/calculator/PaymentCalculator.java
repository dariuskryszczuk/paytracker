package eu.greyson.currency.calculator;

import eu.greyson.payment.Payment;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PaymentCalculator {

    private PaymentCalculator() {}

    // todo
    public static Payment sum(final Payment p1, Payment... p2) {
        BigDecimal sum = p1.getAmount();
        Arrays.asList(p2).forEach(p -> sum.add(p.getAmount()));
        return sum;
    }

    // todo
    public static Payment sum(final List<Payment> payments) {
        BigDecimal sum = new BigDecimal(0);
        payments.forEach(p -> sum.add(p.getAmount()));
        return sum;
    }

    // todo
    public static Payment difference(final Payment p1, Payment p2) {
        return p1.getAmount().subtract(p2.getAmount());
    }


}
