package eu.greyson.payment;

import com.google.gwt.thirdparty.guava.common.base.CharMatcher;
import eu.greyson.currency.CurrencyFactory;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class PaymentParser {

    /**
     * @param s Will be usually in format: USD 800
     * @return <code>Payable</code>
     * todo: add decimal point and symbol support
     */
    static Payable parse(String s) {
        BigDecimal digits = new BigDecimal(s.replaceAll("[^\\d-]", ""));
        String letters = CharMatcher.JAVA_LETTER.retainFrom(s);
        return new Payment(digits, CurrencyFactory.getCurrency(letters));
    }

    /**
     * Parse method for the starter input file with amount and currency/symbol on each line:
     * USD 1000
     * HKD 100
     * @param p
     * @return
     */
    static List<Payable> parse(Path p) throws IOException {
        List<Payable> payments = new ArrayList<>();
        try (Stream<String> stream = Files.lines(p)) {
            stream.forEach(row -> payments.add(PaymentParser.parse(row)));
        }
        return payments;
    }
}
