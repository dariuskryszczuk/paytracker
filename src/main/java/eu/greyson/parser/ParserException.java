package eu.greyson.parser;

public class ParserException extends RuntimeException {
    static final long serialVersionUID = 43L;

    public ParserException(String msg) {
        super(msg);
    }

    public ParserException(String msg, String input) {
        super(msg);
    }

    static ParserException forInputString(String s) {
        return new ParserException("Incorrect input: \"" + s + "\"", s);
    }
}
