package eu.greyson.calculator;

@Deprecated
class UnsupportedMoneyOperation extends UnsupportedOperationException {
    static final long serialVersionUID = 44L;

    UnsupportedMoneyOperation(String msg) {
        super(msg);
    }
}
