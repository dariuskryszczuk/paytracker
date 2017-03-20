package eu.greyson.print;

import eu.greyson.Bookkeeper;
import eu.greyson.file.StarterFileChooser;
import eu.greyson.parser.*;
import eu.greyson.payment.Payable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

public class PrintManager {

    public static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public static void printWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("********************************************************************************************\n");
        sb.append("                             WELCOME TO THE PAYTRACKER 9000\n");
        sb.append("********************************************************************************************\n");
        sb.append("Welcome to the payment tracker 'PAYTRACKER'. You can load or add new Payments in the format: \n");
        sb.append("\n<currency code/symbol> <amount> \n");
        sb.append("              or\n");
        sb.append("<amount> <currency code/symbol> \n\n");
        sb.append("Supported formats of currency codes are ISO 4217. Aggregated payments are printed every minute.");
        sb.append("Write 'quit' to exit the application.");
        System.out.print(sb.toString());
    }

    public static String printLoadFileQuestion() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("\nDo you want to load file with payments? Only txt files are now supported. (Y/N)\n");
            System.out.print(sb.toString());
            return BUFFERED_READER.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error when answering question.");
        }


    }

    public static void printTotalTable() {
        System.out.println();
        System.out.format("+------------------+%n");
        System.out.format("|       TOTAL      |%n");
        System.out.format("+------------------+%n");
        new Bookkeeper().countTotals().forEach(pay -> System.out.println("  " + pay.getDisplayName()));
        System.out.format("+------------------+%n");
        System.out.println();
//        printPaymentQuestion();
    }

    public static void showDialog() {
        final Parser<Payable> payableParser = new PaymentParser(new CurrencyParser(), new AmountParser());
        final Parser<List<Payable>> fileParser = new PaymentFileParser(payableParser);
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

    public static void printLoadFileDialogQuestion() {
        String answer = printLoadFileQuestion();
        if (answer.trim().equalsIgnoreCase("y")) {
            showDialog();
            printTotalTable();
        }
    }

    public static String printPaymentQuestion() {
        try {
            System.out.print("Payment: ");
            return BUFFERED_READER.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error when accepting users payment.");
        }
    }
}
