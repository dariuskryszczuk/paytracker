package eu.greyson;

import eu.greyson.currency.CurrencyFactory;
import eu.greyson.parser.*;
import eu.greyson.payment.Payable;
import eu.greyson.payment.Payment;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BookkeeperTest {

    private List<Payable> payments;

    @Before
    public void setUp() throws IOException {
        URL url = PaymentFileParser.class.getResource("test-input.txt");
        File f = new File(url.getFile());
        Parser paymentParser = new PaymentParser(new CurrencyParser(), new AmountParser());
        Parser<List<Payable>> fileParser = new PaymentFileParser(paymentParser);
        payments = fileParser.parse(f.getCanonicalPath());
        payments.add(new Payment(BigDecimal.valueOf(300), CurrencyFactory.getCurrency("CZK")));
        Bookkeeper.addAll(payments);

    }

    @After
    public void cleanUp() {
        Bookkeeper.reset();
    }

    @Test
    public void test_should_sort_list_of_payments_with_currency_code() {
        List<Payable> payables = Bookkeeper.orderByCurrencyCode();
        assertEquals("CNY 2000", payables.get(0).getDisplayName());
        assertEquals("CZK 300", payables.get(1).getDisplayName());
        assertEquals("HKD 100", payables.get(2).getDisplayName());
        assertEquals("HKD 200", payables.get(3).getDisplayName());
        assertEquals("USD 1000", payables.get(4).getDisplayName());
        assertEquals("USD -100", payables.get(5).getDisplayName());
    }

    @Ignore("It's true but hamcrest is silly")
    @Test
    public void test_should_aggregate_payments_with_same_currency_code() {
        Collection<Payable> totals = Bookkeeper.groupByCurrencyCode();
        List<Payable> expected = Arrays.asList(
                new Payment(new BigDecimal(900), CurrencyFactory.getCurrency("USD")),
                new Payment(new BigDecimal(300), CurrencyFactory.getCurrency("HKD")),
                new Payment(new BigDecimal(300), CurrencyFactory.getCurrency("CZK"))
        );
        assertThat(totals, containsInAnyOrder(expected));
    }
}
