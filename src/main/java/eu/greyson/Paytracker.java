package eu.greyson;

import eu.greyson.parser.*;
import eu.greyson.payment.Payable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static eu.greyson.PrintManager.*;

public class Paytracker {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        printWelcome();
        String answer = printLoadFileQuestion(br);
        if (answer.trim().equalsIgnoreCase("y")) {
            showDialog();
            printTotalTable();
        }
        initTimer();
        while(true) {
            try {
                String input = printPaymentQuestion(br);
                if (input.equalsIgnoreCase("quit")) {
                    break;
                }
                Payable p = new PaymentParser(new CurrencyParser(), new AmountParser()).parse(input);
                Bookkeeper.add(p);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        System.exit(1);
    }

    private static void initTimer() {
        Runnable r = new Runnable() {
            public void run() {
                printTotalTable();
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(r, 60, 60, TimeUnit.SECONDS);
    }
}
