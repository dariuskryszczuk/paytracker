package eu.greyson.calculator;

import eu.greyson.payment.Payable;
import eu.greyson.payment.Payment;
import org.junit.Before;
import org.junit.Test;

import static eu.greyson.calculator.MoneyCalculator.minus;
import static eu.greyson.calculator.MoneyCalculator.plus;
import static eu.greyson.calculator.MoneyCalculator.sum;
import static eu.greyson.currency.CurrencyFactory.getCurrency;
import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;

public class MoneyCalculatorTest {

    private Payable dollar300;
    private Payable dollarNegative150;
    private Payable dollar500;
    private Payable euro20;

    @Before
    public void setUp() {
        dollar300 = new Payment(valueOf(300), getCurrency("USD"));
        dollarNegative150 = new Payment(valueOf(-150), getCurrency("USD"));
        dollar500 = new Payment(valueOf(500), getCurrency("$"));
        euro20 = new Payment(valueOf(20), getCurrency("EUR"));
    }

    @Test
    public void test_should_sum_300_dollars_and_500_dollars_and_300_dollars() {
        assertEquals(valueOf(1100), sum(dollar300, dollar500, dollar300).getAmount());
    }

    @Test
    public void test_should_add_300_dollars_to_negative_150_dollars() {
        assertEquals(valueOf(150), plus(dollar300, dollarNegative150).getAmount());
    }

    @Test
    public void test_should_min_500_dollars_to_300_dollars() {
        assertEquals(valueOf(200), minus(dollar500, dollar300).getAmount());
    }

    @Test(expected = UnsupportedMoneyOperation.class)
    public void test_should_throw_ex_for_differenc_currencies_cause() {
        plus(dollar300, euro20);
    }



}
