package eu.greyson.parser;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
        String amount = findAmount(s);
        if (amount.trim().length() > 0) {
            String cleanDigits = amount.replaceAll(",", ".").replaceAll(" ", "");
            return new BigDecimal(cleanDigits);
        } else
            throw ParserException.forInputString(s);
    }

    static String findAmount(String s) {
        List<String> allMatches = new ArrayList<>();
        Pattern pattern = Pattern.compile("[-\\d]?[\\d., ][\\d]?");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            allMatches.add(matcher.group());
        }
        return StringUtils.join(allMatches, "");
    }
}
