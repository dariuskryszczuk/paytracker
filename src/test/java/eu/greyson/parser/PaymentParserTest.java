package eu.greyson.parser;

import eu.greyson.currency.CurrencyDesignator;
import eu.greyson.payment.Payable;
import eu.greyson.payment.Payment;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static eu.greyson.currency.CurrencySymbol.Symbol.DOLLAR;
import static eu.greyson.currency.CurrencySymbol.Symbol.EURO;
import static eu.greyson.currency.CurrencySymbol.Symbol.POUND;
import static org.junit.Assert.assertEquals;

public class PaymentParserTest {

    private Parser<CurrencyDesignator> currencyParser;
    private Parser<BigDecimal> amountParser;
    private Parser<Payable> parser;

    @Before
    public void setUp() {
        currencyParser = new CurrencyParser();
        amountParser = new AmountParser();
        parser = new PaymentParser(currencyParser, amountParser);
    }

    @Test
    public void test_should_parse_payment_from_positive_number_and_code_string() {
        final String input = "USD 800";
        final Payable p = parser.parse(input);
        assertEquals(new BigDecimal(800),p.getAmount());
        assertEquals("USD", p.getCurrency().getCurrencyCode());
        assertEquals(input, p.getDisplayName());
    }

    @Test
    public void test_should_parse_payment_from_negative_number_and_code_string() {
        final String input = "USD -800";
        final Payable p = parser.parse(input);
        assertEquals(new BigDecimal(-800), p.getAmount());
        assertEquals("USD", p.getCurrency().getCurrencyCode());
        assertEquals("USD -800", p.getDisplayName());
    }

    @Test
    public void test_should_parse_payment_from_large_negative_number_and_code_string() {
        final String input = "USD-100 234 873 983";
        final Payable p = parser.parse(input);
        assertEquals(new BigDecimal(-100234873983L), p.getAmount());
        assertEquals("USD", p.getCurrency().getCurrencyCode());
        assertEquals("USD -100234873983", p.getDisplayName());
    }

    @Test
    public void test_should_parse_payment_from_number_with_decimal_and_code_string() {
        final String input = "USD100.5";
        final Payable p = parser.parse(input);
        assertEquals(BigDecimal.valueOf(100.5), p.getAmount());
        assertEquals("USD", p.getCurrency().getCurrencyCode());
        assertEquals("USD 100.5", p.getDisplayName());
    }

    @Test
    public void test_should_parse_payment_from_$_and_negative_number_string() {
        final String input = "-350" + DOLLAR;
        final Payable p = parser.parse(input);
        assertEquals(new BigDecimal(-350), p.getAmount());
        assertEquals("USD", p.getCurrency().getCurrencyCode());
        assertEquals("USD -350", p.getDisplayName());
    }

    @Test
    public void test_should_parse_payment_from_euro_symbol_and_large_number_string() {
        final String input = EURO + "100673826384756";
        final Payable p = parser.parse(input);
        assertEquals(BigDecimal.valueOf(100673826384756D), p.getAmount());
        assertEquals("EUR", p.getCurrency().getCurrencyCode());
        assertEquals("EUR 100673826384756", p.getDisplayName());
    }

    @Test
    public void test_should_parse_payment_from_pound_symbol_and_large_number_string() {
        final String input = POUND + " 100 300 2200";
        final Payable p = parser.parse(input);
        assertEquals(new BigDecimal(1003002200), p.getAmount());
        assertEquals("GBP", p.getCurrency().getCurrencyCode());
        assertEquals("GBP 1003002200", p.getDisplayName());
    }
}
