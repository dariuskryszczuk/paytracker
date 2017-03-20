package eu.greyson.currency;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static eu.greyson.currency.CurrencySymbol.Symbol.DOLLAR;
import static eu.greyson.currency.CurrencySymbol.Symbol.EURO;
import static eu.greyson.currency.CurrencySymbol.Symbol.POUND;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class CurrencyFactoryTest {

    private List<String> symbols;
    private List<String> codes;
    private List<String> unofficialCodes;

    @Before
    public void setUp() {
        symbols = Arrays.asList(DOLLAR, EURO, POUND);
        codes = Arrays.asList("USD", "EUR", "GBP", "GNF", "INR", "czk", "cZk");
        unofficialCodes = Arrays.asList("CNH", "CTN", "RMB", "GGP", "IMP", "JEP", "KID", "NIS");
    }

    @Test
    public void test_should_create_instances_of_currency_code_class() {
        codes.forEach(c -> assertThat(CurrencyFactory.getCurrency(c), instanceOf(CurrencyCode.class)));
    }

    @Test
    public void test_should_create_instances_of_currency_symbol_class() {
        symbols.forEach(s -> assertThat(CurrencyFactory.getCurrency(s), instanceOf(CurrencySymbol.class)));
    }

    @Test
    public void test_should_create_instances_of_unofficial_class() {
        unofficialCodes.forEach(c -> assertThat(CurrencyFactory.getCurrency(c), instanceOf(UnofficialCurrencyCode.class)));
    }
}
