package eu.greyson.parser;

import eu.greyson.currency.CurrencyDesignator;
import eu.greyson.payment.Payable;
import eu.greyson.payment.Payment;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class PaymentParser implements Parser<Payable> {

    private Parser<CurrencyDesignator> currencyParser;
    private Parser<BigDecimal> amountParser;

    public PaymentParser(Parser<CurrencyDesignator> currencyParser, Parser<BigDecimal> amountParser) {
        this.currencyParser = currencyParser;
        this.amountParser = amountParser;
    }

    /**
     * @param s Parsable string input (something like 'USD 800,50').
     * @return
     * @throws ParserException
     */
    @NotNull
    @Override
    public Payable parse(String s) throws ParserException {
        // get amount:BigDecimal from string
        BigDecimal amount = amountParser.parse(s);

        // get currency:CurrencyDesignator
        CurrencyDesignator currency = currencyParser.parse(s);

        // create payment:Playable
        return new Payment(amount, currency);
    }
}
