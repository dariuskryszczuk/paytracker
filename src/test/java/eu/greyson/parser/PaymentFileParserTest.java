package eu.greyson.parser;

import eu.greyson.payment.Payable;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PaymentFileParserTest {

    private String path;
    private Parser<List<Payable>> parser;

    @Before
    public void setUp() throws IOException {
        URL url = this.getClass().getResource("test-input.txt");
        File f = new File(url.getFile());
        Parser<Payable> paymentParser = new PaymentParser(new CurrencyParser(), new AmountParser());
        path = f.getCanonicalPath();
        parser = new PaymentFileParser(paymentParser);
    }

    @Test
    public void test_should_return_five_payment_objects_from_input_file() throws IOException {
        final List<Payable> paymentList = parser.parse(path);
        assertEquals(5, paymentList.size());
    }

    @Test
    public void test_should_return_USD_payment_from_test_input_file() throws IOException {
        final List<Payable> paymentList = parser.parse(path);
        assertEquals("USD", paymentList.get(0).getCurrency().getCurrencyCode());
        assertEquals(new BigDecimal(1000), paymentList.get(0).getAmount());
    }

    @Test
    public void test_should_return_HKD_payment_from_test_input_file() throws IOException {
        final List<Payable> paymentList = parser.parse(path);
        assertEquals("HKD", paymentList.get(1).getCurrency().getCurrencyCode());
        assertEquals(new BigDecimal(100), paymentList.get(1).getAmount());
    }

    @Test
    public void test_should_return_USD_negative_payment_from_test_input_file() throws IOException {
        final List<Payable> paymentList = parser.parse(path);
        assertEquals("USD", paymentList.get(2).getCurrency().getCurrencyCode());
        assertEquals(new BigDecimal(-100), paymentList.get(2).getAmount());
    }

    /**
     * CNY and *NOT* RMB because RMB is an unofficial currency code for chinese juan (CNY)
     * @throws IOException
     */
    @Test
    public void test_should_return_CNY_not_RMB_payment_from_test_input_file() throws IOException {
        final List<Payable> paymentList = parser.parse(path);
        assertEquals("CNY", paymentList.get(3).getCurrency().getCurrencyCode());
        assertEquals(new BigDecimal(2000), paymentList.get(3).getAmount());
    }

    @Test
    public void test_should_return_another_HKD_payment_from_test_input_file() throws IOException {
        final List<Payable> paymentList = parser.parse(path);
        assertEquals("HKD", paymentList.get(4).getCurrency().getCurrencyCode());
        assertEquals(new BigDecimal(200), paymentList.get(4).getAmount());
    }
}
