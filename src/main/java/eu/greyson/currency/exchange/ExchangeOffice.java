package eu.greyson.currency.exchange;

import eu.greyson.payment.Payment;

public interface ExchangeOffice {

    Payment exchange(ExchangeRate rate);

}
