package eu.greyson;

import eu.greyson.file.StarterFileChooser;
import eu.greyson.parser.*;
import eu.greyson.payment.Payable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class PrintManager {

    public static void printWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("****************************************************************************\n");
        sb.append("                               PAYTRACKER\n");
        sb.append("****************************************************************************\n");
        sb.append("Welcome to the Paytracker. You can load or add new Payments in the format: \n");
        sb.append("<currency code/symbol> <amount> \n");
        sb.append("<amount> <currency code/symbol> \n");
        sb.append("Supported formats of currency codes are ISO 4217");
        System.out.print(sb.toString());
    }

    public static String printLoadFileQuestion(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("\nDo you want to load file with payments? Only txt files are now supported. (Y/N)\n");
        System.out.print(sb.toString());
        return br.readLine();
    }
    public static String printAddPaymentQuestion(BufferedReader br) throws IOException {
        System.out.print("Payment: ");
        return br.readLine();
    }

    public static void printTotalTable() {
        System.out.println();
        System.out.format("+------------------+%n");
        System.out.format("|       TOTAL      |%n");
        System.out.format("+------------------+%n");
        new Bookkeeper().countTotals().forEach(pay -> System.out.println("  " + pay.getDisplayName()));
        System.out.format("+------------------+%n");
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

    public static String printPaymentQuestion(BufferedReader br) throws IOException {
        System.out.print("Payment: ");
        return br.readLine();
    }
}
