package eu.greyson.payment;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static eu.greyson.currency.CurrencyFactory.getCurrency;
import static org.junit.Assert.assertEquals;

public class PaymentTest {

    private Payable dollar800;
    private Payable euro350;
    private Payable negativeKc222;

    @Before
    public void setUp() {
        dollar800 = new Payment(BigDecimal.valueOf(800), getCurrency("USD"));
        euro350 = new Payment(BigDecimal.valueOf(350), getCurrency("EUR"));
        negativeKc222 = new Payment(BigDecimal.valueOf(-222), getCurrency("CZK"));
    }

    @Test
    public void test_should_check_fields_from_800_dollar_payment() {
        assertEquals(BigDecimal.valueOf(800), dollar800.getAmount());
        assertEquals("USD", dollar800.getCurrency().getCurrencyCode());
        assertEquals("USD 800", dollar800.getDisplayName());
    }

    @Test
    public void test_should_check_fields_from_350_eur_payment() {
        assertEquals(BigDecimal.valueOf(350), euro350.getAmount());
        assertEquals("EUR", euro350.getCurrency().getCurrencyCode());
        assertEquals("EUR 350", euro350.getDisplayName());
    }

    @Test
    public void test_should_check_fields_from_negative_222_kc_payment() {
        assertEquals(BigDecimal.valueOf(-222), negativeKc222.getAmount());
        assertEquals("CZK", negativeKc222.getCurrency().getCurrencyCode());
        assertEquals("CZK -222", negativeKc222.getDisplayName());
    }
}
