package eu.greyson.parser;

class ParserException extends RuntimeException {
    static final long serialVersionUID = 43L;

    private ParserException(String msg) {
        super(msg);
    }

    static ParserException forInputString(String s) {
        return new ParserException("Can't parse input string: \"" + s + "\"");
    }
}
