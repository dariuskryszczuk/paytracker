package eu.greyson.parser;

import eu.greyson.currency.CurrencyDesignator;
import eu.greyson.payment.Payable;
import eu.greyson.payment.Payment;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class PaymentParser implements Parser<Payable> {

    /**
     * @param s Parsable string input (something like 'USD 800,50').
     * @return
     * @throws ParserException
     */
    @NotNull
    @Override
    public Payable parse(String s) throws ParserException {
        // get amount:BigDecimal from string
        Parser<BigDecimal> amountParser = new AmountParser();
        BigDecimal amount = amountParser.parse(s);

        // get currency:CurrencyDesignator
        Parser<CurrencyDesignator> currencyParser = new CurrencyParser();
        CurrencyDesignator currency = currencyParser.parse(s);

        // create payment:Playable
        return new Payment(amount, currency);
    }
}
