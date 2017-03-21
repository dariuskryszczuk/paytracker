package eu.greyson;

import eu.greyson.parser.*;
import eu.greyson.payment.Payable;
import eu.greyson.print.PrintJob;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static eu.greyson.print.PrintManager.*;

public class Paytracker {

    public static void main(String args[]) throws IOException {
        printWelcome();
        printLoadFileDialogQuestion();
        PrintJob.run(60l, 60l);
        while(true) {
            try {
                String input = printPaymentQuestion();
                if (input.equalsIgnoreCase("quit")) {  break; }
                if (input.equalsIgnoreCase("print")) {printTotalTable(); continue;}
                Parser<Payable> payableParser = new PaymentParser(new CurrencyParser(), new AmountParser());
                Payable p = payableParser.parse(input);
                Bookkeeper.add(p);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        System.exit(1);
    }
}
