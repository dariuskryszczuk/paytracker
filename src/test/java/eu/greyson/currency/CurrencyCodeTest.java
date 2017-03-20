package eu.greyson.currency;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static eu.greyson.currency.CurrencySymbol.Symbol.DOLLAR;
import static eu.greyson.currency.CurrencySymbol.Symbol.EURO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CurrencyCodeTest {

    private CurrencyDesignator dollar1;
    private CurrencyDesignator dollar2;
    private CurrencyDesignator euro;
    private CurrencyDesignator pound;

    @Before
    public void setUp() {
        dollar1 = CurrencyFactory.getCurrency("USD");
        dollar2 = CurrencyFactory.getCurrency("USD");
        euro = CurrencyFactory.getCurrency("EUR");
        pound = CurrencyFactory.getCurrency("GBP");
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
    public void test_should_return_$_symbol() {
        assertEquals(DOLLAR, dollar1.getSymbol());
    }

    @Ignore("Need to add locale")
    @Test
    public void test_should_return_euro_symbol() {
        assertEquals(EURO, euro.getSymbol());
    }

    @Ignore("Need to add locale")
    @Test
    public void test_should_return_pound_symbol() {
        assertEquals("£", pound.getSymbol());
    }

    @Test
    public void test_should_return_display_name_for_dollar() {
        assertEquals("US Dollar", dollar1.getDisplayName());
    }

    @Test
    public void test_should_return_display_name_for_euro() {
        assertEquals("Euro", euro.getDisplayName());
    }

    @Test
    public void test_should_return_display_name_for_pound() {
        assertEquals("British Pound Sterling", pound.getDisplayName());
    }

    @Test(expected = CurrencyFormatException.class)
    public void test_should_throw_exception_from_invalid_code_cause() {
        CurrencyFactory.getCurrency("dolan");
    }

}
