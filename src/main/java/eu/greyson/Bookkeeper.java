package eu.greyson;

import eu.greyson.currency.CurrencyDesignator;
import eu.greyson.payment.Payable;
import eu.greyson.payment.Payment;

import java.util.*;

/**
 * {@code Bookkeeper} class is responsible for bookkeeping all the money operations
 */
public class Bookkeeper {

    private static List<Payable> payments = new ArrayList<>();

    static void add(Payable p) {
        Bookkeeper.payments.add(p);
    }

    public static void addAll(List<Payable> payableList) {
        Bookkeeper.payments.addAll(payableList);
    }

    List<Payable> getPayments() {
        return new ArrayList<>(payments);
    }

    public Collection<Payable> countTotals() {
        Map<String, Payable> totals = new HashMap<>();
        getPayments().forEach(p -> {
            CurrencyDesignator currency = p.getCurrency();
            if (!totals.containsKey(currency.getCurrencyCode())) {
                Payable zero = Payment.zero(currency);
                totals.put(currency.getCurrencyCode(), zero);
            }
            Payable previous = totals.get(currency.getCurrencyCode());
            Payable newTotal = previous.add(p);
            totals.put(currency.getCurrencyCode(), newTotal);
        });
        return totals.values();
    }

    static void reset() {
        payments = new ArrayList<>();
    }

    List<Payable> getSortedPaymentsByCurrency() {
        List<Payable> sorted = new ArrayList<>(payments);
        if (sorted.size() > 0) {
            sorted.sort(Comparator.comparing(p -> p.getCurrency().getCurrencyCode()));
        }
        return sorted;
    }
}
