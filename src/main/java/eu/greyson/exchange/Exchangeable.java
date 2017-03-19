package eu.greyson.exchange;

import org.jetbrains.annotations.Nullable;

public interface Exchangeable {

    @Nullable
    ExchangeRate getExchangeRate();
}
