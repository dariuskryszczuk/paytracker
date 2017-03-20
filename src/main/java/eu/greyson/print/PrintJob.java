package eu.greyson.print;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static eu.greyson.print.PrintManager.printTotalTable;

/**
 * This print job prints table of aggregated payments
 */
public class PrintJob {

    /**
     * @param delay in seconds
     * @param period in seconds
     */
    public static void run(long delay, long period) {
        Runnable r = new Runnable() {
            public void run() {
                printTotalTable();
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(r, delay, period, TimeUnit.SECONDS);
    }
}
