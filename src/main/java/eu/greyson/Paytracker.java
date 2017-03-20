package eu.greyson;

import eu.greyson.file.StarterFileChooser;
import eu.greyson.parser.*;
import eu.greyson.payment.Payable;

import java.io.Console;
import java.io.File;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Paytracker {

    public static void main(String args[]) {
        final Parser<Payable> payableParser = new PaymentParser(new CurrencyParser(), new AmountParser());
        final Parser<List<Payable>> fileParser = new PaymentFileParser(payableParser);
        final Console c = System.console();

        String loadData = c.readLine("Do you want to load file with payments? (Y/n)");
        if (loadData.trim().equalsIgnoreCase("y")) {
            File f = new StarterFileChooser().chooseFile();
            if (f != null) {
                try {
                    Bookkeeper.addAll(fileParser.parse(f.getPath()));
                } catch (Exception e) {
                    c.printf(e.getLocalizedMessage());
                    c.printf("\n");
                    c.printf("\nInsert new payment.");
                }
            }
        }
        c.printf("You can write your payments below: \n");
//        initTimer(c);
        while(true) {
            try {
                String input = c.readLine("\n Payment: ");

                if (input.equalsIgnoreCase("print")) {
                    printTotals(c);
                    continue;
                }

                if (input.equalsIgnoreCase("quit")) {
                    break;
                }

                Payable p = payableParser.parse(input);
                Bookkeeper.add(p);

            } catch (Exception e) {
                c.printf(e.getLocalizedMessage());
                c.printf("\n");
                c.printf("\nInsert new payment.");
            }
        }
        System.exit(1);
    }

    private static void printTotals(Console c) {
        c.printf("\nTOTAL\n");
        new Bookkeeper().countTotals()
                .forEach(payable -> c.printf(payable.getDisplayName() + "\n"));
    }

    private static void initTimer(Console c) {
        Runnable helloRunnable = new Runnable() {
            public void run() {
                c.printf("\nTOTAL\n");
                new Bookkeeper().countTotals()
                        .forEach(payable -> c.printf(payable.getDisplayName() + "\n"));
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);
    }
}
