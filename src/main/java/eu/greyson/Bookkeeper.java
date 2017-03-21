package eu.greyson;

import eu.greyson.currency.CurrencyDesignator;
import eu.greyson.payment.Payable;
import eu.greyson.payment.Payment;

import java.util.*;

/**
 * {@code Bookkeeper} is a static class responsible for bookkeeping all the money operations
 */
public class Bookkeeper {

    private Bookkeeper() {}

    private static List<Payable> payments = new ArrayList<>();

    synchronized static List<Payable> getPayments() {
        return new ArrayList<>(payments);
    }

    public synchronized static void reset() {
        payments = new ArrayList<>();
    }

    public synchronized static void add(Payable p) {
        Bookkeeper.payments.add(p);
    }

    public synchronized static void addAll(List<Payable> payableList) {
        Bookkeeper.payments.addAll(payableList);
    }

    /**
     *
     * @return payments grouped by currency code
     */
    public static Collection<Payable> groupByCurrencyCode() {
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

    /**
     * @return payments sorted by currency code
     */
    static List<Payable> orderByCurrencyCode() {
        List<Payable> sorted = new ArrayList<>(getPayments());
        if (sorted.size() > 0) {
            sorted.sort(Comparator.comparing(p -> p.getCurrency().getCurrencyCode()));
        }
        return sorted;
    }
}
