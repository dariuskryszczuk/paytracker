package eu.greyson.currency;

import org.junit.Before;
import org.junit.Test;

import static eu.greyson.currency.CurrencySymbol.Symbol.DOLLAR;
import static eu.greyson.currency.CurrencySymbol.Symbol.EURO;
import static eu.greyson.currency.CurrencySymbol.Symbol.POUND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CurrencySymbolTest {

    private CurrencyDesignator dollar1;
    private CurrencyDesignator dollar2;
    private CurrencyDesignator euro;
    private CurrencyDesignator pound;

    @Before
    public void setUp() {
        dollar1 = CurrencyFactory.getCurrency(DOLLAR);
        dollar2 = CurrencyFactory.getCurrency(DOLLAR);
        euro = CurrencyFactory.getCurrency(EURO);
        pound = CurrencyFactory.getCurrency(POUND);
    }

    @Test
    public void test_should_return_true_because_dollars_are_equal() {
        assertTrue(dollar1.equals(dollar2));
    }

    @Test
    public void test_should_return_false_because_dollar_is_not_equal_to_euro() {
        assertFalse(dollar1.equals(euro));
    }

    @Test
    public void test_should_return_$_symbol_for_dollar() {
        assertEquals(DOLLAR, dollar1.getSymbol());
    }

    @Test(expected = CurrencyFormatException.class)
    public void test_should_throw_exception_from_invalid_symbol_cause() {
        CurrencyFactory.getCurrency(DOLLAR + "#");
    }
}
