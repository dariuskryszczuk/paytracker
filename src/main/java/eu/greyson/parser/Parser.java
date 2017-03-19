package eu.greyson.parser;

import org.jetbrains.annotations.NotNull;

public interface Parser<T> {

    @NotNull
    T parse(String s) throws ParserException;

}
