package eu.greyson;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;

public class Paytracker {

    public static void main(String args[]) throws ParseException {
        NumberFormat n = NumberFormat.getCurrencyInstance();
        Currency currency = Currency.getInstance("$");
    }

}
