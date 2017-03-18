package eu.greyson.currency;

import org.junit.Test;

public class CurrencyCodeTest {

    @Test(expected = CurrencyFormatException.class)
    public void test_should_throw_exception_from_invalid_code_cause() {
        CurrencyFactory.getCurrency("FOO");
    }

}
