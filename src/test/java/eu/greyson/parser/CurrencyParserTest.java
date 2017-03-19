package eu.greyson.parser;

import eu.greyson.currency.CurrencyDesignator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyParserTest {

    private Parser<CurrencyDesignator> parser;

    @Before
    public void setUp() {
        parser = new CurrencyParser();
    }

    @Test
    public void test_should_return_USD_currency_from_USD_800_string() {
        final String input = "USD 800";
        final CurrencyDesignator currency = parser.parse(input);
        assertEquals("USD", currency.getCurrencyCode());
    }

    @Test
    public void test_should_return_CZK_currency_from_800_CZK_string() {
        final String input = "CZK 800";
        final CurrencyDesignator currency = parser.parse(input);
        assertEquals("CZK", currency.getCurrencyCode());
    }

    @Test
    public void test_should_return_USD_currency_from_$_800_string() {
        final String input = "$ 800";
        final CurrencyDesignator currency = parser.parse(input);
        assertEquals("USD", currency.getCurrencyCode());
    }


    @Test
    public void test_should_return_EUR_currency_from_22euro_symbol_string() {
        final String input = "22€";
        final CurrencyDesignator currency = parser.parse(input);
        assertEquals("EUR", currency.getCurrencyCode());
    }

    /**
     * IMP is an unofficial currency code with value of GBP
     */
    @Test
    public void test_should_return_GBP_currency_from_IMP111_string() {
        final String input = "IMP111";
        final CurrencyDesignator currency = parser.parse(input);
        assertEquals("GBP", currency.getCurrencyCode());
    }


    @Test(expected = ParserException.class)
    public void test_should_throw_parsing_ex_from_missing_currency_info() {
        final String input = "232332";
        parser.parse(input);
    }

}
