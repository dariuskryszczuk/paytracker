package eu.greyson;

import eu.greyson.payment.Payable;
import eu.greyson.payment.Payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Bookkeeper {

    private static List<Payable> payments = new ArrayList<>();

    private static void add(Payable p) {
        Bookkeeper.add(p);
    }

    static void addAll(List<Payable> payableList) {
        Bookkeeper.payments.addAll(payableList);
    }

    static List<Payable> getPayments() {
        return new ArrayList<>(payments);
    }


//    public static List<Payable> aggregate() {
//        List<Payable> aggregated = new ArrayList<>();
//        for (Payable p : getSortedByCode()) {
//
//        }
//    }

    static List<Payable> getSortedByCode() {
        List<Payable> sorted = new ArrayList<>(payments);
        if (sorted.size() > 0) {
            sorted.sort(Comparator.comparing(p -> p.getCurrency().getCurrencyCode()));
        }
        return sorted;
    }

}
