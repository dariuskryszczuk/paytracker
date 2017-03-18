package eu.greyson.currency;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class CurrencyFactoryTest {

    private List<String> symbols;
    private List<String> codes;

    @Before
    public void setUp() {
        symbols = Arrays.asList("$", "€", "£");
        codes = Arrays.asList("USD", "EUR", "GBP", "GNF", "INR", "CZK", "cZk");
    }

    @Test
    public void test_should_create_instances_of_currency_code_class() {
        codes.forEach(c -> assertThat(CurrencyFactory.getCurrency(c), instanceOf(CurrencyCode.class)));
    }

    @Test
    public void test_should_create_instances_of_currency_symbol_class() {
        symbols.forEach(s -> assertThat(CurrencyFactory.getCurrency(s), instanceOf(CurrencySymbol.class)));
    }
}
