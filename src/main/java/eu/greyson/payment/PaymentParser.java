package eu.greyson.payment;

import com.google.gwt.thirdparty.guava.common.base.CharMatcher;
import eu.greyson.currency.CurrencyFactory;

import java.math.BigDecimal;
import java.text.ParseException;

public class PaymentParser {

    /**
     * @param s Will be usually in format: USD 800
     * @return <code>Payment</code>
     * // todo: add decimal point support
     */
    public static Payment parse(String s) {
        BigDecimal digits = new BigDecimal(s.replaceAll("[^\\d-]", ""));
        String letters = CharMatcher.JAVA_LETTER.retainFrom(s);
        return new Payment(digits, CurrencyFactory.getCurrency(letters));
    }
}
