package eu.greyson.parser;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmountParser implements Parser<BigDecimal> {

    /**
     * Find digits, digits with decimal (. or ,) or digits with dash in front.
     * @param s @param s Parsable string input (probably something like 'USD 800,50').
     * @return BigDecimal with replaced ',' for '.' (800.50)
     */
    @NotNull
    @Override
    public BigDecimal parse(String s) throws ParserException {
        Pattern pattern = Pattern.compile("[-\\d.,]");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            String digits = matcher.group(1);
            String cleanDigits = digits.replaceAll(",", ".");
            return new BigDecimal(cleanDigits);
        } else
            throw ParserException.forInputString(s);
    }
    
}
