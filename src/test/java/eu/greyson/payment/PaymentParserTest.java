package eu.greyson.payment;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static eu.greyson.payment.PaymentParser.parse;
import static org.junit.Assert.assertEquals;

public class PaymentParserTest {

    private Path testInput;

    @Before
    public void setUp() {
        String path = this.getClass().getResource("test-input.txt").getPath();
        testInput = Paths.get(path);
    }

    @Test
    public void test_should_parse_payment_from_positive_number_and_code_string() {
        final String input = "USD 800";
        assertEquals(new BigDecimal(800), parse(input).getAmount());
        assertEquals("USD", parse(input).getCurrency().getCurrencyCode());
    }

    @Test
    public void test_should_parse_payment_from_negative_number_and_code_string() {
        final String input = "USD -800";
        assertEquals(new BigDecimal(-800), parse(input).getAmount());
        assertEquals("USD", parse(input).getCurrency().getCurrencyCode());
    }

    @Test
    public void test_should_parse_payment_from_large_negative_number_and_code_string() {
        final String input = "USD-100 234 873 983";
        assertEquals(new BigDecimal(-100234873983L), parse(input).getAmount());
        assertEquals("USD", parse(input).getCurrency().getCurrencyCode());
    }

    @Ignore("Need to add decimal point support!")
    @Test
    public void test_should_parse_payment_from_number_with_decimal_and_code_string() {
        final String input = "USD100.50";
        assertEquals(new BigDecimal(100.50), parse(input).getAmount());
        assertEquals("USD", parse(input).getCurrency().getCurrencyCode());
    }

    @Test
    public void test_should_parse_payment_from_$_and_negative_number_string() {
        final String input = "-350$";
        assertEquals(new BigDecimal(-350), parse(input).getAmount());
        assertEquals("USD", parse(input).getCurrency().getCurrencyCode());
    }

    @Test
    public void test_should_parse_payment_from_euro_symbol_and_large_number_string() {
        final String input = "€100673826384756298384756";
        assertEquals(new BigDecimal(100673826384756298384756D), parse(input).getAmount());
        assertEquals("EUR", parse(input).getCurrency().getCurrencyCode());
    }

    @Test
    public void test_should_parse_payment_from_pound_symbol_and_large_number_string() {
        final String input = "£ 100 300 2200";
        assertEquals(new BigDecimal(1003002200), parse(input).getAmount());
        assertEquals("GBP", parse(input).getCurrency().getCurrencyCode());
    }

    @Test
    public void test_should_return_five_payment_objects_from_input_file() throws IOException {
        final List<Payable> paymentList = parse(testInput);
        assertEquals(5, paymentList.size());
    }

    @Test
    public void test_should_return_USD_payment_from_test_input_file() throws IOException {
        final List<Payable> paymentList = parse(testInput);
        assertEquals("USD", paymentList.get(0).getCurrency().getCurrencyCode());
        assertEquals(new BigDecimal(1000), paymentList.get(0).getAmount());
    }

    @Test
    public void test_should_return_HKD_payment_from_test_input_file() throws IOException {
        final List<Payable> paymentList = parse(testInput);
        assertEquals("HKD", paymentList.get(1).getCurrency().getCurrencyCode());
        assertEquals(new BigDecimal(100), paymentList.get(1).getAmount());
    }

    @Test
    public void test_should_return_USD_negative_payment_from_test_input_file() throws IOException {
        final List<Payable> paymentList = parse(testInput);
        assertEquals("USD", paymentList.get(2).getCurrency().getCurrencyCode());
        assertEquals(new BigDecimal(-100), paymentList.get(2).getAmount());
    }

    @Test
    public void test_should_return_CNY_not_RMB_payment_from_test_input_file() throws IOException {
        final List<Payable> paymentList = parse(testInput);
        assertEquals("CNY", paymentList.get(3).getCurrency().getCurrencyCode());
        assertEquals(new BigDecimal(2000), paymentList.get(3).getAmount());
    }

    @Test
    public void test_should_return_another_HKD_payment_from_test_input_file() throws IOException {
        final List<Payable> paymentList = parse(testInput);
        assertEquals("HKD", paymentList.get(4).getCurrency().getCurrencyCode());
        assertEquals(new BigDecimal(200), paymentList.get(4).getAmount());
    }

}
