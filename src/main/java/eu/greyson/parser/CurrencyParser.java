package eu.greyson.parser;

import eu.greyson.currency.CurrencyDesignator;
import eu.greyson.currency.CurrencyFactory;
import org.jetbrains.annotations.NotNull;

public class CurrencyParser implements Parser<CurrencyDesignator> {

    /**
     * Find every char which is not digit, '-', ',' or '.'.
     * @param s Parsable string input (probably something like 'USD 800,50').
     * @return CurrencyDesignator (USD)
     */
    @NotNull
    @Override
    public CurrencyDesignator parse(String s) throws ParserException {
        String lettersAndSymbols = s.replaceAll("[-\\d.,]", "");
        if (lettersAndSymbols.length() > 0)
            return CurrencyFactory.getCurrency(lettersAndSymbols);
        else
            throw ParserException.forInputString(s);
    }
}
