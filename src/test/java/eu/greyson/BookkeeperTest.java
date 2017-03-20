package eu.greyson;

import eu.greyson.currency.CurrencyFactory;
import eu.greyson.parser.*;
import eu.greyson.payment.Payable;
import eu.greyson.payment.Payment;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    }

    @Test
    public void test_should_sort_list_of_payments_with_currency_code() {
        Bookkeeper.addAll(payments);
        List<Payable> payables = Bookkeeper.getSortedByCode();
        assertEquals("CNY 2000", payables.get(0).getDisplayName());
        assertEquals("CZK 300", payables.get(1).getDisplayName());
        assertEquals("HKD 100", payables.get(2).getDisplayName());
        assertEquals("HKD 200", payables.get(3).getDisplayName());
        assertEquals("USD 1000", payables.get(4).getDisplayName());
        assertEquals("USD -100", payables.get(5).getDisplayName());

    }

}
