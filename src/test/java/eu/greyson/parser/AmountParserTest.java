package eu.greyson.parser;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static eu.greyson.currency.CurrencySymbol.Symbol.DOLLAR;
import static org.junit.Assert.assertEquals;

public class AmountParserTest {

    private Parser<BigDecimal> parser;

    @Before
    public void setUp() {
        parser = new AmountParser();
    }

    @Test
    public void test_should_parse_number_550_from_USD550_string() {
        final String input = "USD550";
        assertEquals(new BigDecimal(550), parser.parse(input));
    }

    @Test
    public void test_should_parse_number_550_from_750___EUR_string() {
        final String input = "750   EUR";
        assertEquals(new BigDecimal(750), parser.parse(input));
    }

    @Test
    public void test_should_parse_negative_number_222_from_negative_222_string() {
        final String input = "-222";
        assertEquals(new BigDecimal(-222), parser.parse(input));
    }

    @Test
    public void test_should_parse_number_1dot11_from_1dot11_string() {
        final String input = "1.11";
        assertEquals(BigDecimal.valueOf(1.11), parser.parse(input));
    }

    @Test
    public void test_should_parse_negative_number_1dot11_from_1dot11_string() {
        final String input = "-1.11";
        assertEquals(BigDecimal.valueOf(-1.11), parser.parse(input));
    }

    @Test
    public void test_should_parse_number_1dot11_from_1comma11_string() {
        final String input = "1,11";
        assertEquals(BigDecimal.valueOf(1.11), parser.parse(input));
    }

    @Test
    public void test_should_parse_number_22_from_$22_string() {
        final String input = DOLLAR + "22";
        assertEquals(BigDecimal.valueOf(22), parser.parse(input));
    }

    @Test(expected = ParserException.class)
    public void test_should_throw_parser_ex_from_rubbish_string() {
        final String input = "y u do tis";
        parser.parse(input);
    }
}
