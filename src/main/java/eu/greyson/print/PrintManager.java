package eu.greyson.print;

import eu.greyson.Bookkeeper;
import eu.greyson.file.StarterFileChooser;
import eu.greyson.parser.*;
import eu.greyson.payment.Payable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class PrintManager {

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public static void printWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("****************************************************************************************************\n");
        sb.append("                                  WELCOME TO THE PAYTRACKER 9000\n");
        sb.append("****************************************************************************************************\n");
        sb.append("Welcome to the payment tracker 'PAYTRACKER 9000'. You can load or add new Payments in the format: \n\n");
        sb.append("                                <currency code/symbol> <amount> \n");
        sb.append("                                              or\n");
        sb.append("                                <amount> <currency code/symbol> \n\n");
        sb.append("Supported formats of currencies are ISO 4217 currency codes or symbols \u0024, \u20ac and \u00A3. Aggregated \n");
        sb.append("payments are printed every minute or when 'print' is written. Write 'quit' to exit the application or \n");
        sb.append("'reset' for resetting the total table count. \n");
        sb.append("****************************************************************************************************\n");
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
        Bookkeeper.groupByCurrencyCode().forEach(pay -> System.out.println("  " + pay.getDisplayName()));
        System.out.format("+------------------+%n");
        System.out.println();
    }

    public static void showDialog() {
        final Parser<Payable> payableParser = new PaymentParser(new CurrencyParser(), new AmountParser());
        final Parser<List<Payable>> fileParser = new PaymentFileParser(payableParser);
        File f = new StarterFileChooser().chooseFile();
        if (f != null) {
            try {
                Bookkeeper.addAll(fileParser.parse(f.getPath()));
                System.out.println("\nFile \"" + f.getName() + "\" successfully loaded.");
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
            System.out.print("> ");
            return BUFFERED_READER.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error when accepting users payment.");
        }
    }

    public static void printResetOption() {
        try {
            System.out.print("\nReally want to reset the total table count? (Y/N)");
            String answer = BUFFERED_READER.readLine();
            if (answer.equalsIgnoreCase("y")) {
                Bookkeeper.reset();
                System.out.print("\nTotal table count was successfully reset.\n");
                printTotalTable();
            } else {
                System.out.print("\nCanceling operation.\n");
            }
        } catch (IOException io) {
            throw new RuntimeException("Error when resetting total table count.");
        }
    }
}
