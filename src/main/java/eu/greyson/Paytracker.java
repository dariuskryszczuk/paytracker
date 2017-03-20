package eu.greyson;

import eu.greyson.file.StarterFileChooser;
import eu.greyson.parser.*;
import eu.greyson.payment.Payable;

import java.io.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Paytracker {

    public static void main(String args[]) throws IOException {
        final Parser<Payable> payableParser = new PaymentParser(new CurrencyParser(), new AmountParser());
        final Parser<List<Payable>> fileParser = new PaymentFileParser(payableParser);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Do you want to load file with payments? (Y/n)");
        String answer = br.readLine();
        if (answer.trim().equalsIgnoreCase("y")) {
            File f = new StarterFileChooser().chooseFile();
            if (f != null) {
                try {
                    Bookkeeper.addAll(fileParser.parse(f.getPath()));
                    System.out.println("Loaded");
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
        System.out.println("You can write your payments below:");
        initTimer();
        while(true) {
            try {
                System.out.print("Payment: ");
                String input = br.readLine();
                if (isPrint(input)) {
                    printTotals();
                    continue;
                }
                if (isQuit(input)) {
                    break;
                }
                Payable p = payableParser.parse(input);
                Bookkeeper.add(p);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        System.exit(1);
    }

    private static boolean isQuit(String input) {
        return input.equalsIgnoreCase("quit");
    }

    private static boolean isPrint(String input) {
        return input.equalsIgnoreCase("print");
    }

    private static void printTotals() {
        System.out.println("\n");
        System.out.println("TOTAL");
        new Bookkeeper().countTotals()
                .forEach(payable -> System.out.println(payable.getDisplayName() + "\n"));
    }

    private static void initTimer() {
        Runnable r = new Runnable() {
            public void run() {
                printTotals();
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(r, 0, 60, TimeUnit.SECONDS);
    }
}
